package com.example.springstudy.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50, unique = true)
    private String username;
    @Column(length = 100)
    private String password;
    @Column(nullable = false, length = 100)
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @CreationTimestamp
    private Timestamp createDate;
}
