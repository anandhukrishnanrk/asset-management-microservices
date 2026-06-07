package com.cis.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users") // 'user' is often a reserved keyword
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;
}
