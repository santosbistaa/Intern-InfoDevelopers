package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Assignment;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.repository.AssignmentRepository;
import com.example.studentmanagement.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    public Assignment createAssignment(Long courseId, String title, String description, LocalDate dueDate){
        Course course = courseRepository.findById(courseId).orElseThrow();
        Assignment assignment = Assignment.builder()
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .course(course)
                .build();
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByCourse(Long courseId){
        Course course = courseRepository.findById(courseId).orElseThrow();
        return assignmentRepository.findByCourse(course);
    }
}
