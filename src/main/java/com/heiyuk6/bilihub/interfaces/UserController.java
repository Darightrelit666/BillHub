package com.heiyuk6.bilihub.interfaces;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.heiyuk6.bilihub.application.user.dto.*;
import com.heiyuk6.bilihub.application.user.service.UserAppService;
import com.heiyuk6.bilihub.common.result.ApiResponse;
import com.heiyuk6.bilihub.common.result.PageResult;
import com.heiyuk6.bilihub.domain.user.exception.UserDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /** 查询自身信息 */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getCurrentUser() {
        UserResponseDTO user = userAppService.getCurrentUser();
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    /** 修改自身基本信息 */
    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateProfile(
            @Valid @RequestBody UserUpdateProfileDTO dto) {
        UserResponseDTO updated = userAppService.updateProfile(dto);
        return ResponseEntity.ok(ApiResponse.success(updated));
    }

    /** 修改密码 */
    @PostMapping("/me/password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @Valid @RequestBody UserChangePasswordDTO dto) {
        userAppService.changePassword(dto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /** （管理员）分页查询所有用户 */
    @SaCheckPermission("admin:users:list")
    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<UserResponseDTO>>> listUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        PageResult<UserResponseDTO> pageResult = userAppService.listUsers(page, size);
        return ResponseEntity.ok(ApiResponse.success(pageResult));
    }
}

