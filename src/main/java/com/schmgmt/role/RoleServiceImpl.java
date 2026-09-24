package com.schmgmt.role;

import com.schmgmt.common.exception.DuplicateResourceException;
import com.schmgmt.common.exception.ResourceNotFoundException;
import com.schmgmt.role.dto.RoleCreateRequest;
import com.schmgmt.role.dto.RoleResponse;
import com.schmgmt.role.dto.RoleUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleResponse createRole(RoleCreateRequest request) {
        String roleName = request.getName().trim().toUpperCase();
        if (roleRepository.existsByNameIgnoreCase(roleName)) {
            throw new DuplicateResourceException("Role already exists with name: " + roleName);
        }
        Role role = Role.builder().name(roleName).description(request.getDescription()).isActive(true).build();
        Role savedRole = roleRepository.save(role);
        return mapToResponse(savedRole);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getActiveRoles() {
        return roleRepository.findAllByIsActiveTrueOrderByNameAsc().stream().map(this::mapToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponse getRoleById(Long primaryKey) {
        Role role = roleRepository.findById(primaryKey).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + primaryKey));
        return mapToResponse(role);
    }

    @Override
    public RoleResponse updateRole(Long primaryKey, RoleUpdateRequest request) {
        Role role = roleRepository.findById(primaryKey).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + primaryKey));
        String roleName = request.getName().trim().toUpperCase();
        if (roleRepository.existsByNameIgnoreCaseAndPrimaryKeyNot(roleName, primaryKey)) {
            throw new IllegalArgumentException("Role already exists with name: " + roleName);
        }
        role.setName(roleName);
        role.setDescription(request.getDescription());
        Role updatedRole = roleRepository.save(role);
        return mapToResponse(updatedRole);
    }

    @Override
    public RoleResponse updateRoleStatus(Long primaryKey, boolean active) {
        Role role = roleRepository.findById(primaryKey).orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + primaryKey));
        role.setActive(active);
        Role updatedRole = roleRepository.save(role);
        return mapToResponse(updatedRole);
    }

    private RoleResponse mapToResponse(Role role) {
        return RoleResponse.builder().primaryKey(role.getPrimaryKey()).name(role.getName())
                .description(role.getDescription()).active(role.isActive()).build();
    }
}