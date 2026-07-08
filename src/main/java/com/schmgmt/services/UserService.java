package com.schmgmt.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schmgmt.enums.RoleName;
import com.schmgmt.models.*;
import com.schmgmt.repositories.UserRepository;
// If using Spring Security, import: org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    // private final PasswordEncoder passwordEncoder; 

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createSystemUser(String username, String rawPassword, RoleName designation) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("System identity conflict: Username is already registered.");
        }

        User newUser = new User();
        newUser.setUsername(username);
        
        // Replace this fallback with passwordEncoder.encode(rawPassword) when security is configured
        newUser.setPassword(rawPassword); 
        newUser.setActive(true);

        Role assignedRole = userRepository.findRoleByName(designation)
                .orElseThrow(() -> new IllegalStateException("Database invariant violation: Targeted role structure is missing."));
        
        newUser.getRoles().add(assignedRole);
        return userRepository.save(newUser);
    }
}
