package com.heiyuk6.bilihub.application.user.dto;

import lombok.Data;

/**
 * 登录成功后返回给前端的 DTO，包含 Token 与用户信息
 */
@Data
public class UserLoginResponseDTO {
    private String token;          // 模拟的登录凭证（后续可用 JWT 替换）
    private UserResponseDTO user;  // 登录用户的基本信息
}
