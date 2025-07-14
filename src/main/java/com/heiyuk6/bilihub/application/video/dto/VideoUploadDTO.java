package com.heiyuk6.bilihub.application.video.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VideoUploadDTO {
    private Long uploaderId;
    private String title;
    private String description;
    private MultipartFile file;
}
