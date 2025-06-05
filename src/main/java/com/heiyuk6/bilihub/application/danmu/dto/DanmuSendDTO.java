package com.heiyuk6.bilihub.application.danmu.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 前端发送弹幕时的 DTO
 */
public class DanmuSendDTO {

    @NotNull(message = "视频 ID 不能为空")
    private Long videoId;

    private Long userId; // 可为空或游客

    @NotBlank(message = "弹幕内容不能为空")
    private String content;

    @NotNull(message = "位置时间不能为空")
    private Double positionTime; // 秒，可以带小数

    private String color;        // 默认 "#FFFFFF"
    private Integer fontSize;    // px，默认 25
    private String type;         // scroll/top/bottom，默认 scroll

    // --------- Getter & Setter ---------

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
}
