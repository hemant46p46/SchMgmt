package com.schmgmt.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "teachers")
@NamedQueries({
	@NamedQuery(name = "Teacher.findAllNQ", query = "SELECT t from Teacher t"),
	@NamedQuery(name = "Teacher.findById", query = "SELECT t from Teacher t WHERE t.teacherId = :id"),
	@NamedQuery(name = "Teacher.updateFields", query = "UPDATE Teacher t set t.firstName = :firstName, t.lastName = :lastName, t.email = :email, t.department = :department where t.teacherId = :id"),
	@NamedQuery(name = "Teacher.deleteById", query = "DELETE FROM Teacher t where t.teacherId= :id")
})
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
	
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true)
    private User user;
}
