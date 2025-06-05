package com.heiyuk6.bilihub.application.video.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 前端上传视频时传入的 DTO
 */
public class VideoUploadDTO {

    @NotNull(message = "上传者 ID 不能为空")
    private Long uploaderId;

    @NotBlank(message = "标题不能为空")
    private String title;

    private String description;

    @NotBlank(message = "storagePath 不能为空")
    private String storagePath;

    private String thumbnailPath;

    @NotNull(message = "时长不能为空")
    private Integer duration;

    @NotNull(message = "文件大小不能为空")
    private Long size;

    // --------- Getter & Setter ------------

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
}
