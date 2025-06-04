package com.heiyuk6.bilihub.application.user.dto;

import lombok.Data;

/**
 * 前端注册请求 DTO
 */
@Data
public class UserRegisterDTO {
    private String username;
    private String password; // 明文密码
    private String email;
}
