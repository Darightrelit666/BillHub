package com.heiyuk6.bilihub.domain.danmu.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Danmu {
    private Long id;
    private Long videoId;
    private Long userId;         // 可为 null（游客弹幕）
    private String content;
    private Double positionTime; // 单位秒
    private String color;        // 默认 "#FFFFFF"
    private Integer fontSize;    // 默认 25
    private String type;         // scroll/top/bottom
    private LocalDateTime createTime;

    public static Danmu of(Long id,
                           Long videoId,
                           Long userId,
                           String content,
                           Double positionTime,
                           String color,
                           Integer fontSize,
                           String type) {
        Danmu d = new Danmu();
        d.id = id;
        d.videoId = videoId;
        d.userId = userId;
        d.content = content;
        d.positionTime = positionTime;
        d.color = color;
        d.fontSize = fontSize;
        d.type = type;
        d.createTime = LocalDateTime.now();
        return d;
    }
}
