package com.heiyuk6.bilihub.infrastructure.repository;

import com.heiyuk6.bilihub.infrastructure.po.danmu.DanmuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanmuJpaRepository extends JpaRepository<DanmuEntity, Long> {
    Page<DanmuEntity> findByVideoIdOrderByCreateTimeAsc(Long videoId, Pageable pageable);
    List<DanmuEntity> findByVideoIdOrderByCreateTimeAsc(Long videoId);
}