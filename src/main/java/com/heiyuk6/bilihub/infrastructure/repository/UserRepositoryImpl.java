package com.heiyuk6.bilihub.infrastructure.repository;

import com.heiyuk6.bilihub.domain.user.entity.User;
import com.heiyuk6.bilihub.domain.user.repository.UserRepository;
import com.heiyuk6.bilihub.infrastructure.po.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Optional<User> findByUsername(String username) {
        // jpaRepo.findByUsername 返回 UserEntity 或 null
        return Optional.ofNullable(jpaRepo.findByUsername(username))
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepo.findById(id).map(this::toDomain);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        // 调用 Spring Data JPA 的分页查询
        Page<UserEntity> pageEnt = jpaRepo.findAll(pageable);
        // 将 UserEntity 映射为领域对象 User，并保持分页信息
        return pageEnt.map(this::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity saved = jpaRepo.save(entity);
        return toDomain(saved);
    }

    // ... 省略其它方法 ...

    /**
     * 将领域对象转换为 JPA 实体
     */
    private UserEntity toEntity(User u) {
        UserEntity e = new UserEntity();
        e.setId(u.getId());
        e.setUsername(u.getUsername());
        e.setPasswordHash(u.getPasswordHash());
        e.setEmail(u.getEmail());
        e.setRole(u.getRole());
        e.setAvatarUrl(u.getAvatarUrl());
        e.setCreateTime(u.getCreateTime());
        e.setUpdateTime(u.getUpdateTime());
        return e;
    }

    /**
     * 将 JPA 实体转换为领域对象
     */
    private User toDomain(UserEntity e) {
        try {
            User u = User.class.getDeclaredConstructor().newInstance();
            var rf = User.class.getDeclaredField("id");
            rf.setAccessible(true); rf.set(u, e.getId());
            rf = User.class.getDeclaredField("username");
            rf.setAccessible(true); rf.set(u, e.getUsername());
            rf = User.class.getDeclaredField("passwordHash");
            rf.setAccessible(true); rf.set(u, e.getPasswordHash());
            rf = User.class.getDeclaredField("email");
            rf.setAccessible(true); rf.set(u, e.getEmail());
            rf = User.class.getDeclaredField("role");
            rf.setAccessible(true); rf.set(u, e.getRole());
            rf = User.class.getDeclaredField("avatarUrl");
            rf.setAccessible(true); rf.set(u, e.getAvatarUrl());
            rf = User.class.getDeclaredField("createTime");
            rf.setAccessible(true); rf.set(u, e.getCreateTime());
            rf = User.class.getDeclaredField("updateTime");
            rf.setAccessible(true); rf.set(u, e.getUpdateTime());
            return u;
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException("UserEntity → User 转换失败", ex);
        }
    }
}
