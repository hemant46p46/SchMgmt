package com.schmgmt.permission;

import com.schmgmt.common.exception.DuplicateResourceException;
import com.schmgmt.common.exception.ResourceNotFoundException;
import com.schmgmt.permission.dto.PermissionCreateRequest;
import com.schmgmt.permission.dto.PermissionResponse;
import com.schmgmt.permission.dto.PermissionUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public PermissionResponse createPermission(PermissionCreateRequest request) {
        String permissionName = request.getName().trim().toUpperCase();
        if (permissionRepository.existsByNameIgnoreCase(permissionName)) {
            throw new DuplicateResourceException("Permission already exists with name: " + permissionName);
        }

        Permission permission = Permission.builder()
            .name(permissionName)
            .description(request.getDescription())
            .module(request.getModule().trim().toUpperCase())
            .isActive(true)
            .build();

        Permission savedPermission =
                permissionRepository.save(permission);

        return mapToResponse(savedPermission);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> getActivePermissions() {
        return permissionRepository
            .findAllByIsActiveTrueOrderByModuleAscNameAsc()
            .stream()
            .map(this::mapToResponse)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponse getPermissionById(Long primaryKey) {
        Permission permission = permissionRepository
            .findById(primaryKey)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Permission not found with id: " + primaryKey)
            );
        return mapToResponse(permission);
    }

    @Override
    public PermissionResponse updatePermission(Long primaryKey, PermissionUpdateRequest request) {
        Permission permission = permissionRepository
            .findById(primaryKey)
            .orElseThrow(() ->
                new ResourceNotFoundException("Permission not found with id: " + primaryKey)
            );
        String permissionName = request.getName().trim().toUpperCase();

        if (permissionRepository.existsByNameIgnoreCaseAndPrimaryKeyNot(permissionName, primaryKey)) {
            throw new DuplicateResourceException("Permission already exists with name: " + permissionName);
        }
        permission.setName(permissionName);
        permission.setDescription(request.getDescription());
        permission.setModule(request.getModule().trim().toUpperCase());
        Permission updatedPermission = permissionRepository.save(permission);
        return mapToResponse(updatedPermission);
    }

    @Override
    public PermissionResponse updatePermissionStatus(Long primaryKey, boolean active) {
        Permission permission = permissionRepository
            .findById(primaryKey)
            .orElseThrow(() ->
                new ResourceNotFoundException("Permission not found with id: " + primaryKey)
            );
        permission.setActive(active);
        Permission updatedPermission = permissionRepository.save(permission);
        return mapToResponse(updatedPermission);
    }

    private PermissionResponse mapToResponse(Permission permission) {
        return PermissionResponse.builder()
            .primaryKey(permission.getPrimaryKey())
            .name(permission.getName())
            .description(permission.getDescription())
            .module(permission.getModule())
            .active(permission.isActive())
            .build();
    }
}