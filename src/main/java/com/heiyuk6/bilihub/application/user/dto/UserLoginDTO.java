package com.heiyuk6.bilihub.application.user.dto;

import lombok.Data;

/**
 * 前端登录请求 DTO
 */
@Data
public class UserLoginDTO {
    private String username;
    private String password; // 明文密码
}
