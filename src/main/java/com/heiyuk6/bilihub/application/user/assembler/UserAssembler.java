package com.heiyuk6.bilihub.application.user.assembler;

import com.heiyuk6.bilihub.application.user.dto.UserResponseDTO;
import com.heiyuk6.bilihub.domain.user.entity.User;
import org.springframework.stereotype.Component;

/**
 * 将领域对象转换为返回给前端的 DTO
 */
@Component
public class UserAssembler {
    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setCreateTime(user.getCreateTime());
        dto.setUpdateTime(user.getUpdateTime());
        return dto;
    }
}
