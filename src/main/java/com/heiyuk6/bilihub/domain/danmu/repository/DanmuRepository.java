package com.heiyuk6.bilihub.domain.danmu.repository;

import com.heiyuk6.bilihub.domain.danmu.entity.Danmu;

import java.util.List;

/**
 * 弹幕仓储接口
 */
public interface DanmuRepository {

    /**
     * 保存新弹幕，保存后 danmu.id 会被回填
     */
    Danmu save(Danmu danmu);

    /**
     * 根据视频 ID 查询该视频所有弹幕（按 created_at 升序）
     */
    List<Danmu> findByVideoIdOrderByCreatedAtAsc(Long videoId);

    /**
     * 删除弹幕（按 ID）
     */
    void deleteById(Long id);
}
