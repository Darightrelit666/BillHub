package com.heiyuk6.bilihub.application.danmu.dto;

import java.time.LocalDateTime;

/**
 * 后端返回给前端的弹幕 DTO
 */
public class DanmuResponseDTO {

    private Long id;
    private Long videoId;
    private Long userId;
    private String content;
    private Double positionTime;
    private String color;
    private Integer fontSize;
    private String type;
    private LocalDateTime createdAt;

    // --------- Getter & Setter ---------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVideoId() { return videoId; }
    public void setVideoId(Long videoId) { this.videoId = videoId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Double getPositionTime() { return positionTime; }
    public void setPositionTime(Double positionTime) { this.positionTime = positionTime; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Integer getFontSize() { return fontSize; }
    public void setFontSize(Integer fontSize) { this.fontSize = fontSize; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
