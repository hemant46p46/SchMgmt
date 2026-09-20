package com.schmgmt.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndPrimaryKeyNot(
            String name,
            Long primaryKey
    );

    List<Role> findAllByIsActiveTrueOrderByNameAsc();
}