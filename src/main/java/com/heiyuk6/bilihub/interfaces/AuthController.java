package com.heiyuk6.bilihub.interfaces;

import com.heiyuk6.bilihub.common.result.ApiResponse;
import com.heiyuk6.bilihub.application.user.dto.UserLoginDTO;
import com.heiyuk6.bilihub.application.user.service.UserAppService;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAppService userAppService;
    public AuthController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    /** 本地账号登录 */
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody UserLoginDTO dto) {
        Long userId = userAppService.login(dto).getUser().getId();         // 自己的逻辑：验证用户名+密码
        StpUtil.login(userId);                           // Sa-Token 登录，会话建立
        return ApiResponse.success(StpUtil.getTokenValue());  // 返回 token 给前端
    }

    /** 登出 */
    @PostMapping("/logout")
    public ApiResponse<Void> logout() {
        StpUtil.logout();
        return ApiResponse.success(null);
    }

    /** 会话校验：是否已登录 */
    @GetMapping("/check")
    public ApiResponse<Boolean> check() {
        return ApiResponse.success(StpUtil.isLogin());
    }
}

