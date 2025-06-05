package com.heiyuk6.bilihub.infrastructure.repository.user;

import com.heiyuk6.bilihub.infrastructure.po.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA 自动生成常用 CRUD 方法
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    /**
     * 按用户名查找
     */
    UserEntity findByUsername(String username);
}

