package com.ocms.repository;

import com.ocms.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // finds all courses taught by a specific instructor
    List<Course> findByInstructorId(Long instructorId);

    // find all courses where a specific student is enrolled
    List<Course> findByStudentsId(Long studentId);
}
