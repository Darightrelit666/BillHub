package com.heiyuk6.bilihub.infrastructure.repository;

import com.heiyuk6.bilihub.domain.danmu.entity.Danmu;
import com.heiyuk6.bilihub.domain.danmu.repository.DanmuRepository;
import com.heiyuk6.bilihub.infrastructure.po.danmu.DanmuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DanmuRepositoryImpl implements DanmuRepository {
    private final DanmuJpaRepository jpa;

    @Override
    public Danmu save(Danmu d) {
        DanmuEntity e = toEntity(d);
        DanmuEntity saved = jpa.save(e);
        return toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public Page<Danmu> findByVideoId(Long videoId, Pageable pageable) {
        Page<DanmuEntity> page = jpa.findByVideoIdOrderByCreateTimeAsc(videoId, pageable);
        return new PageImpl<>(
                page.getContent().stream().map(this::toDomain).collect(Collectors.toList()),
                pageable, page.getTotalElements()
        );
    }

    @Override
    public List<Danmu> findAllByVideoId(Long videoId) {
        return jpa.findByVideoIdOrderByCreateTimeAsc(videoId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private DanmuEntity toEntity(Danmu d) {
        DanmuEntity e = new DanmuEntity();
        e.setId(d.getId());
        e.setVideoId(d.getVideoId());
        e.setUserId(d.getUserId());
        e.setContent(d.getContent());
        e.setPositionTime(d.getPositionTime());
        e.setColor(d.getColor());
        e.setFontSize(d.getFontSize());
        e.setType(d.getType());
        e.setCreateTime(d.getCreateTime());
        return e;
    }

    private Danmu toDomain(DanmuEntity e) {
        return Danmu.of(
                e.getId(), e.getVideoId(), e.getUserId(), e.getContent(),
                e.getPositionTime(), e.getColor(), e.getFontSize(), e.getType()
        );
    }
}
