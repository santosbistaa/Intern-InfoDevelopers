package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Material;
import com.example.studentmanagement.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/material")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/upload/{courseId}")
    public Material upload(@PathVariable Long courseId,
                           @RequestParam("title")String title,
                           @RequestParam("description")String description,
                           @RequestParam("file")MultipartFile file
    )throws IOException{
        return materialService.uploadMaterial(courseId, title, description, file);
    }

    @PreAuthorize("hasAnyRole('STUDENT','INSTRUCTOR')")
    @GetMapping("/course/{courseId}")
    public List<Material> getCourseMaterials(@PathVariable Long courseId) {
        return materialService.getMaterialByCourse(courseId);
    }

    @PreAuthorize("hasAnyRole('STUDENT','INSTRUCTOR')")
    @GetMapping("/download/{materialId}")
    public ResponseEntity<byte[]> download (@PathVariable Long materialId) throws IOException {
        byte[] data = materialService.downloadMaterial(materialId);
        String fileName = materialService.getMaterialFileName(materialId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename(fileName).build());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }
}
