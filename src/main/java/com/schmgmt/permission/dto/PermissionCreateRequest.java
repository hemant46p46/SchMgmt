package com.schmgmt.permission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionCreateRequest {

    @NotBlank(message = "Permission name is required")
    @Size(max = 100, message = "Permission name can not exceed 100 character")
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 character")
    private String description;

    @NotBlank(message = "Module is required")
    @Size(max = 50, message = "Permission name cannot exceed 50 character")
    private String module;
}
