package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Assignment;
import com.example.studentmanagement.entity.AssignmentSubmission;
import com.example.studentmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {
    List<AssignmentSubmission> findByAssignment(Assignment assignment);
    List<AssignmentSubmission> findByStudent(User student);
}
