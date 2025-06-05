package com.heiyuk6.bilihub.application.danmu.service;

import com.heiyuk6.bilihub.application.danmu.assembler.DanmuAssembler;
import com.heiyuk6.bilihub.application.danmu.dto.DanmuResponseDTO;
import com.heiyuk6.bilihub.application.danmu.dto.DanmuSendDTO;
import com.heiyuk6.bilihub.domain.danmu.exception.DanmuDomainException;
import com.heiyuk6.bilihub.domain.danmu.model.Danmu;
import com.heiyuk6.bilihub.domain.danmu.repository.DanmuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 弹幕应用服务
 */
@Service
public class DanmuAppService {

    private final DanmuRepository danmuRepository;

    public DanmuAppService(DanmuRepository danmuRepository) {
        this.danmuRepository = danmuRepository;
    }

    /**
     * 发送弹幕
     */
    public DanmuResponseDTO sendDanmu(DanmuSendDTO dto) {
        Danmu danmu = DanmuAssembler.toDomain(dto);
        Danmu saved = danmuRepository.save(danmu);
        return DanmuAssembler.toResponseDTO(saved);
    }

    /**
     * 查询某个视频的所有弹幕（按 createdAt 升序）
     */
    public List<DanmuResponseDTO> listByVideoId(Long videoId) {
        List<Danmu> list = danmuRepository.findByVideoIdOrderByCreatedAtAsc(videoId);
        return list.stream()
                .map(DanmuAssembler::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * 删除弹幕
     */
    public void deleteDanmu(Long id) {
        // 可校验是否存在
        danmuRepository.deleteById(id);
    }
}
