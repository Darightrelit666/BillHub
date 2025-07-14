package com.heiyuk6.bilihub.infrastructure.po.video;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "video")
@Data
public class VideoEntity {
    @Id
    private Long id;  // 由代码生成，不自增

    @Column(name = "uploader_id", nullable = false)
    private Long uploaderId;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "storage_path", length = 500, nullable = false)
    private String storagePath;

    @Column(name = "thumbnail_path", length = 500)
    private String thumbnailPath;

    @Column
    private Integer duration;

    @Column
    private Long size;

    @Column(length = 50, nullable = false)
    private String status;

    @Column(name = "view_count", nullable = false)
    private Long viewCount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updateTime;
}
