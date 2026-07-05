package com.schmgmt.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.schmgmt.models.Student;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Student save(Student student) {
        if (student.getStudentId() == null) {
            this.entityManager.persist(student);
            return student;
        } else {
            return this.entityManager.merge(student);
        }
    }

    public List<Student> findAll() {
        return this.entityManager
                .createNamedQuery("Student.findAll", Student.class)
                .getResultList();
    }

    public Optional<Student> findById(Long id) {
        List<Student> results = this.entityManager
                .createNamedQuery("Student.findById", Student.class)
                .setParameter("id", id)
                .getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Transactional
    public Student update(Student studentDetails, Long id) {
        int updatedRows = this.entityManager
                .createNamedQuery("Student.updateFields")
                .setParameter("firstName", studentDetails.getFirstName())
                .setParameter("lastName", studentDetails.getLastName())
                .setParameter("email", studentDetails.getEmail())
                .setParameter("enrollmentDate", studentDetails.getEnrollmentDate())
                .setParameter("gpa", studentDetails.getGpa())
                .setParameter("address", studentDetails.getAddress())
                .setParameter("profilePic", studentDetails.getProfilePic())
                .setParameter("id", id)
                .executeUpdate();

        if (updatedRows == 0) {
            throw new RuntimeException("Student record missing for id: " + id);
        }
        studentDetails.setStudentId(id);
        return studentDetails;
    }

    @Transactional
    public void deleteById(Long id) {
        int deletedRows = this.entityManager
                .createNamedQuery("Student.deleteById")
                .setParameter("id", id)
                .executeUpdate();

        if (deletedRows == 0) {
            throw new RuntimeException("Student record missing for id: " + id);
        }
    }}
