package com.heiyuk6.bilihub.application.video.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoInfoDTO {
    private Long id;
    private Long uploaderId;
    private String title;
    private String description;
    private String storagePath;
    private String thumbnailPath;
    private Integer duration;
    private Long size;
    private String status;
    private Long viewCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
