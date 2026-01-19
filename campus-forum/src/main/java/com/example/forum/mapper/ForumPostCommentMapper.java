package com.example.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.forum.vo.ForumPostCommentQueryVO;
import com.example.forum.entity.ForumPostComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 22417
* @description 针对表【forum_post_comment(帖子评论表)】的数据库操作Mapper
* @createDate 2025-12-09 17:36:38
* @Entity com.example.campusapp.entity.ForumPostComment
*/
@Mapper
public interface ForumPostCommentMapper extends BaseMapper<ForumPostComment> {

    /**
     *
     * 根据父评论ID批量查询子评论
     * @param postId 帖子ID
     * @param parentIds 父评论ID列表
     * @return 子评论列表
     */
    List<ForumPostCommentQueryVO> selectCommentByParentId(@Param("postId")Long postId, @Param("parentIds")List<Long> parentIds);

    /**
     * 根据帖子ID分页查询评论列表
     * @param page 分页参数
     * @param postId 帖子ID
     * @return 评论列表
     */
    IPage<ForumPostCommentQueryVO> selectCommentPageByPostId(IPage<ForumPostCommentQueryVO> page, @Param("postId")Long postId);
    
    /**
     * 根据帖子ID软删除所有评论
     * @param postId 帖子ID
     * @param deletedBy 删除人ID
     * @return 更新记录数
     */
    int updateDeleteByPostId(@Param("postId") Long postId, @Param("userId") Long deletedBy);
    
    /**
     * 根据父评论ID列表和指定层级查询子评论
     * @param postId 帖子ID
     * @param parentIds 父评论ID列表
     * @param level 评论层级
     * @return 子评论列表
     */
    List<ForumPostCommentQueryVO> selectCommentByParentIdsAndLevel(@Param("postId")Long postId, @Param("parentIds")List<Long> parentIds, @Param("level")Integer level);

    /**
     * 批量查询指定父评论ID的所有后代评论（二级、三级、四级...）
     * @param postId 活动ID
     * @param parentIds 父评论ID列表（一级评论ID）
     * @return 所有后代评论列表
     */
    List<ForumPostCommentQueryVO> selectAllDescendantCommentsByParentIds(@Param("postId") Long postId, @Param("parentIds") List<Long> parentIds);

}