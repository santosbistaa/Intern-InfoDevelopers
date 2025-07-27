package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @PostMapping("/student")
    public User registerStudent(@RequestBody User user) {
        user.setRole(User.Role.STUDENT);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PostMapping("/instructor")
    public User registerInstructor(@RequestBody User user) {
        user.setRole(User.Role.INSTRUCTOR);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
