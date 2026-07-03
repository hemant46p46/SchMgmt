package com.schmgmt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schmgmt.models.Course;
import com.schmgmt.repositories.CourseRepository;

@Service
public class CourseService {
	private CourseRepository courseRepository;
	
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> getAllCourse(){
		return this.courseRepository.findAll();
	}
}
