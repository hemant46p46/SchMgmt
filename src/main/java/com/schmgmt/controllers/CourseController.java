package com.schmgmt.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schmgmt.models.Course;
import com.schmgmt.services.CourseService;

@RestController
@RequestMapping("/schmgmt/api/courses")
public class CourseController {
	private CourseService courseService;
	
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@GetMapping("/getAllCourses")
	public List<Course> getAllCourses() {
		return this.courseService.getAllCourse();
	}
}
