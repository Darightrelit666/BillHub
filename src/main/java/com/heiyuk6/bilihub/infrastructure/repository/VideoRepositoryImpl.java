package com.heiyuk6.bilihub.infrastructure.repository;

import com.heiyuk6.bilihub.domain.video.entity.Video;
import com.heiyuk6.bilihub.domain.video.repository.VideoRepository;
import com.heiyuk6.bilihub.domain.video.exception.VideoDomainException;
import com.heiyuk6.bilihub.infrastructure.mapper.VideoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Video 仓储实现（MyBatis）
 */
@Repository
public class VideoRepositoryImpl implements VideoRepository {

    private final VideoMapper videoMapper;

    public VideoRepositoryImpl(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Override
    public Video save(Video video) {
        int rows = videoMapper.insert(video);
        if (rows != 1 || video.getId() == null) {
            throw new VideoDomainException("视频保存失败");
        }
        return video;
    }

    @Override
    public Video update(Video video) {
        int rows = videoMapper.update(video);
        if (rows != 1) {
            throw new VideoDomainException("视频更新失败，ID=" + video.getId());
        }
        return video;
    }

    @Override
    public void deleteById(Long id) {
        int rows = videoMapper.deleteById(id);
        if (rows != 1) {
            throw new VideoDomainException("视频删除失败，ID=" + id);
        }
    }

    @Override
    public Optional<Video> findById(Long id) {
        Video v = videoMapper.selectById(id);
        return Optional.ofNullable(v);
    }

    @Override
    public List<Video> findByUploaderId(Long uploaderId) {
        return videoMapper.selectByUploaderId(uploaderId);
    }

    @Override
    public List<Video> findAllOrderByCreatedAtDesc(int offset, int limit) {
        return videoMapper.selectAllOrderByCreatedAtDesc(offset, limit);
    }
}
