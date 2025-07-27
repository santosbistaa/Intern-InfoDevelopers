package com.ocms.service;

import com.ocms.dto.UserRegistrationDto;
import com.ocms.entities.Role;
import com.ocms.entities.User;
import com.ocms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());
        return userRepository.save(user);
    }

    public List<User> getAllInstructors(){
        return userRepository.findByRole(Role.INSTRUCTOR);
    }
}
