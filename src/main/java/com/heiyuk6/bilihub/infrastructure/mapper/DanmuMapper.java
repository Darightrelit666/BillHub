package com.heiyuk6.bilihub.infrastructure.mapper;

import com.heiyuk6.bilihub.domain.danmu.model.Danmu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis Mapper：danmu 表
 */
@Mapper
public interface DanmuMapper {

    @Insert("INSERT INTO danmu(video_id, user_id, content, position_time, color, font_size, type, created_at) " +
            "VALUES(#{videoId}, #{userId}, #{content}, #{positionTime}, #{color}, #{fontSize}, #{type}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Danmu danmu);

    @Select("SELECT id, video_id AS videoId, user_id AS userId, content, position_time AS positionTime, color, font_size AS fontSize, " +
            "type, created_at AS createdAt " +
            "FROM danmu WHERE video_id=#{videoId} ORDER BY created_at ASC")
    List<Danmu> selectByVideoIdOrderByCreatedAtAsc(@Param("videoId") Long videoId);

    @Delete("DELETE FROM danmu WHERE id=#{id}")
    int deleteById(@Param("id") Long id);
}
