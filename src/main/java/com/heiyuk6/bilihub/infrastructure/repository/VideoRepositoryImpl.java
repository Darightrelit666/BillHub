package com.heiyuk6.bilihub.infrastructure.repository;

import com.heiyuk6.bilihub.domain.video.entity.Video;
import com.heiyuk6.bilihub.domain.video.repository.VideoRepository;
import com.heiyuk6.bilihub.infrastructure.po.video.VideoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class VideoRepositoryImpl implements VideoRepository {
    private final VideoJpaRepository jpa;

    @Override
    public Video save(Video video) {
        VideoEntity e = toEntity(video);
        VideoEntity saved = jpa.save(e);
        return toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public Optional<Video> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public Page<Video> findAll(Pageable pageable) {
        Page<VideoEntity> page = jpa.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                        Sort.by(Sort.Direction.DESC, "createTime"))
        );
        return new PageImpl<>(
                page.getContent().stream().map(this::toDomain).collect(Collectors.toList()),
                pageable, page.getTotalElements()
        );
    }

    @Override
    public Page<Video> findByUploaderId(Long uploaderId, Pageable pageable) {
        Page<VideoEntity> page = jpa.findByUploaderIdOrderByCreateTimeDesc(
                uploaderId,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())
        );
        return new PageImpl<>(
                page.getContent().stream().map(this::toDomain).collect(Collectors.toList()),
                pageable, page.getTotalElements()
        );
    }

    private VideoEntity toEntity(Video v) {
        VideoEntity e = new VideoEntity();
        e.setId(v.getId());
        e.setUploaderId(v.getUploaderId());
        e.setTitle(v.getTitle());
        e.setDescription(v.getDescription());
        e.setStoragePath(v.getStoragePath());
        e.setThumbnailPath(v.getThumbnailPath());
        e.setDuration(v.getDuration());
        e.setSize(v.getSize());
        e.setStatus(v.getStatus());
        e.setViewCount(v.getViewCount());
        e.setCreateTime(v.getCreateTime());
        e.setUpdateTime(v.getUpdateTime());
        return e;
    }

    private Video toDomain(VideoEntity e) {
        Video v = Video.of(
                e.getId(), e.getUploaderId(), e.getTitle(), e.getDescription(),
                e.getStoragePath(), e.getThumbnailPath(), e.getDuration(), e.getSize(), e.getStatus()
        );
        while (v.getViewCount() < e.getViewCount()) v.incrementView();
        return v;
    }
}