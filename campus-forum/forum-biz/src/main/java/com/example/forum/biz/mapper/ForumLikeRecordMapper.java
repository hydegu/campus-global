package com.example.forum.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.api.dto.ForumLikeDTO;
import com.example.forum.api.entity.ForumLikeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ForumLikeRecordMapper extends BaseMapper<ForumLikeRecord> {

    void addLikeRecord(@Param("likeDTO") ForumLikeDTO likeDTO);

    void subLikeRecord(@Param("likeDTO") ForumLikeDTO likeDTO);

    ForumLikeRecord getLikeRecord(@Param("likeDTO") ForumLikeDTO likeDTO);
}
