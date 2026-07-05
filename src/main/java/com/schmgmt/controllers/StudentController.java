package com.schmgmt.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.schmgmt.models.Student;
import com.schmgmt.services.StudentService;

@RestController
@RequestMapping("/schmgmt/api/students")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/saveStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@Valid @RequestBody Student student) {
        log.info("REST request to save Student : {}", student.getEmail());
        return this.studentService.saveStudent(student);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        log.info("REST request to get all Students");
        return this.studentService.getAllStudents();
    }

    @GetMapping("/getStudent/{id}")
    public Student getStudentById(@PathVariable Long id) {
        log.info("REST request to get Student by ID : {}", id);
        return this.studentService.getStudentById(id);
    }

    @PutMapping("/updateStudent/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentDetails) {
        log.info("REST request to update Student ID : {}", id);
        return this.studentService.updateStudent(studentDetails, id);
    }

    @DeleteMapping("/deleteStudent/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        log.info("REST request to delete Student ID : {}", id);
        this.studentService.deleteStudent(id);
    }
}
