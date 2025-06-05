package com.heiyuk6.bilihub.application.video.service;

import com.baidu.fsg.uid.UidGenerator;
import com.heiyuk6.bilihub.application.video.assembler.VideoAssembler;
import com.heiyuk6.bilihub.application.video.dto.VideoResponseDTO;
import com.heiyuk6.bilihub.application.video.dto.VideoUploadDTO;
import com.heiyuk6.bilihub.domain.video.exception.VideoDomainException;
import com.heiyuk6.bilihub.domain.video.entity.Video;
import com.heiyuk6.bilihub.domain.video.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 视频应用服务
 */
@Service
public class VideoAppService {

    private final VideoRepository videoRepository;
    private final UidGenerator uidGenerator;

    public VideoAppService(UidGenerator uidGenerator,VideoRepository videoRepository) {
        this.uidGenerator= uidGenerator;
        this.videoRepository = videoRepository;
    }

    /**
     * 上传视频
     */
    public VideoResponseDTO uploadVideo(VideoUploadDTO dto) {
        // 1. 先从 UidGenerator 拿到一个全局唯一的 long
        Long newId = uidGenerator.getUID();

        // 2. 用 newId + 其它上传信息创建领域模型，注意这里手动给 id 赋值
        Video video = Video.newInstance(
                dto.getUploaderId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getStoragePath(),
                dto.getThumbnailPath(),
                dto.getDuration(),
                dto.getSize()
        );
        video.setId(newId);   // 注意：你的领域类里有 setId(Long id) 方法

        // 3. 把领域模型转换成持久化实体（VideoEntity），再保存
        Video entity = new Video(
                video.getId(),
                video.getUploaderId(),
                video.getTitle(),
                video.getDescription(),
                video.getStoragePath(),
                video.getThumbnailPath(),
                video.getDuration(),
                video.getSize(),
                video.getStatus(),
                video.getViewCount(),
                video.getCreatedAt(),
                video.getUpdatedAt()
        );
        Video saved = videoRepository.save(entity);

        // 4. 把持久化后（或者直接用领域模型）映射为 ResponseDTO 并返回
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
