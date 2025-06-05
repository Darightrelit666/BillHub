package com.heiyuk6.bilihub.infrastructure.repository.user;

import com.heiyuk6.bilihub.domain.user.entity.User;
import com.heiyuk6.bilihub.domain.user.repository.UserRepository;
import com.heiyuk6.bilihub.infrastructure.po.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 将领域层的 UserRepository 接口，委托给 UserJpaRepository（JPA 实现）
 * 同时负责编码 UserEntity ↔ User（领域对象）的转换
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepo;

    @Override
    public User save(User user) {
        // 领域对象 → JPA 实体
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole());
        entity.setAvatarUrl(user.getAvatarUrl());
        entity.setCreateTime(user.getCreateTime());
        entity.setUpdateTime(user.getUpdateTime());

        // 保存到数据库
        UserEntity saved = jpaRepo.save(entity);

        // JPA 实体 → 领域对象
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepo.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        UserEntity entity = jpaRepo.findByUsername(username);
        return entity == null ? Optional.empty() : Optional.of(toDomain(entity));
    }

    /**
     * 把 UserEntity 转换成领域对象 User
     */
    private User toDomain(UserEntity entity) {
        User u = new User();
        try {
            java.lang.reflect.Field idField = User.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(u, entity.getId());

            java.lang.reflect.Field usernameField = User.class.getDeclaredField("username");
            usernameField.setAccessible(true);
            usernameField.set(u, entity.getUsername());

            java.lang.reflect.Field passwordField = User.class.getDeclaredField("passwordHash");
            passwordField.setAccessible(true);
            passwordField.set(u, entity.getPasswordHash());

            java.lang.reflect.Field emailField = User.class.getDeclaredField("email");
            emailField.setAccessible(true);
            emailField.set(u, entity.getEmail());

            java.lang.reflect.Field roleField = User.class.getDeclaredField("role");
            roleField.setAccessible(true);
            roleField.set(u, entity.getRole());

            java.lang.reflect.Field avatarField = User.class.getDeclaredField("avatarUrl");
            avatarField.setAccessible(true);
            avatarField.set(u, entity.getAvatarUrl());

            java.lang.reflect.Field createField = User.class.getDeclaredField("createTime");
            createField.setAccessible(true);
            createField.set(u, entity.getCreateTime());

            java.lang.reflect.Field updateField = User.class.getDeclaredField("updateTime");
            updateField.setAccessible(true);
            updateField.set(u, entity.getUpdateTime());
        } catch (Exception e) {
            throw new RuntimeException("UserEntity → User 转换失败", e);
        }
        return u;
    }
}

