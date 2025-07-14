package com.heiyuk6.bilihub.application.danmu.service;

import com.heiyuk6.bilihub.application.danmu.dto.DanmuInfoDTO;
import com.heiyuk6.bilihub.application.danmu.dto.DanmuPostDTO;
import com.heiyuk6.bilihub.domain.danmu.entity.Danmu;
import com.heiyuk6.bilihub.domain.danmu.repository.DanmuRepository;
import com.baidu.fsg.uid.UidGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DanmuAppService {

    private final DanmuRepository danmuRepo;
    private final UidGenerator uidGen;

    /** 发布弹幕 */
    public DanmuInfoDTO post(DanmuPostDTO dto) {
        long id = uidGen.getUID();
        Danmu d = Danmu.of(
                id,
                dto.getVideoId(),
                dto.getUserId(),
                dto.getContent(),
                dto.getPositionTime(),
                dto.getColor(),
                dto.getFontSize(),
                dto.getType()
        );
        Danmu saved = danmuRepo.save(d);
        DanmuInfoDTO out = new DanmuInfoDTO();
        out.setId(saved.getId());
        out.setVideoId(saved.getVideoId());
        out.setUserId(saved.getUserId());
        out.setContent(saved.getContent());
        out.setPositionTime(saved.getPositionTime());
        out.setColor(saved.getColor());
        out.setFontSize(saved.getFontSize());
        out.setType(saved.getType());
        out.setCreateTime(saved.getCreateTime());
        return out;
    }

    /** 删除弹幕 */
    public void delete(Long videoId, Long danmuId) {
        // 可先校验 videoId 与 弹幕 videoId 一致性
        danmuRepo.deleteById(danmuId);
    }

    /** 分页查询弹幕 */
    public Page<DanmuInfoDTO> listByVideo(Long videoId, int page, int size) {
        Pageable pg = PageRequest.of(page, size);
        return danmuRepo.findByVideoId(videoId, pg)
                .map(d -> {
                    DanmuInfoDTO out = new DanmuInfoDTO();
                    out.setId(d.getId());
                    out.setVideoId(d.getVideoId());
                    out.setUserId(d.getUserId());
                    out.setContent(d.getContent());
                    out.setPositionTime(d.getPositionTime());
                    out.setColor(d.getColor());
                    out.setFontSize(d.getFontSize());
                    out.setType(d.getType());
                    out.setCreateTime(d.getCreateTime());
                    return out;
                });
    }

    /** 查询所有弹幕（不分页） */
    public List<DanmuInfoDTO> listAllByVideo(Long videoId) {
        return danmuRepo.findAllByVideoId(videoId)
                .stream()
                .map(d -> {
                    DanmuInfoDTO out = new DanmuInfoDTO();
                    out.setId(d.getId());
                    out.setVideoId(d.getVideoId());
                    out.setUserId(d.getUserId());
                    out.setContent(d.getContent());
                    out.setPositionTime(d.getPositionTime());
                    out.setColor(d.getColor());
                    out.setFontSize(d.getFontSize());
                    out.setType(d.getType());
                    out.setCreateTime(d.getCreateTime());
                    return out;
                })
                .collect(Collectors.toList());
    }
}
