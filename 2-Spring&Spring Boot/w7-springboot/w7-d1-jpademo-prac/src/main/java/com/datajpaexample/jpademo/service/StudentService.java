package com.datajpaexample.jpademo.service;

import com.datajpaexample.jpademo.entity.Student;
import com.datajpaexample.jpademo.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private  StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create Student
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    // Read all students
    public List<Student> readAllStudents() {
        return studentRepository.findAll();
    }

    // Read students by id
    public Student readStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Update student
    public Student updateStudent(int id, Student updatedStudent){
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setAddress(updatedStudent.getAddress());
            return studentRepository.save(student);
        }).orElse(null);
    }

    // Delete Student by id
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
