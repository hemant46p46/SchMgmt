package com.schmgmt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@Table(name = "students")
@NamedQueries({
    @NamedQuery(
        name = "Student.findAll", 
        query = "SELECT s FROM Student s"
    ),
    @NamedQuery(
        name = "Student.findById", 
        query = "SELECT s FROM Student s WHERE s.studentId = :id"
    ),
    @NamedQuery(
        name = "Student.updateFields", 
        query = "UPDATE Student s SET s.firstName = :firstName, s.lastName = :lastName, s.email = :email, s.enrollmentDate = :enrollmentDate, s.gpa = :gpa, s.address = :address, s.profilePic = :profilePic WHERE s.studentId = :id"
    ),
    @NamedQuery(
        name = "Student.deleteById", 
        query = "DELETE FROM Student s WHERE s.studentId = :id"
    )
})
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotNull(message = "Enrollment date is required")
    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Min(value = 0, message = "GPA cannot be less than 0.0")
    @Max(value = 4, message = "GPA cannot be greater than 4.0")
    @Column(name = "gpa")
    private Double gpa;

    @Size(max = 200, message = "Address must not exceed 200 characters")
    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "profile_pic", columnDefinition = "bytea")
    private byte[] profilePic;
}
