package com.schmgmt.config;

import com.schmgmt.enums.RoleName;
import com.schmgmt.models.*;
import com.schmgmt.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DatabaseTestRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DatabaseTestRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("====== STARTING MANUAL DATABASE SCHEMGMT TEST ======");

        try {
            // 1. Create and persist a Role
            Role studentRole = new Role();
            studentRole.setRoleName(RoleName.ROLE_STUDENT);
            entityManager.persist(studentRole);
            System.out.println("[SUCCESS] Role saved with ID: " + studentRole.getRoleId());

            // 2. Create and save a User using UserRepository
            User user = new User();
            user.setUsername("teststudent2027");
            user.setPassword("password123");
            user.setActive(true);
            user.getRoles().add(studentRole);
            
            userRepository.save(user);
            System.out.println("[SUCCESS] User saved with ID: " + user.getUserId());

            entityManager.flush();

            // 3. Test custom NamedQuery execution
            Optional<User> fetchedUserOpt = userRepository.findByUsername("teststudent2027");
            if (fetchedUserOpt.isPresent()) {
                User fetchedUser = fetchedUserOpt.get();
                System.out.println("[SUCCESS] NamedQuery worked! Fetched Username: " + fetchedUser.getUsername());
                System.out.println("[SUCCESS] Fetched User Roles count: " + fetchedUser.getRoles().size());
                
                // 4. Test Student 1:1 Relationship
                Student student = new Student();
                student.setUser(fetchedUser); // Links student profile to authentication account

                student.setFirstName("John");
                student.setLastName("Doe");
                student.setEmail("johndoe@college.edu");
                student.setEnrollmentDate(java.time.LocalDate.now()); 

                entityManager.persist(student);
                entityManager.flush();
                System.out.println("[SUCCESS] Student profile mapped 1:1 with User. Student ID: " + student.getStudentId());
            } else {
                System.err.println("[FAILURE] NamedQuery failed to fetch the user!");
            }

        } catch (Exception e) {
            System.err.println("[ERROR] Database transaction failed due to schema configuration errors!");
            e.printStackTrace();
        }

        System.out.println("====== DATABASE SCHEMGMT TEST COMPLETED ======");
    }
}
