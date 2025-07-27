package com.ocms.service;

import com.ocms.dto.CourseCreateDto;
import com.ocms.entities.Course;
import com.ocms.entities.Role;
import com.ocms.entities.User;
import com.ocms.repository.CourseRepository;
import com.ocms.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private UserRepository userRepository;

    public Course createCourse(CourseCreateDto dto, User instructor){
        Course course = new Course();
        course.setTitle(dto.title());
        course.setDescription(dto.description());
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public void enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        User student = userRepository.findById(studentId).orElseThrow();

        if (student.getRole() != Role.STUDENT) {
            throw new RuntimeException("Invalid role of the student");
        }

        course.getStudents().add(student);
        courseRepository.save(course);
    }
}
