package com.heiyuk6.bilihub.application.danmu.dto;

import lombok.Data;

@Data
public class DanmuPostDTO {
    private Long videoId;
    private Long userId;
    private String content;
    private Double positionTime;
    private String color;
    private Integer fontSize;
    private String type;
}
