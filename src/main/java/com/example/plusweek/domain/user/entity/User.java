package com.example.plusweek.domain.user.entity;

import com.example.plusweek.global.timestamp.TimeStamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Role role = Role.ROLE_USER;

    @Builder
    private User(Long id, String username, String password, Role role) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
