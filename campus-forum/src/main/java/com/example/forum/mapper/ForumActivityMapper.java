package com.example.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.vo.ForumActivityDetailVO;
import com.example.forum.vo.ForumActivityQueryVO;
import com.example.forum.entity.ForumActivity;
import com.example.forum.entity.ForumActivityComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 22417
* @description 针对表【forum_activity(活动表)】的数据库操作Mapper
* @createDate 2025-12-09 17:36:39
* @Entity com.example.campusapp.entity.ForumActivity
*/
@Mapper
public interface ForumActivityMapper extends BaseMapper<ForumActivity> {

    IPage<ForumActivityQueryVO> selectForumActivityPage(Page<ForumActivityQueryVO> page);

    ForumActivityDetailVO selectDetailById(@Param("id") Long id);

    ForumActivityComment selectCommentById(@Param("id") Long id);

    /**
     * 更新活动评论数
     * @param activityId 活动ID
     * @param increment 增加的数量（正数为增加，负数为减少）
     */
    void updateCommentCount(Long activityId, Integer increment);
}




