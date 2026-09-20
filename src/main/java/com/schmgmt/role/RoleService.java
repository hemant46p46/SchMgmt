package com.schmgmt.role;

import com.schmgmt.role.dto.RoleCreateRequest;
import com.schmgmt.role.dto.RoleResponse;
import com.schmgmt.role.dto.RoleUpdateRequest;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleCreateRequest request);

    List<RoleResponse> getAllRoles();

    List<RoleResponse> getActiveRoles();

    RoleResponse getRoleById(Long primaryKey);

    RoleResponse updateRole(Long primaryKey, RoleUpdateRequest request);

    RoleResponse updateRoleStatus(Long primaryKey, boolean active);
}