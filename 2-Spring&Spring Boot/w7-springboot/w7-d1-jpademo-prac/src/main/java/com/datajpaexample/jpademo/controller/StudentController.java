package com.datajpaexample.jpademo.controller;

import com.datajpaexample.jpademo.entity.Student;
import com.datajpaexample.jpademo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private  StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> readAllStudents(){
        return studentService.readAllStudents();
    }

    @GetMapping("/{id}")
    public Student readStudentById(@PathVariable int id) {
        return studentService.readStudentById(id);
    }

    @PutMapping("/{id}")
        public Student updateStudent(@PathVariable int id , @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

}
