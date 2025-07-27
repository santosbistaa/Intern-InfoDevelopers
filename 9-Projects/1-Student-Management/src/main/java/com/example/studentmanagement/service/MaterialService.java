package com.example.studentmanagement.service;


import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Material;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final CourseRepository courseRepository;

    @Value("${app.upload.dir:${user.home}/Student_management/uploads}")
    private String uploadDir;

    public Material uploadMaterial(Long courseId,
                                   String title,
                                   String description,
                                   MultipartFile file) throws IOException {

        Course course = courseRepository.findById(courseId).orElseThrow();

        String filePath = null;
        if (!file.isEmpty()) {
            String directory = uploadDir + "/course-" + courseId;
            Files.createDirectories(Paths.get(directory));

            String originalFilename = file.getOriginalFilename();
            String safeFilename = originalFilename.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
            filePath = directory + "/" + safeFilename;

            file.transferTo(new File(filePath));
        }

        Material material = Material.builder()
                .title(title)
                .description(description)
                .filePath(filePath)
                .course(course)
                .build();

        return materialRepository.save(material);
    }

    public List<Material> getMaterialByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        return materialRepository.findByCourse(course);
    }

    public byte[] downloadMaterial(Long materialId) throws IOException {
        Material material = materialRepository.findById(materialId).orElseThrow();
        return Files.readAllBytes(Paths.get(material.getFilePath()));
    }

    public String getMaterialFileName(Long materialId) {
        Material material = materialRepository.findById(materialId).orElseThrow();
        return Paths.get(material.getFilePath()).getFileName().toString();
    }

    public List<Material> getAllMaterials(){
        return materialRepository.findAll();
    }
}