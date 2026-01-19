package com.example.forum.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.forum.api.entity.ForumActivityComment;
import com.example.forum.api.vo.ForumActivityCommentQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ForumActivityCommentMapper extends BaseMapper<ForumActivityComment> {

    /**
     * 根据帖子ID分页查询评论列表
     * @param page 分页参数
     * @param activityId 活动ID
     * @return 评论列表
     */
   IPage<ForumActivityCommentQueryVO> selectCommentByActivityId(IPage<ForumActivityCommentQueryVO> page, Long activityId);

    /**
     * 根据父评论ID批量查询子评论
     * @param activityId 活动ID
     * @param parentId 父评论ID列表
     * @return 子评论列表
     */
   List<ForumActivityCommentQueryVO> selectCommentByParentId(Long activityId, @Param("parentIds") List<Long> parentId);

    /**
     * 批量查询指定父评论ID的所有后代评论（二级、三级、四级...）
     * @param activityId 活动ID
     * @param parentIds 父评论ID列表（一级评论ID）
     * @return 所有后代评论列表
     */
    List<ForumActivityCommentQueryVO> selectAllDescendantCommentsByParentIds(@Param("activityId") Long activityId, @Param("parentIds") List<Long> parentIds);
   /**
     * 根据活动ID软删除所有评论
     * @param activityId 活动ID
     * @param deletedBy 删除人ID
     * @return 删除的记录数
     */
   int updateDeleteByActivityId(@Param("activityId") Long activityId, @Param("deletedBy") Long deletedBy);

    /**
     * 根据父评论ID列表和指定层级查询子评论
      * @param activityId 活动ID
     * @param parentIds 父评论ID列表
     * @param level 评论层级
     * @return 子评论列表
     */
    List<ForumActivityCommentQueryVO> selectCommentByParentIdsAndLevel(@Param("activityId")Long activityId, @Param("parentIds")List<Long> parentIds, @Param("level")Integer level);

}
