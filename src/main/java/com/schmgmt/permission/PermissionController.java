package com.schmgmt.permission;

import com.schmgmt.common.response.ApiResponse;
import com.schmgmt.permission.dto.PermissionCreateRequest;
import com.schmgmt.permission.dto.PermissionResponse;
import com.schmgmt.permission.dto.PermissionUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionResponse>> createPermission(@Valid @RequestBody PermissionCreateRequest request) {
        PermissionResponse permission = permissionService.createPermission(request);
        ApiResponse<PermissionResponse> response = ApiResponse.<PermissionResponse>builder().success(true).message("Permission created successfully")
                .data(permission).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermissionResponse>>> getAllPermissions() {
        List<PermissionResponse> permissions = permissionService.getAllPermissions();
        ApiResponse<List<PermissionResponse>> response = ApiResponse.<List<PermissionResponse>>builder().success(true).message("Permissions retrieved successfully")
                .data(permissions).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<PermissionResponse>>> getActivePermissions() {
        List<PermissionResponse> permissions = permissionService.getActivePermissions();
        ApiResponse<List<PermissionResponse>> response = ApiResponse.<List<PermissionResponse>>builder().success(true).message("Active permissions retrieved successfully")
                .data(permissions).build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{primaryKey}")
    public ResponseEntity<ApiResponse<PermissionResponse>> getPermissionById(@PathVariable Long primaryKey) {
        PermissionResponse permission = permissionService.getPermissionById(primaryKey);
        ApiResponse<PermissionResponse> response = ApiResponse.<PermissionResponse>builder().success(true).message("Permission retrieved successfully")
                .data(permission).build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{primaryKey}")
    public ResponseEntity<ApiResponse<PermissionResponse>> updatePermission(@PathVariable Long primaryKey, @Valid @RequestBody PermissionUpdateRequest request) {
        PermissionResponse permission = permissionService.updatePermission(primaryKey, request);
        ApiResponse<PermissionResponse> response = ApiResponse.<PermissionResponse>builder().success(true).message("Permission updated successfully")
                .data(permission).build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{primaryKey}/status")
    public ResponseEntity<ApiResponse<PermissionResponse>> updatePermissionStatus(@PathVariable Long primaryKey, @RequestParam boolean active) {
        PermissionResponse permission = permissionService.updatePermissionStatus(primaryKey, active);
        ApiResponse<PermissionResponse> response = ApiResponse.<PermissionResponse>builder().success(true)
                .message(active ? "Permission activated successfully" : "Permission deactivated successfully").data(permission).build();

        return ResponseEntity.ok(response);
    }
}