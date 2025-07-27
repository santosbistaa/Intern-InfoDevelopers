package com.ocms.controller;

import com.ocms.dto.CourseCreateDto;
import com.ocms.entities.Course;
import com.ocms.entities.User;
import com.ocms.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Course> createCourse(
            @RequestBody @Valid CourseCreateDto dto,
            @AuthenticationPrincipal User instructor
            ){
        return ResponseEntity.ok(courseService.createCourse(dto, instructor));
    }

    @PostMapping("/{courseId}/enroll")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Void> enroll(
            @PathVariable Long courseId,
            @AuthenticationPrincipal User student
    ){
        courseService.enrollStudent(courseId, student.getId());
        return ResponseEntity.ok().build();
    }
}
