package com.schmgmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schmgmt.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
}
