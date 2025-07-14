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
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoAppService videoAppService;

    /** 上传新视频（含文件） */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<VideoInfoDTO> upload(
            @RequestPart("file") MultipartFile file,
            @RequestPart("metadata") VideoUploadDTO dto) throws Exception {
        dto.setFile(file);
        VideoInfoDTO saved = videoAppService.upload(dto);
        return ApiResponse.success(saved);
    }

    /** 删除视频 */
    @DeleteMapping("/{videoId}")
    public ApiResponse<Void> delete(@PathVariable Long videoId) {
        videoAppService.delete(videoId);
        return ApiResponse.success(null);
    }

    /** 获取单个视频详情 */
    @GetMapping("/{videoId}")
    public ApiResponse<VideoInfoDTO> getById(@PathVariable Long videoId) {
        VideoInfoDTO dto = videoAppService.getById(videoId);
        return ApiResponse.success(dto);
    }

    /** 分页列出所有视频 */
    @GetMapping
    public ApiResponse<Page<VideoInfoDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.success(videoAppService.list(page, size));
    }

    /** 分页列出某上传者的视频 */
    @GetMapping("/by/{uploaderId}")
    public ApiResponse<Page<VideoInfoDTO>> byUploader(
            @PathVariable Long uploaderId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.success(videoAppService.listByUploader(uploaderId, page, size));
    }
}
