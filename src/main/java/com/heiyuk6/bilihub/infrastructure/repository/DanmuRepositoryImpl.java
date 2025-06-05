package com.heiyuk6.bilihub.infrastructure.repository;

import com.heiyuk6.bilihub.domain.danmu.model.Danmu;
import com.heiyuk6.bilihub.domain.danmu.repository.DanmuRepository;
import com.heiyuk6.bilihub.domain.danmu.exception.DanmuDomainException;
import com.heiyuk6.bilihub.infrastructure.mapper.DanmuMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Danmu 仓储实现（MyBatis）
 */
@Repository
public class DanmuRepositoryImpl implements DanmuRepository {

    private final DanmuMapper danmuMapper;

    public DanmuRepositoryImpl(DanmuMapper danmuMapper) {
        this.danmuMapper = danmuMapper;
    }

    @Override
    public Danmu save(Danmu danmu) {
        int rows = danmuMapper.insert(danmu);
        if (rows != 1 || danmu.getId() == null) {
            throw new DanmuDomainException("弹幕保存失败");
        }
        return danmu;
    }

    @Override
    public List<Danmu> findByVideoIdOrderByCreatedAtAsc(Long videoId) {
        return danmuMapper.selectByVideoIdOrderByCreatedAtAsc(videoId);
    }

    @Override
    public void deleteById(Long id) {
        int rows = danmuMapper.deleteById(id);
        if (rows != 1) {
            throw new DanmuDomainException("弹幕删除失败，ID=" + id);
        }
    }
}
