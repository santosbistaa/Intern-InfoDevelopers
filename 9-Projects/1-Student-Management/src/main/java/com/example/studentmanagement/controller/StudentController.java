package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Enrollment;
import com.example.studentmanagement.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final EnrollmentService enrollmentService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/enroll/{courseId}")
    public Enrollment enroll(@PathVariable Long courseId, Principal principal) {
        return enrollmentService.enrollStudent(principal.getName(), courseId);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/courses")
    public List<Course> getMyCourses(Principal principal) {
        return enrollmentService.getEnrolledCourses(principal.getName());
    }
}
