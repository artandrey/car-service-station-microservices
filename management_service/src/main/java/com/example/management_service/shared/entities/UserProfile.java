package com.example.management_service.shared.entities;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Table(name = "user_profiles")
@AllArgsConstructor
public class UserProfile extends BaseEntity {
    private Long userId;
    private String firstName;
    private String lastName;
    private String patronymic;
}
