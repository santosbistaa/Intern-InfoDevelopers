package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Assignment;
import com.example.studentmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCourse(Course course);
}
