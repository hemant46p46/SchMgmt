package com.schmgmt.role;

import com.schmgmt.common.response.ApiResponse;
import com.schmgmt.role.dto.RoleCreateRequest;
import com.schmgmt.role.dto.RoleResponse;
import com.schmgmt.role.dto.RoleUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> createRole(@Valid @RequestBody RoleCreateRequest request) {
        RoleResponse role = roleService.createRole(request);
        ApiResponse<RoleResponse> response = ApiResponse.<RoleResponse>builder().success(true)
                .message("Role created successfully").data(role).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAllRoles() {
        List<RoleResponse> roles = roleService.getAllRoles();
        ApiResponse<List<RoleResponse>> response = ApiResponse.<List<RoleResponse>>builder().success(true)
                .message("Roles retrieved successfully").data(roles).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getActiveRoles() {
        List<RoleResponse> roles = roleService.getActiveRoles();
        ApiResponse<List<RoleResponse>> response = ApiResponse.<List<RoleResponse>>builder().success(true)
                .message("Active roles retrieved successfully").data(roles).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{primaryKey}")
    public ResponseEntity<ApiResponse<RoleResponse>> getRoleById(@PathVariable Long primaryKey) {
        RoleResponse role = roleService.getRoleById(primaryKey);
        ApiResponse<RoleResponse> response = ApiResponse.<RoleResponse>builder().success(true)
                .message("Role retrieved successfully").data(role).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{primaryKey}")
    public ResponseEntity<ApiResponse<RoleResponse>> updateRole(@PathVariable Long primaryKey, @Valid @RequestBody RoleUpdateRequest request) {
        RoleResponse role = roleService.updateRole(primaryKey, request);
        ApiResponse<RoleResponse> response = ApiResponse.<RoleResponse>builder().success(true)
                .message("Role updated successfully").data(role).build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{primaryKey}/status")
    public ResponseEntity<ApiResponse<RoleResponse>> updateRoleStatus(@PathVariable Long primaryKey, @RequestParam boolean active) {
        RoleResponse role = roleService.updateRoleStatus(primaryKey, active);
        ApiResponse<RoleResponse> response = ApiResponse.<RoleResponse>builder().success(true)
                .message(active ? "Role activated successfully" : "Role deactivated successfully").data(role).build();
        return ResponseEntity.ok(response);
    }
}