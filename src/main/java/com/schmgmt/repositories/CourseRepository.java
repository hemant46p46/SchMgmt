package com.schmgmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schmgmt.models.Course;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseCode(String courseCode);

    boolean existsByCourseCode(String courseCode);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Course c SET c.courseCode = :#{#course.courseCode}, c.courseName = :#{#course.courseName}, c.credits = :#{#course.credits} WHERE c.courseId = :id")
    int updateCourseFields(@Param("course") Course course, @Param("id") Long id);
}
