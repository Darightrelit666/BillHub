package com.heiyuk6.bilihub.domain.user.repository;

import com.heiyuk6.bilihub.domain.user.entity.User;
import java.util.Optional;

/**
 * 领域层定义的仓储接口
 */
public interface UserRepository {
    /**
     * 保存（新增或更新）一个 User
     */
    User save(User user);

    /**
     * 按 ID 查询
     */
    Optional<User> findById(Long id);

    /**
     * 按用户名查询
     */
    Optional<User> findByUsername(String username);
}
