package com.heiyuk6.bilihub.domain.video.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Video {
    private Long id;
    private Long uploaderId;
    private String title;
    private String description;
    private String storagePath;    // 对应 DDL 中 storage_path
    private String thumbnailPath;  // 对应 DDL 中 thumbnail_path
    private Integer duration;      // 单位秒
    private Long size;             // 字节数
    private String status;         // PENDING, READY, FAILED
    private Long viewCount;        // 播放量
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /** 静态工厂，所有字段由外部提供 */
    public static Video of(Long id,
                           Long uploaderId,
                           String title,
                           String description,
                           String storagePath,
                           String thumbnailPath,
                           Integer duration,
                           Long size,
                           String status) {
        Video v = new Video();
        v.id = id;
        v.uploaderId = uploaderId;
        v.title = title;
        v.description = description;
        v.storagePath = storagePath;
        v.thumbnailPath = thumbnailPath;
        v.duration = duration;
        v.size = size;
        v.status = status;
        v.viewCount = 0L;
        v.createTime = LocalDateTime.now();
        v.updateTime = LocalDateTime.now();
        return v;
    }

    public void updateMetadata(String title, String description) {
        this.title = title;
        this.description = description;
        this.updateTime = LocalDateTime.now();
    }

    public void markReady(Long size, Integer duration) {
        this.status = "READY";
        this.size = size;
        this.duration = duration;
        this.updateTime = LocalDateTime.now();
    }

    public void incrementView() {
        this.viewCount++;
    }

}
