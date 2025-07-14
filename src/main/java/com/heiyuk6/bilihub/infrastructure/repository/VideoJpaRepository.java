package com.heiyuk6.bilihub.infrastructure.repository;

import com.heiyuk6.bilihub.infrastructure.po.video.VideoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoJpaRepository extends JpaRepository<VideoEntity, Long> {
    Page<VideoEntity> findByUploaderIdOrderByCreateTimeDesc(Long uploaderId, Pageable pageable);
}
