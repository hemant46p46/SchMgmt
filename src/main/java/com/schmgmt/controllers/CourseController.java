package com.schmgmt.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.schmgmt.models.Course;
import com.schmgmt.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/schmgmt/api/v1/courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/saveCourse")
    @ResponseStatus(HttpStatus.CREATED)
    public Course saveCourse(@Valid @RequestBody Course course) {
        log.info("REST request to save Course : {}", course.getCourseCode());
        return this.courseService.saveCourse(course);
    }

    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses() {
        log.info("REST request to get all Courses");
        return this.courseService.getAllCourses();
    }

    @GetMapping("/courseById/{id}")
    public Course getCourseById(@PathVariable Long id) {
        log.info("REST request to get Course by ID : {}", id);
        return this.courseService.getCourseById(id);
    }

    @PutMapping("/updateCourse/{id}")
    public Course updateCourse(@PathVariable Long id, @Valid @RequestBody Course courseDetails) {
        log.info("REST request to update Course ID : {}", id);
        return this.courseService.updateCourse(courseDetails, id);
    }

    @DeleteMapping("/deleteCourse/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        log.info("REST request to delete Course ID : {}", id);
        this.courseService.deleteCourse(id);
    }
    
    
    

    @PostMapping("/saveCourseRE")
    public ResponseEntity<Course> saveCourseRE(@Valid @RequestBody Course course) {
        log.info("REST request to save Course : {}", course.getCourseCode());
        Course savedCourse = this.courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/getAllCoursesRE")
    public ResponseEntity<List<Course>> getAllCoursesRE() {
        log.info("REST request to get all Courses");
        List<Course> courses = this.courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/getCourseByIdRE/{id}")
    public ResponseEntity<Course> getCourseByIdRE(@PathVariable Long id) {
        log.info("REST request to get Course by ID : {}", id);
        Course course = this.courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/updateCourseRE/{id}")
    public ResponseEntity<Course> updateCourseRE(@PathVariable Long id, @Valid @RequestBody Course courseDetails) {
        log.info("REST request to update Course ID : {}", id);
        Course updatedCourse = this.courseService.updateCourse(courseDetails, id);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/deleteCourseRE/{id}")
    public ResponseEntity<Void> deleteCourseRE(@PathVariable Long id) {
        log.info("REST request to delete Course ID : {}", id);
        this.courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

