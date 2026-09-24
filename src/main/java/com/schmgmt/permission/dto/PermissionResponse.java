package com.schmgmt.permission.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {
    private Long primaryKey;

    private String name;

    private String description;

    private String module;

    private boolean active;
}
