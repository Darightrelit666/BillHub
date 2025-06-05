package com.heiyuk6.bilihub.application.danmu.assembler;

import com.heiyuk6.bilihub.application.danmu.dto.DanmuResponseDTO;
import com.heiyuk6.bilihub.application.danmu.dto.DanmuSendDTO;
import com.heiyuk6.bilihub.domain.danmu.model.Danmu;

public class DanmuAssembler {

    /**
     * 将 DanmuSendDTO 转成领域模型 Danmu
     */
    public static Danmu toDomain(DanmuSendDTO dto) {
        return Danmu.newInstance(
                dto.getVideoId(),
                dto.getUserId(),
                dto.getContent(),
                dto.getPositionTime(),
                dto.getColor(),
                dto.getFontSize(),
                dto.getType()
        );
    }

    /**
     * 将领域模型 Danmu 转成 DanmuResponseDTO
     */
    public static DanmuResponseDTO toResponseDTO(Danmu danmu) {
        DanmuResponseDTO vo = new DanmuResponseDTO();
        vo.setId(danmu.getId());
        vo.setVideoId(danmu.getVideoId());
        vo.setUserId(danmu.getUserId());
        vo.setContent(danmu.getContent());
        vo.setPositionTime(danmu.getPositionTime());
        vo.setColor(danmu.getColor());
        vo.setFontSize(danmu.getFontSize());
        vo.setType(danmu.getType());
        vo.setCreatedAt(danmu.getCreatedAt());
        return vo;
    }
}
