package com.heiyuk6.bilihub.infrastructure.po.danmu;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "danmu")
@Data
public class DanmuEntity {
    @Id
    private Long id;  // 由代码生成

    @Column(name = "video_id", nullable = false)
    private Long videoId;

    @Column(name = "user_id")
    private Long userId;

    @Column(length = 255, nullable = false)
    private String content;

    @Column(name = "position_time", nullable = false)
    private Double positionTime;

    @Column(length = 20, nullable = false)
    private String color;

    @Column(name = "font_size", nullable = false)
    private Integer fontSize;

    @Column(length = 20, nullable = false)
    private String type;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createTime;
}
