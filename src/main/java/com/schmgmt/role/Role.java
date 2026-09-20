package com.schmgmt.role;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_roles_name",
                        columnNames = "name"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long primaryKey;

    @Column(
            name = "name",
            nullable = false,
            length = 50
    )
    private String name;

    @Column(
            name = "description",
            length = 255
    )
    private String description;

    @Column(
            name = "is_active",
            nullable = false
    )
    @Builder.Default
    private boolean isActive = true;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}