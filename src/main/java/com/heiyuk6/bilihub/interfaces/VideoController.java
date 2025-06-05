package com.heiyuk6.bilihub.interfaces;

import com.heiyuk6.bilihub.application.video.dto.VideoResponseDTO;
import com.heiyuk6.bilihub.application.video.dto.VideoUploadDTO;
import com.heiyuk6.bilihub.application.video.service.VideoAppService;
import com.heiyuk6.bilihub.common.result.ApiResponse;
import com.heiyuk6.bilihub.domain.video.exception.VideoDomainException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 视频接口
 */
@RestController
@RequestMapping("/api/video")
public class VideoController {

    private final VideoAppService videoAppService;

    public VideoController(VideoAppService videoAppService) {
        this.videoAppService = videoAppService;
    }

    /**
     * 上传视频
     */
    @PostMapping("/upload")
    public ApiResponse<VideoResponseDTO> upload(@Valid @RequestBody VideoUploadDTO dto) {
        try {
            VideoResponseDTO vo = videoAppService.uploadVideo(dto);
            return ApiResponse.success(vo);
        } catch (VideoDomainException ex) {
            return ApiResponse.fail(400, ex.getMessage());
        } catch (Exception ex) {
            return ApiResponse.fail(500, "视频上传异常");
        }
    }

    /**
     * 更新视频信息
     */
    @PutMapping("/{id}")
    public ApiResponse<VideoResponseDTO> update(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String thumbnailPath,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Long size) {
        try {
            VideoResponseDTO vo = videoAppService.updateVideo(id, title, description, thumbnailPath, duration, size);
            return ApiResponse.success(vo);
        } catch (VideoDomainException ex) {
            return ApiResponse.fail(400, ex.getMessage());
        } catch (Exception ex) {
            return ApiResponse.fail(500, "视频更新异常");
        }
    }

    /**
     * 更新状态
     */
    @PatchMapping("/{id}/status")
    public ApiResponse<Void> updateStatus(@PathVariable("id") Long id,
                                          @RequestParam("status") String status) {
        try {
            videoAppService.updateStatus(id, status);
            return ApiResponse.success(null);
        } catch (VideoDomainException ex) {
            return ApiResponse.fail(400, ex.getMessage());
        } catch (Exception ex) {
            return ApiResponse.fail(500, "更新状态异常");
        }
    }

    /**
     * 删除视频
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") Long id) {
        try {
            videoAppService.deleteVideo(id);
            return ApiResponse.success(null);
        } catch (VideoDomainException ex) {
            return ApiResponse.fail(400, ex.getMessage());
        } catch (Exception ex) {
            return ApiResponse.fail(500, "视频删除异常");
        }
    }

    /**
     * 查询单个视频
     */
    @GetMapping("/{id}")
    public ApiResponse<VideoResponseDTO> getById(@PathVariable("id") Long id) {
        try {
            VideoResponseDTO vo = videoAppService.getVideoById(id);
            return ApiResponse.success(vo);
        } catch (VideoDomainException ex) {
            return ApiResponse.fail(404, ex.getMessage());
        } catch (Exception ex) {
            return ApiResponse.fail(500, "查询视频异常");
        }
    }

    /**
     * 查询某用户所有视频
     */
    @GetMapping("/user/{uploaderId}")
    public ApiResponse<List<VideoResponseDTO>> listByUploader(@PathVariable("uploaderId") Long uploaderId) {
        try {
            List<VideoResponseDTO> list = videoAppService.listByUploader(uploaderId);
            return ApiResponse.success(list);
        } catch (Exception ex) {
            return ApiResponse.fail(500, "查询用户视频异常");
        }
    }

    /**
     * 分页查询所有视频
     */
    @GetMapping("/all")
    public ApiResponse<List<VideoResponseDTO>> listAll(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "10") int limit) {
        try {
            List<VideoResponseDTO> list = videoAppService.listAll(offset, limit);
            return ApiResponse.success(list);
        } catch (Exception ex) {
            return ApiResponse.fail(500, "查询视频列表异常");
        }
    }

    /**
     * 增加播放量
     */
    @PostMapping("/{id}/view")
    public ApiResponse<Void> incrementView(@PathVariable("id") Long id) {
        try {
            videoAppService.incrementViewCount(id);
            return ApiResponse.success(null);
        } catch (VideoDomainException ex) {
            return ApiResponse.fail(400, ex.getMessage());
        } catch (Exception ex) {
            return ApiResponse.fail(500, "增加播放量异常");
        }
    }
}
