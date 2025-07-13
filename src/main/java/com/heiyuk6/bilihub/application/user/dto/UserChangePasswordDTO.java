package com.heiyuk6.bilihub.application.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserChangePasswordDTO {
    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
