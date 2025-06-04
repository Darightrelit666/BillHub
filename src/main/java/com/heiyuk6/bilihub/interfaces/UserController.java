package com.heiyuk6.bilihub.interfaces;

import com.heiyuk6.bilihub.application.user.dto.*;
import com.heiyuk6.bilihub.application.user.service.UserAppService;
import com.heiyuk6.bilihub.common.result.ApiResponse;
import com.heiyuk6.bilihub.domain.user.exception.UserDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 对外暴露的用户 REST 接口
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserAppService userAppService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@RequestBody UserRegisterDTO dto) {
        UserResponseDTO user = userAppService.register(dto);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponseDTO>> login(@RequestBody UserLoginDTO dto) {
        UserLoginResponseDTO resp = userAppService.login(dto);
        return ResponseEntity.ok(ApiResponse.success(resp));
    }

    /**
     * 根据 ID 查询用户信息
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getById(@PathVariable Long id) {
        UserResponseDTO user = userAppService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    /**
     * 演示抛出领域异常如何被统一处理
     */
    @GetMapping("/error-demo")
    public void errorDemo() {
        throw new UserDomainException("这是一个演示领域异常");
    }
}

