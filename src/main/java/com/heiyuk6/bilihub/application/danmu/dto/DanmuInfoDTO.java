package com.heiyuk6.bilihub.application.danmu.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DanmuInfoDTO {
    private Long id;
    private Long videoId;
    private Long userId;
    private String content;
    private Double positionTime;
    private String color;
    private Integer fontSize;
    private String type;
    private LocalDateTime createTime;
}
