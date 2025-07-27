package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Enrollment;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.EnrollmentRepository;
import com.example.studentmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepo;
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;

    public Enrollment enrollStudent(String email, Long courseId) {
        User student = userRepo.findByEmail(email).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();
        return enrollmentRepo.save(new Enrollment(null, student, course));
    }

    public List<Course> getEnrolledCourses(String email) {
        User student = userRepo.findByEmail(email).orElseThrow();
        return enrollmentRepo.findByStudent(student)
                .stream()
                .map(Enrollment::getCourse)
                .distinct()
                .toList();
    }
}

