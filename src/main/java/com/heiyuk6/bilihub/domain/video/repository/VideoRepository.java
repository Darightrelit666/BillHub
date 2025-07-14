package com.heiyuk6.bilihub.domain.video.repository;

import com.heiyuk6.bilihub.domain.video.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 视频仓储接口，支持分页查询
 */
public interface VideoRepository {

    /**
     * 保存新视频或更新已有视频
     * @param video 领域对象
     * @return 保存后的领域对象（包含回填 ID 等）
     */
    Video save(Video video);

    /**
     * 根据 ID 删除视频
     */
    void deleteById(Long id);

    /**
     * 根据 ID 查询视频
     */
    Optional<Video> findById(Long id);

    /**
     * 分页查询所有视频，按创建时间降序
     */
    Page<Video> findAll(Pageable pageable);

    /**
     * 分页查询某上传者的视频，按创建时间降序
     */
    Page<Video> findByUploaderId(Long uploaderId, Pageable pageable);
}
