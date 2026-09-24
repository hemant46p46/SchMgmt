package com.schmgmt.permission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndPrimaryKeyNot(String name, Long primaryKey);

    List<Permission> findAllByIsActiveTrueOrderByModuleAscNameAsc();
}
