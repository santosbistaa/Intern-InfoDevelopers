package com.ocms.controller;

import com.ocms.dto.UserRegistrationDto;
import com.ocms.dto.UserResponseDto;
import com.ocms.entities.User;
import com.ocms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<User> register (@RequestBody @Valid UserRegistrationDto dto) {
////        return ResponseEntity.ok(userService.registerUser(dto));
//        User registeredUser = userService.registerUser(dto);
//        return ResponseEntity.ok(registeredUser);
//    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(
            @RequestBody @Valid UserRegistrationDto registrationDto
    ) {
        User registeredUser = userService.registerUser(registrationDto);
        return ResponseEntity.ok(UserResponseDto.fromUser(registeredUser));
    }
}

