package com.ocms.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseCreateDto(
        @NotBlank String title,
        String description
) {}
