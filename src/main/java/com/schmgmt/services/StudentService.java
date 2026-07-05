package com.schmgmt.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schmgmt.models.Student;
import com.schmgmt.repositories.StudentRepository;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student saveStudent(Student student) {
        log.info("Saving student: {}", student.getEmail());
        this.studentRepository.findAll().stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(student.getEmail()))
                .findFirst()
                .ifPresent(s -> {
                    throw new IllegalArgumentException("Email already registered: " + student.getEmail());
                });
        return this.studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        log.info("Fetching all students");
        return this.studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        log.info("Fetching student ID: {}", id);
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Transactional
    public Student updateStudent(Student studentDetails, Long id) {
        log.info("Updating student ID: {}", id);
        this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        this.studentRepository.findAll().stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(studentDetails.getEmail()) && !s.getStudentId().equals(id))
                .findFirst()
                .ifPresent(s -> {
                    throw new IllegalArgumentException("Email already taken: " + studentDetails.getEmail());
                });

        return this.studentRepository.update(studentDetails, id);
    }

    @Transactional
    public void deleteStudent(Long id) {
        log.info("Deleting student ID: {}", id);
        this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        this.studentRepository.deleteById(id);
    }
}