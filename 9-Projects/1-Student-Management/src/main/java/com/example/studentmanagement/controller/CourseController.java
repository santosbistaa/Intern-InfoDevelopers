package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/course")
    public Course createCourse(@RequestBody Course course, Principal principal) {
        User instructor = userRepo.findByEmail(principal.getName()).orElseThrow();
        course.setInstructor(instructor);
        return courseRepo.save(course);
    }
}