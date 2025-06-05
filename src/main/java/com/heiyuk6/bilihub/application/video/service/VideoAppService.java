package com.heiyuk6.bilihub.application.video.service;

import com.heiyuk6.bilihub.application.video.assembler.VideoAssembler;
import com.heiyuk6.bilihub.application.video.dto.VideoResponseDTO;
import com.heiyuk6.bilihub.application.video.dto.VideoUploadDTO;
import com.heiyuk6.bilihub.domain.video.exception.VideoDomainException;
import com.heiyuk6.bilihub.domain.video.model.Video;
import com.heiyuk6.bilihub.domain.video.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 视频应用服务
 */
@Service
public class VideoAppService {

    private final VideoRepository videoRepository;

    public VideoAppService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    /**
     * 上传视频
     */
    public VideoResponseDTO uploadVideo(VideoUploadDTO dto) {
        Video video = VideoAssembler.toDomain(dto);
        Video saved = videoRepository.save(video);
        return VideoAssembler.toResponseDTO(saved);
    }

    /**
     * 更新视频信息（除 id, uploaderId, createdAt 外）
     */
    public VideoResponseDTO updateVideo(Long id,
                                        String title,
                                        String description,
                                        String thumbnailPath,
                                        Integer duration,
                                        Long size) {
        Video existing = videoRepository.findById(id)
                .orElseThrow(() -> new VideoDomainException("视频不存在，ID=" + id));
        existing.updateInfo(title, description, thumbnailPath, duration, size);
        Video updated = videoRepository.update(existing);
        return VideoAssembler.toResponseDTO(updated);
    }

    /**
     * 更新视频状态
     */
    public void updateStatus(Long id, String newStatus) {
        Video existing = videoRepository.findById(id)
                .orElseThrow(() -> new VideoDomainException("视频不存在，ID=" + id));
        existing.updateStatus(newStatus);
        videoRepository.update(existing);
    }

    /**
     * 删除视频
     */
    public void deleteVideo(Long id) {
        if (!videoRepository.findById(id).isPresent()) {
            throw new VideoDomainException("视频不存在，ID=" + id);
        }
        videoRepository.deleteById(id);
    }

    /**
     * 根据 ID 查询视频
     */
    public VideoResponseDTO getVideoById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new VideoDomainException("视频不存在，ID=" + id));
        return VideoAssembler.toResponseDTO(video);
    }

    /**
     * 根据上传者 ID 查询该用户所有视频
     */
    public List<VideoResponseDTO> listByUploader(Long uploaderId) {
        List<Video> list = videoRepository.findByUploaderId(uploaderId);
        return list.stream()
                .map(VideoAssembler::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * 分页查询所有视频
     */
    public List<VideoResponseDTO> listAll(int offset, int limit) {
        List<Video> list = videoRepository.findAllOrderByCreatedAtDesc(offset, limit);
        return list.stream()
                .map(VideoAssembler::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * 增加视频播放量
     */
    public void incrementViewCount(Long id) {
        Video existing = videoRepository.findById(id)
                .orElseThrow(() -> new VideoDomainException("视频不存在，ID=" + id));
        existing.incrementViewCount();
        videoRepository.update(existing);
    }
}
