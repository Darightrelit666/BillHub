package com.heiyuk6.bilihub.interfaces;

import com.heiyuk6.bilihub.application.danmu.dto.DanmuResponseDTO;
import com.heiyuk6.bilihub.application.danmu.dto.DanmuSendDTO;
import com.heiyuk6.bilihub.application.danmu.service.DanmuAppService;
import com.heiyuk6.bilihub.common.result.ApiResponse;
import com.heiyuk6.bilihub.domain.danmu.exception.DanmuDomainException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 弹幕接口
 */
@RestController
@RequestMapping("/api/danmu")
public class DanmuController {

    private final DanmuAppService danmuAppService;

    public DanmuController(DanmuAppService danmuAppService) {
        this.danmuAppService = danmuAppService;
    }

    /**
     * 发送弹幕
     */
    @PostMapping("/send")
    public ApiResponse<DanmuResponseDTO> send(@Valid @RequestBody DanmuSendDTO dto) {
        try {
            DanmuResponseDTO vo = danmuAppService.sendDanmu(dto);
            return ApiResponse.success(vo);
        } catch (DanmuDomainException ex) {
            return ApiResponse.fail(400, ex.getMessage());
        } catch (Exception ex) {
            return ApiResponse.fail(500, "发送弹幕异常");
        }
    }

    /**
     * 查询某视频的弹幕列表
     */
    @GetMapping("/video/{videoId}")
    public ApiResponse<List<DanmuResponseDTO>> listByVideo(@PathVariable("videoId") Long videoId) {
        try {
            List<DanmuResponseDTO> list = danmuAppService.listByVideoId(videoId);
            return ApiResponse.success(list);
        } catch (Exception ex) {
            return ApiResponse.fail(500, "查询弹幕列表异常");
        }
    }

    /**
     * 删除弹幕
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") Long id) {
        try {
            danmuAppService.deleteDanmu(id);
            return ApiResponse.success(null);
        } catch (DanmuDomainException ex) {
            return ApiResponse.fail(400, ex.getMessage());
        } catch (Exception ex) {
            return ApiResponse.fail(500, "删除弹幕异常");
        }
    }
}
