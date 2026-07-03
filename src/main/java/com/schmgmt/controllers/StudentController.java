package com.schmgmt.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schmgmt.models.Student;
import com.schmgmt.services.StudentService;

@RestController
@RequestMapping("/schmgmt/api/students")
public class StudentController {
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents(){
		return this.studentService.getAllStudents();
	}
}
