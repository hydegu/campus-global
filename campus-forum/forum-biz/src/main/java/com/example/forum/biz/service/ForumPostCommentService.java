package com.example.forum.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admin.api.dto.UserInfo;
import com.example.common.mybatis.utils.PageResult;
import com.example.forum.api.dto.forumcomment.ForumCommentListDTO;
import com.example.forum.api.dto.forumpost.ForumPostQueryDTO;
import com.example.forum.api.entity.ForumPostComment;
import com.example.forum.api.vo.ForumPostCommentQueryVO;
import com.example.forum.api.vo.MyCommentVO;

/**
* @author 22417
* @description 针对表【forum_post_comment(帖子评论表)】的数据库操作Service
* @createDate 2025-12-09 17:36:38
*/
public interface ForumPostCommentService extends IService<ForumPostComment> {

    /**
     * 获取帖子评论列表（分页）
     *
     * @param postId 帖子ID
     * @param queryDTO 分页查询参数
     * @return 分页评论列表
     */
    IPage<ForumPostCommentQueryVO> getForumPostCommentPageList(Long postId, ForumCommentListDTO queryDTO);

    /**
     * 创建评论
     *
     * @param comment 评论对象
     * @return 创建的评论ID
     */
    void createComment(Long userId, Long postId, ForumPostComment comment);

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     */
    void deleteComment(Long userId,Long commentId);

    /**
     * 删除帖子下的所有评论
     * @param userId 用户ID
     * @param postId 帖子ID
     * @return 删除的记录数
     */
    void deleteCommentByPostId(Long userId,Long postId);

    /**
     * 点赞评论
     *
     * @param commentId 评论ID
     */
    void likeComment(Long userId,Long commentId);

    /**
     * 取消点赞评论
     *
     * */
    void unlikeComment(Long userId,Long commentId);
    /**
     * 分页查询当前用户的评论记录
     * @param queryDTO 查询参数
     * @return 评论记录分页结果
     */
    PageResult<MyCommentVO> getMyComments(ForumPostQueryDTO queryDTO);

}