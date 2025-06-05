package com.heiyuk6.bilihub.domain.video.repository;

import com.heiyuk6.bilihub.domain.video.model.Video;

import java.util.List;
import java.util.Optional;

/**
 * 视频仓储接口
 */
public interface VideoRepository {

    /**
     * 保存新视频，保存后 video.id 会被回填
     */
    Video save(Video video);

    /**
     * 更新已存在的视频信息
     */
    Video update(Video video);

    /**
     * 删除视频（按 ID）
     */
    void deleteById(Long id);

    /**
     * 根据 ID 查询视频
     */
    Optional<Video> findById(Long id);

    /**
     * 根据上传者 ID 查询该用户所有视频（不分页）
     */
    List<Video> findByUploaderId(Long uploaderId);

    /**
     * 分页查询所有视频，按 created_at 降序
     */
    List<Video> findAllOrderByCreatedAtDesc(int offset, int limit);
}
