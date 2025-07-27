package com.ocms.repository;

import com.ocms.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {

    // find all assignment belongings to a specific course
    List<Assignment> findByCourseId(Long courseId);
}
