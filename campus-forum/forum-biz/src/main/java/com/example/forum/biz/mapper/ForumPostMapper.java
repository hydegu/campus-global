package com.example.forum.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.api.entity.ForumPost;
import com.example.forum.api.vo.ForumPostQueryVO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 22417
* @description 针对表【forum_post(帖子表)】的数据库操作Mapper
* @createDate 2025-12-09 17:36:38
* @Entity com.example.campusapp.entity.ForumPost
*/
@Mapper
public interface ForumPostMapper extends BaseMapper<ForumPost> {

    IPage<ForumPostQueryVO> selectForumPostHotPage(Page<ForumPostQueryVO> page);

    IPage<ForumPostQueryVO> selectForumPostTimePage(Page<ForumPostQueryVO> page);

    ForumPostQueryVO selectPostDetailById(Long id);
    
    /**
     * 更新帖子评论数
     * @param postId 帖子ID
     * @param increment 增加的数量（正数为增加，负数为减少）
     */
    void updateCommentCount(Long postId, Integer increment);
}