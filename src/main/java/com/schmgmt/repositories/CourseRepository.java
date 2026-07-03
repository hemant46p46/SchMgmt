package com.schmgmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schmgmt.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
