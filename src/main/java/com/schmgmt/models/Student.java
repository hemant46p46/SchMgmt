package com.schmgmt.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@NamedNativeQuery(name = "Student.findEnrolledCoursesByEntity", 
	query = "SELECT c.course_id AS courseId, c.course_code AS courseCode, c.course_name AS courseName, " + 
		"c.credits AS credits, sc.enrollment_semester AS semester FROM courses c JOIN student_courses sc ON c.course_id = sc.course_id " + 
		"where sc.student_id = :studentId", resultSetMapping = "courseMapping")
@SqlResultSetMapping(name = "courseMapping", columns = {
		@ColumnResult(name = "courseId", type = Long.class),
		@ColumnResult(name = "courseCode", type = String.class),
		@ColumnResult(name = "courseName", type = String.class),
		@ColumnResult(name = "credits", type = Integer.class),
		@ColumnResult(name = "semester", type = String.class)
})
@Data
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long studentId;
	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(name = "enrollment_date")
	private LocalDate enrollmentDate;
	
	@Column(name = "gpa")
	private Double gpa;
	
	@Column(name = "address", length = 200)
	private String address;	

	@Column(name = "profile_pic", columnDefinition = "bytea")
	private byte[] profilePic;
}
