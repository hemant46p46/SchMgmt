package com.schmgmt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schmgmt.models.Student;
import com.schmgmt.repositories.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
}
