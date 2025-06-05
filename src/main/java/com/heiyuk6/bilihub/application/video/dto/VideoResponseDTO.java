package com.heiyuk6.bilihub.application.video.dto;

import java.time.LocalDateTime;

/**
 * 后端返回给前端的视频信息 DTO
 */
public class VideoResponseDTO {

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // --------- Getter & Setter ------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUploaderId() { return uploaderId; }
    public void setUploaderId(Long uploaderId) { this.uploaderId = uploaderId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStoragePath() { return storagePath; }
    public void setStoragePath(String storagePath) { this.storagePath = storagePath; }

    public String getThumbnailPath() { return thumbnailPath; }
    public void setThumbnailPath(String thumbnailPath) { this.thumbnailPath = thumbnailPath; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getViewCount() { return viewCount; }
    public void setViewCount(Long viewCount) { this.viewCount = viewCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
