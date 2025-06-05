package com.heiyuk6.bilihub.application.video.assembler;

import com.heiyuk6.bilihub.application.video.dto.VideoUploadDTO;
import com.heiyuk6.bilihub.application.video.dto.VideoResponseDTO;
import com.heiyuk6.bilihub.domain.video.model.Video;

public class VideoAssembler {

    /**
     * 将 VideoUploadDTO 转为领域模型 Video
     */
    public static Video toDomain(VideoUploadDTO dto) {
        Video v = Video.newInstance(
                dto.getUploaderId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getStoragePath(),
                dto.getThumbnailPath(),
                dto.getDuration(),
                dto.getSize()
        );
        return v;
    }

    /**
     * 将领域模型 Video 转为 VideoResponseDTO
     */
    public static VideoResponseDTO toResponseDTO(Video video) {
        VideoResponseDTO vo = new VideoResponseDTO();
        vo.setId(video.getId());
        vo.setUploaderId(video.getUploaderId());
        vo.setTitle(video.getTitle());
        vo.setDescription(video.getDescription());
        vo.setStoragePath(video.getStoragePath());
        vo.setThumbnailPath(video.getThumbnailPath());
        vo.setDuration(video.getDuration());
        vo.setSize(video.getSize());
        vo.setStatus(video.getStatus());
        vo.setViewCount(video.getViewCount());
        vo.setCreatedAt(video.getCreatedAt());
        vo.setUpdatedAt(video.getUpdatedAt());
        return vo;
    }
}
