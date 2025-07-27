package com.ocms.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record CourseMaterialDto(
        @NotBlank String title,
        String description,
        MultipartFile file
) {}
