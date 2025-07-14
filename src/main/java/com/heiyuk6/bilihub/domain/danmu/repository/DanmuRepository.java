package com.heiyuk6.bilihub.domain.danmu.repository;

import com.heiyuk6.bilihub.domain.danmu.entity.Danmu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 弹幕仓储接口，支持分页或全部查询
 */
public interface DanmuRepository {

    /**
     * 保存新弹幕
     */
    Danmu save(Danmu danmu);

    /**
     * 删除弹幕
     */
    void deleteById(Long id);

    /**
     * 分页查询某视频的弹幕，按创建时间升序
     */
    Page<Danmu> findByVideoId(Long videoId, Pageable pageable);

    /**
     * 查询某视频的所有弹幕，按创建时间升序
     */
    List<Danmu> findAllByVideoId(Long videoId);
}
