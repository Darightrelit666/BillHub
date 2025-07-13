package com.heiyuk6.bilihub.infrastructure.po.user;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 持久化对象：UserEntity
 * 与数据库表 `user` 一一映射
 */
@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @Column(length = 100)
    private String email;

    @Column(length = 20, nullable = false)
    private String role;

    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}

