package com.schmgmt.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.schmgmt.models.User;
import com.schmgmt.models.Role;
import com.schmgmt.enums.RoleName;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<User> findByUsername(String username) {
        List<User> results = this.entityManager
                .createNamedQuery("User.findByUsernameWithRoles", User.class)
                .setParameter("username", username)
                .getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public boolean existsByUsername(String username) {
        Long count = this.entityManager
                .createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    public Optional<Role> findRoleByName(RoleName roleName) {
        List<Role> results = this.entityManager
                .createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Transactional
    public User save(User user) {
        if (user.getUserId() == null) {
            this.entityManager.persist(user);
            return user;
        } else {
            return this.entityManager.merge(user);
        }
    }
}
