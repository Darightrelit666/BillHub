package com.heiyuk6.bilihub.application.user.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 返回给前端的用户基本信息 DTO（不含密码）
 */
@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String role;
    private String avatarUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
