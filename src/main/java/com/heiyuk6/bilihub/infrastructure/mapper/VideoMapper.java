package com.heiyuk6.bilihub.infrastructure.mapper;

import com.heiyuk6.bilihub.domain.video.model.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis Mapper：video 表
 */
@Mapper
public interface VideoMapper {

    @Insert("INSERT INTO video(uploader_id, title, description, storage_path, thumbnail_path, duration, size, status, view_count, created_at, updated_at) " +
            "VALUES(#{uploaderId}, #{title}, #{description}, #{storagePath}, #{thumbnailPath}, #{duration}, #{size}, #{status}, #{viewCount}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Video video);

    @Update("UPDATE video SET title=#{title}, description=#{description}, storage_path=#{storagePath}, thumbnail_path=#{thumbnailPath}, duration=#{duration}, " +
            "size=#{size}, status=#{status}, view_count=#{viewCount}, updated_at=#{updatedAt} " +
            "WHERE id=#{id}")
    int update(Video video);

    @Delete("DELETE FROM video WHERE id=#{id}")
    int deleteById(@Param("id") Long id);

    @Select("SELECT id, uploader_id AS uploaderId, title, description, storage_path AS storagePath, thumbnail_path AS thumbnailPath, " +
            "duration, size, status, view_count AS viewCount, created_at AS createdAt, updated_at AS updatedAt " +
            "FROM video WHERE id=#{id}")
    Video selectById(@Param("id") Long id);

    @Select("SELECT id, uploader_id AS uploaderId, title, description, storage_path AS storagePath, thumbnail_path AS thumbnailPath, " +
            "duration, size, status, view_count AS viewCount, created_at AS createdAt, updated_at AS updatedAt " +
            "FROM video WHERE uploader_id=#{uploaderId}")
    List<Video> selectByUploaderId(@Param("uploaderId") Long uploaderId);

    @Select("SELECT id, uploader_id AS uploaderId, title, description, storage_path AS storagePath, thumbnail_path AS thumbnailPath, " +
            "duration, size, status, view_count AS viewCount, created_at AS createdAt, updated_at AS updatedAt " +
            "FROM video ORDER BY created_at DESC LIMIT #{offset}, #{limit}")
    List<Video> selectAllOrderByCreatedAtDesc(@Param("offset") int offset, @Param("limit") int limit);
}
