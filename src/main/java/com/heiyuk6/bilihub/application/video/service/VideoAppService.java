package com.heiyuk6.bilihub.application.video.service;

import com.heiyuk6.bilihub.application.video.dto.VideoInfoDTO;
import com.heiyuk6.bilihub.application.video.dto.VideoUploadDTO;
import com.heiyuk6.bilihub.domain.video.entity.Video;
import com.heiyuk6.bilihub.domain.video.repository.VideoRepository;
import com.baidu.fsg.uid.UidGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoAppService {

    private final VideoRepository videoRepo;
    private final UidGenerator uidGen;

    /**
     * 上传新视频
     */
    public VideoInfoDTO upload(VideoUploadDTO dto) throws Exception {
        // 1. 保存文件到存储（本地/MinIO 等），返回路径
        MultipartFile file = dto.getFile();
        String storagePath = /* your storage logic here */ "/videos/" + file.getOriginalFilename();
        file.transferTo(new java.io.File("/data" + storagePath));
        // 2. 生成缩略图、转码、计算时长、大小……
        String thumbnail = "/thumbnails/" + file.getOriginalFilename() + ".jpg";
        int duration = 0; long size = file.getSize(); // stub
        // 3. 构造领域对象
        long id = uidGen.getUID();
        Video v = Video.of(
                id,
                dto.getUploaderId(),
                dto.getTitle(),
                dto.getDescription(),
                storagePath,
                thumbnail,
                duration,
                size,
                "READY"
        );
        v.setCreateTime(LocalDateTime.now());
        v.setUpdateTime(LocalDateTime.now());
        // 4. 持久化
        Video saved = videoRepo.save(v);
        // 5. 返回 DTO
        VideoInfoDTO out = new VideoInfoDTO();
        out.setId(saved.getId());
        out.setUploaderId(saved.getUploaderId());
        out.setTitle(saved.getTitle());
        out.setDescription(saved.getDescription());
        out.setStoragePath(saved.getStoragePath());
        out.setThumbnailPath(saved.getThumbnailPath());
        out.setDuration(saved.getDuration());
        out.setSize(saved.getSize());
        out.setStatus(saved.getStatus());
        out.setViewCount(saved.getViewCount());
        out.setCreateTime(saved.getCreateTime());
        out.setUpdateTime(saved.getUpdateTime());
        return out;
    }

    /** 删除视频 */
    public void delete(Long videoId) {
        videoRepo.deleteById(videoId);
    }

    /** 查询单个视频 */
    public VideoInfoDTO getById(Long videoId) {
        Video v = videoRepo.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("视频不存在"));
        VideoInfoDTO d = new VideoInfoDTO();
        d.setId(v.getId());
        d.setUploaderId(v.getUploaderId());
        d.setTitle(v.getTitle());
        d.setDescription(v.getDescription());
        d.setStoragePath(v.getStoragePath());
        d.setThumbnailPath(v.getThumbnailPath());
        d.setDuration(v.getDuration());
        d.setSize(v.getSize());
        d.setStatus(v.getStatus());
        d.setViewCount(v.getViewCount());
        d.setCreateTime(v.getCreateTime());
        d.setUpdateTime(v.getUpdateTime());
        return d;
    }

    /** 分页列出所有视频 */
    public Page<VideoInfoDTO> list(int page, int size) {
        Pageable pg = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createTime"));
        return videoRepo.findAll(pg)
                .map(v -> {
                    VideoInfoDTO d = new VideoInfoDTO();
                    d.setId(v.getId());
                    d.setUploaderId(v.getUploaderId());
                    d.setTitle(v.getTitle());
                    d.setDescription(v.getDescription());
                    d.setStoragePath(v.getStoragePath());
                    d.setThumbnailPath(v.getThumbnailPath());
                    d.setDuration(v.getDuration());
                    d.setSize(v.getSize());
                    d.setStatus(v.getStatus());
                    d.setViewCount(v.getViewCount());
                    d.setCreateTime(v.getCreateTime());
                    d.setUpdateTime(v.getUpdateTime());
                    return d;
                });
    }

    /** 分页列出某上传者视频 */
    public Page<VideoInfoDTO> listByUploader(Long uploaderId, int page, int size) {
        Pageable pg = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createTime"));
        return videoRepo.findByUploaderId(uploaderId, pg)
                .map(v -> {
                    VideoInfoDTO d = new VideoInfoDTO();
                    d.setId(v.getId());
                    d.setUploaderId(v.getUploaderId());
                    d.setTitle(v.getTitle());
                    d.setDescription(v.getDescription());
                    d.setStoragePath(v.getStoragePath());
                    d.setThumbnailPath(v.getThumbnailPath());
                    d.setDuration(v.getDuration());
                    d.setSize(v.getSize());
                    d.setStatus(v.getStatus());
                    d.setViewCount(v.getViewCount());
                    d.setCreateTime(v.getCreateTime());
                    d.setUpdateTime(v.getUpdateTime());
                    return d;
                });
    }
}
