package com.heiyuk6.bilihub.domain.user.entity;

import java.time.LocalDateTime;

import com.heiyuk6.bilihub.domain.user.exception.UserDomainException;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 领域实体：User
 * - 不包含任何持久化注解，纯业务逻辑
 */
@NoArgsConstructor
@Getter
public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private String email;
    private String role;
    private String avatarUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 用户注册的静态工厂方法
     */
    public static User register(Long Id,String username, String passwordHash, String email) {
        if (username == null || username.isEmpty()) {
            throw new UserDomainException("用户名不能为空");
        }
        if (passwordHash == null || passwordHash.isEmpty()) {
            throw new UserDomainException("密码不能为空");
        }
        User u = new User();
        u.id = Id;
        u.username = username;
        u.passwordHash = passwordHash;
        u.email = email;
        u.role = "USER";  // 默认普通用户
        u.createTime = LocalDateTime.now();
        u.updateTime = LocalDateTime.now();
        return u;
    }

    /**
     * 修改密码
     */
    public void changePassword(String newPasswordHash) {
        if (newPasswordHash == null || newPasswordHash.isEmpty()) {
            throw new UserDomainException("新密码不能为空");
        }
        this.passwordHash = newPasswordHash;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 修改头像 URL
     */
    public void changeAvatar(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        this.updateTime = LocalDateTime.now();
    }
}
