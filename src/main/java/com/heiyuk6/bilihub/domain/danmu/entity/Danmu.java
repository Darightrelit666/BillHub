package com.heiyuk6.bilihub.domain.danmu.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 领域模型：Danmu 对应数据库表 danmu
 */
public class Danmu {

    private Long id;
    private Long videoId;
    private Long userId;           // 可为空，游客弹幕
    private String content;
    private Double positionTime;    // 小数秒
    private String color;           // 例如 "#FFFFFF"
    private Integer fontSize;       // px
    private String type;            // "scroll" / "top" / "bottom"
    private LocalDateTime createdAt;

    public Danmu() { }

    public Danmu(Long id,
                 Long videoId,
                 Long userId,
                 String content,
                 Double positionTime,
                 String color,
                 Integer fontSize,
                 String type,
                 LocalDateTime createdAt) {
        this.id = id;
        this.videoId = videoId;
        this.userId = userId;
        this.content = content;
        this.positionTime = positionTime;
        this.color = color;
        this.fontSize = fontSize;
        this.type = type;
        this.createdAt = createdAt;
    }

    /**
     * 创建新的 Danmu 实例（尚未持久化，无 id）
     */
    public static Danmu newInstance(Long videoId,
                                    Long userId,
                                    String content,
                                    Double positionTime,
                                    String color,
                                    Integer fontSize,
                                    String type) {
        Danmu d = new Danmu();
        d.videoId = videoId;
        d.userId = userId;
        d.content = content;
        d.positionTime = positionTime;
        d.color = (color != null ? color : "#FFFFFF");
        d.fontSize = (fontSize != null ? fontSize : 25);
        d.type = (type != null ? type : "scroll");
        d.createdAt = LocalDateTime.now();
        return d;
    }

    // ------------- Getter & Setter -------------

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Danmu danmu = (Danmu) o;
        return Objects.equals(id, danmu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Danmu{" +
                "id=" + id +
                ", videoId=" + videoId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", positionTime=" + positionTime +
                ", color='" + color + '\'' +
                ", fontSize=" + fontSize +
                ", type='" + type + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
