package com.schmgmt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schmgmt.models.Course;
import com.schmgmt.repositories.CourseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Course saveCourse(Course course) {
        log.info("Attempting to save new course with code: {}", course.getCourseCode());

        if (this.courseRepository.existsByCourseCode(course.getCourseCode())) {
            log.error("Save failed: Course code {} already exists", course.getCourseCode());
            throw new IllegalArgumentException("Course code already registered: " + course.getCourseCode());
        }

        Course savedCourse = this.courseRepository.save(course);
        log.info("Successfully saved course with ID: {}", savedCourse.getCourseId());
        return savedCourse;
    }

    public List<Course> getAllCourses() {
        log.info("Fetching all courses");
        return this.courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        log.info("Fetching course with ID: {}", id);
        return this.courseRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Fetch failed: Course not found with ID: {}", id);
                    return new RuntimeException("Course not found with id: " + id);
                });
    }

    @Transactional
    public Course updateCourse(Course courseDetails, Long id) {
        log.info("Attempting to update course with ID: {}", id);

        Course existingCourse = this.courseRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Update failed: Course not found with ID: {}", id);
                    return new RuntimeException("Course not found with id: " + id);
                });

        this.courseRepository.findByCourseCode(courseDetails.getCourseCode())
                .ifPresent(c -> {
                    if (!c.getCourseId().equals(id)) {
                        log.error("Update failed: Course code {} is already assigned to another course", courseDetails.getCourseCode());
                        throw new IllegalArgumentException("Course code already taken: " + courseDetails.getCourseCode());
                    }
                });

        int updatedRows = this.courseRepository.updateCourseFields(courseDetails, id);
        if (updatedRows == 0) {
            throw new RuntimeException("Failed to update course data with id: " + id);
        }

        courseDetails.setCourseId(id);
        log.info("Successfully updated course with ID: {}", id);
        return courseDetails;
    }

    @Transactional
    public void deleteCourse(Long id) {
        log.info("Attempting to delete course with ID: {}", id);

        if (!this.courseRepository.existsById(id)) {
            log.error("Delete failed: Course not found with ID: {}", id);
            throw new RuntimeException("Cannot delete. Course not found with id: " + id);
        }

        this.courseRepository.deleteById(id);
        log.info("Successfully deleted course with ID: {}", id);
    }
}
