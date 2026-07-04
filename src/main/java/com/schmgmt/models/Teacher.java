package com.schmgmt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacher_id")
	private Long teacherId;
	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;
	
	@Column(name = "department", nullable = false, length = 50)
	private String department;
}
