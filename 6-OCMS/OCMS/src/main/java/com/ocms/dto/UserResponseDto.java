package com.ocms.dto;

import com.ocms.entities.Role;
import com.ocms.entities.User;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        Role role
) {
    public static UserResponseDto fromUser(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}
