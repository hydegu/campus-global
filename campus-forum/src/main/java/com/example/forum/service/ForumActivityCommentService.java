package com.example.forum.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.forum.dto.forumcomment.ForumCommentListDTO;
import com.example.forum.vo.ForumActivityCommentQueryVO;
import com.example.forum.entity.ForumActivityComment;

public interface ForumActivityCommentService extends IService<ForumActivityComment> {

    /**
     * 获取活动评论列表（分页）
     *
     * @param id 活动ID
     * @param queryDTO 分页查询参数
     * @return 分页评论列表
     */
    IPage<ForumActivityCommentQueryVO> getForumActivityCommentPageList(Long id, ForumCommentListDTO queryDTO);

    /**
     * 创建评论
     *
     * @param comment 评论对象
     * @return 创建的评论ID
     */
    void createComment(Long userId, Long activityId, ForumActivityComment comment);

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @throws com.example.campusapp.commons.exception.ForbiddenException 当用户无权限删除该评论时抛出
     * @throws com.example.campusapp.commons.exception.ResourceNotFoundException 当评论不存在时抛出
     */
    void deleteComment(Long userId,Long commentId);

    /**
     * 删除帖子下的所有评论
     * @param userId 用户ID
     * @param activityId 活动ID
     * @return 删除的记录数
     */
    void deleteCommentByPostId(Long userId,Long activityId);

    /**
     * 点赞评论
     *
     * @param commentId 评论ID
     * @throws com.example.campusapp.commons.exception.ResourceNotFoundException 当评论不存在时抛出
     */
    void likeComment(Long userId, Long commentId);

    /**
     * 取消点赞
     *
     * */
    void unlikeComment(Long userId,Long commentId);
}
