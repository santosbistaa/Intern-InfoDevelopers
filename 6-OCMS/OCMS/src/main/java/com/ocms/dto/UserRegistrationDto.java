package com.ocms.dto;

import com.ocms.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationDto(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password,
        @NotNull Role role
) {}
