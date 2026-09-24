package com.schmgmt.permission;

import com.schmgmt.permission.dto.PermissionCreateRequest;
import com.schmgmt.permission.dto.PermissionResponse;
import com.schmgmt.permission.dto.PermissionUpdateRequest;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionCreateRequest request);

    List<PermissionResponse> getAllPermissions();

    List<PermissionResponse> getActivePermissions();

    PermissionResponse getPermissionById(Long primaryKey);

    PermissionResponse updatePermission(Long primaryKey, PermissionUpdateRequest request);

    PermissionResponse updatePermissionStatus(Long primaryKey, boolean active);
}
