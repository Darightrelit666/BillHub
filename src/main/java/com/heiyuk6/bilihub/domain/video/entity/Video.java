package com.heiyuk6.bilihub.domain.video.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 领域模型：Video 对应数据库表 video
 */
public class Video {

    private Long id;
    private Long uploaderId;
    private String title;
    private String description;
    private String storagePath;
    private String thumbnailPath;
    private Integer duration;      // 秒
    private Long size;             // 字节
    private String status;         // PENDING, READY, FAILED, ...
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Video() { }

    public Video(Long id,
                 Long uploaderId,
                 String title,
                 String description,
                 String storagePath,
                 String thumbnailPath,
                 Integer duration,
                 Long size,
                 String status,
                 Long viewCount,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt) {
        this.id = id;
        this.uploaderId = uploaderId;
        this.title = title;
        this.description = description;
        this.storagePath = storagePath;
        this.thumbnailPath = thumbnailPath;
        this.duration = duration;
        this.size = size;
        this.status = status;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * 创建尚未持久化的新 Video 实例
     * createdAt、updatedAt 由应用层设置；viewCount 默认为 0；status 默认为 "PENDING"
     */
    public static Video newInstance(Long uploaderId,
                                    String title,
                                    String description,
                                    String storagePath,
                                    String thumbnailPath,
                                    Integer duration,
                                    Long size) {
        Video v = new Video();
        v.uploaderId = uploaderId;
        v.title = title;
        v.description = description;
        v.storagePath = storagePath;
        v.thumbnailPath = thumbnailPath;
        v.duration = duration;
        v.size = size;
        v.status = "PENDING";
        v.viewCount = 0L;
        v.createdAt = LocalDateTime.now();
        v.updatedAt = LocalDateTime.now();
        return v;
    }

    // ------------- Getter & Setter -------------

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

    /**
     * 增加播放量（并更新 updatedAt）
     */
    public void incrementViewCount() {
        if (this.viewCount == null) {
            this.viewCount = 1L;
        } else {
            this.viewCount++;
        }
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 更新视频状态（例如转码完成后设为 "READY"）
     */
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 更新视频的可修改属性（如标题、描述、缩略图等），忽略 id、uploaderId、createdAt
     */
    public void updateInfo(String title,
                           String description,
                           String thumbnailPath,
                           Integer duration,
                           Long size) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
        if (description != null) {
            this.description = description;
        }
        if (thumbnailPath != null) {
            this.thumbnailPath = thumbnailPath;
        }
        if (duration != null && duration > 0) {
            this.duration = duration;
        }
        if (size != null && size > 0) {
            this.size = size;
        }
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;
        return Objects.equals(id, video.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", uploaderId=" + uploaderId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", storagePath='" + storagePath + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", status='" + status + '\'' +
                ", viewCount=" + viewCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
