package com.heiyuk6.bilihub.interfaces;

import com.heiyuk6.bilihub.application.video.dto.VideoInfoDTO;
import com.heiyuk6.bilihub.application.video.dto.VideoUploadDTO;
import com.heiyuk6.bilihub.application.video.service.VideoAppService;
import com.heiyuk6.bilihub.application.danmu.dto.DanmuInfoDTO;
import com.heiyuk6.bilihub.application.danmu.dto.DanmuPostDTO;
import com.heiyuk6.bilihub.application.danmu.service.DanmuAppService;
import com.heiyuk6.bilihub.common.result.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/videos/{videoId}/danmu")
@RequiredArgsConstructor
public class DanmuController {

    private final DanmuAppService danmuAppService;

    /** 发布一条弹幕 */
    @PostMapping
    public ApiResponse<DanmuInfoDTO> post(
            @PathVariable Long videoId,
            @RequestBody DanmuPostDTO dto) {
        dto.setVideoId(videoId);
        DanmuInfoDTO sent = danmuAppService.post(dto);
        return ApiResponse.success(sent);
    }

    /** 删除一条弹幕 */
    @DeleteMapping("/{danmuId}")
    public ApiResponse<Void> delete(
            @PathVariable Long videoId,
            @PathVariable Long danmuId) {
        danmuAppService.delete(videoId, danmuId);
        return ApiResponse.success(null);
    }

    /** 分页拉取某视频的弹幕 */
    @GetMapping
    public ApiResponse<Page<DanmuInfoDTO>> list(
            @PathVariable Long videoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return ApiResponse.success(danmuAppService.listByVideo(videoId, page, size));
    }

    /** 查询某视频的所有弹幕（不分页） */
    @GetMapping("/all")
    public ApiResponse<List<DanmuInfoDTO>> listAll(@PathVariable Long videoId) {
        return ApiResponse.success(danmuAppService.listAllByVideo(videoId));
    }
}