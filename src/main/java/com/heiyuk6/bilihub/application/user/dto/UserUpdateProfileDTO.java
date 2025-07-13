package com.heiyuk6.bilihub.application.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateProfileDTO {
    @NotBlank
    private String nickname;

    @Email
    private String email;

    private String avatarUrl;
}
