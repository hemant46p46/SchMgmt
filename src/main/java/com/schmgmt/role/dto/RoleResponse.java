package com.schmgmt.role.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {

    private Long primaryKey;

    private String name;

    private String description;

    private boolean active;
}