package com.santos.security.securityconfig;

import com.santos.security.jwt.AuthEntryPointJwt;
import com.santos.security.jwt.AuthTokenFilter;
import com.santos.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private JwtUtils jwtUtils;


//    private AuthTokenFilter authTokenFilter;
//    {
//        return new AuthTokenFilter();
//    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(UserDetailsService userDetailsService) {
        AuthTokenFilter filter = new AuthTokenFilter();
        filter.setJwtUtils(jwtUtils);
        filter.setUserDetailsService(userDetailsService);
        return filter;
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests
                        // Allows unauthenticated access to /signin
                        .requestMatchers("/signin").permitAll()
                        // Requires authentication for any other request
                        .anyRequest().authenticated()
        );

        // here we are making the stateless session->cookies is not created
        http.sessionManagement(session->
                session.sessionCreationPolicy
                        (SessionCreationPolicy.STATELESS));

        //  here we are disabling the form login
        //http.formLogin(Customizer.withDefaults());

        // Default Login
        //http.httpBasic(Customizer.withDefaults());

        // Exception handling mechanism from AuthEntryPointJwt for authentication related errors
        http.exceptionHandling(exception->
                exception.authenticationEntryPoint(unauthorizedHandler));


        http.csrf(csrf->csrf.disable());

        // now we have added a custom filter that we have created in AuthTokenFilter
        // before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authenticationJwtTokenFilter(userDetailsService()),
                UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        // Check if the user already exists before adding it
        if (!userDetailsManager.userExists("user")) {
            userDetailsManager.createUser(user);
        } else {
            userDetailsManager.updateUser(user); // If user exists, update it
        }

        if (!userDetailsManager.userExists("admin")) {
            userDetailsManager.createUser(admin);
        } else {
            userDetailsManager.updateUser(admin); // If admin exists, update it
        }

        return userDetailsManager;
//        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
