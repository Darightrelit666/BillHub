package com.heiyuk6.bilihub.application.user.service;

import com.baidu.fsg.uid.UidGenerator;
import com.heiyuk6.bilihub.application.user.assembler.UserAssembler;
import com.heiyuk6.bilihub.application.user.dto.*;
import com.heiyuk6.bilihub.domain.user.exception.UserDomainException;
import com.heiyuk6.bilihub.domain.user.entity.User;
import com.heiyuk6.bilihub.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAppService {
    private final UserRepository userRepository;
    private final UserAssembler userAssembler;
    private final UidGenerator uidGenerator;

    // BCrypt 加密器；可考虑提到配置里单例注入
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 用户注册
     */
    public UserResponseDTO register(UserRegisterDTO dto) {
        // 1. 检查用户名是否已存在
        Optional<User> exist = userRepository.findByUsername(dto.getUsername());
        if (exist.isPresent()) {
            throw new UserDomainException("用户名已存在");
        }

        // 2. 对明文密码做 BCrypt 加密
        String hashed = passwordEncoder.encode(dto.getPassword());
        // 3. 生成全局唯一 ID
        long newId = uidGenerator.getUID();
        // 4. 创建领域对象
        User newUser = User.register(newId,dto.getUsername(), hashed, dto.getEmail());

        // 5. 持久化
        User saved = userRepository.save(newUser);

        // 6. 转换为 DTO 返回
        return userAssembler.toResponseDTO(saved);
    }

    /**
     * 用户登录
     */
    public UserLoginResponseDTO login(UserLoginDTO dto) {
        // 1. 按用户名查找
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UserDomainException("用户名或密码错误"));

        // 2. 校验密码
        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new UserDomainException("用户名或密码错误");
        }

        // 3. 登录成功后生成一个模拟的 Token（实际可用 JWT）
        String token = UUID.randomUUID().toString();

        // 4. 组装响应 DTO
        UserLoginResponseDTO resp = new UserLoginResponseDTO();
        resp.setToken(token);
        resp.setUser(userAssembler.toResponseDTO(user));
        return resp;
    }

    /**
     * 根据 ID 获取用户信息
     */
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDomainException("用户不存在"));
        return userAssembler.toResponseDTO(user);
    }
}
