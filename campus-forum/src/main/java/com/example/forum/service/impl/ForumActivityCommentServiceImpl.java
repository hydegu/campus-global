package com.example.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.DuplicateException;
import com.example.common.core.exception.ForbiddenException;
import com.example.common.core.exception.ResourceNotFoundException;
import com.example.forum.dto.ForumLikeDTO;
import com.example.forum.dto.forumcomment.ForumCommentListDTO;
import com.example.forum.common.utils.PageUtil;
import com.example.forum.vo.ForumActivityCommentQueryVO;
import com.example.forum.entity.ForumActivityComment;
import com.example.forum.entity.ForumLikeRecord;
import com.example.forum.mapper.ForumActivityCommentMapper;
import com.example.forum.mapper.ForumActivityMapper;
import com.example.forum.mapper.ForumLikeRecordMapper;
import com.example.forum.service.ForumActivityCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ForumActivityCommentServiceImpl extends ServiceImpl<ForumActivityCommentMapper, ForumActivityComment>
implements ForumActivityCommentService {

    private final ForumLikeRecordMapper forumLikeRecordMapper;
    private final ForumActivityMapper forumActivityMapper;

    public ForumActivityCommentServiceImpl(ForumLikeRecordMapper forumLikeRecordMapper, ForumActivityMapper forumActivityMapper) {
        this.forumLikeRecordMapper = forumLikeRecordMapper;
        this.forumActivityMapper = forumActivityMapper;
    }

    @Override
    public IPage<ForumActivityCommentQueryVO> getForumActivityCommentPageList(Long activityId, ForumCommentListDTO queryDTO) {
        IPage<ForumActivityCommentQueryVO> page = PageUtil.createPage(
                queryDTO.getPage(),
                queryDTO.getSize()
        );
        // 查询一级评论
        IPage<ForumActivityCommentQueryVO> forumActivityCommentPageList = baseMapper.selectCommentByActivityId(page, activityId);
        List<ForumActivityCommentQueryVO> firstLevelComments  = forumActivityCommentPageList.getRecords();
        if (CollectionUtils.isEmpty(firstLevelComments)) {
            return forumActivityCommentPageList;
        }
        // 提取所有一级评论的ID，批量查询其直接子评论（二级评论，第二层）
        List<Long> firstLevelCommentIds  = firstLevelComments.stream()
                .map(ForumActivityCommentQueryVO::getId)
                .collect(Collectors.toList());
        // 查询所有匹配的子评论
        List<ForumActivityCommentQueryVO> allDescendantComments  = baseMapper.selectAllDescendantCommentsByParentIds(activityId, firstLevelCommentIds);
        if(CollectionUtils.isEmpty(allDescendantComments)) {
            return forumActivityCommentPageList;
        }
        // 对所有后代评论按【根评论ID】分组，方便快速查找子评论（提高查询效率）
        Map<Long, List<ForumActivityCommentQueryVO>> descendantCommentMap = allDescendantComments.stream()
                .collect(Collectors.groupingBy(ForumActivityCommentQueryVO::getRootId));
        // 处理层级关系
        firstLevelComments.forEach(comment -> {
            // 为一级评论匹配所有后代评论（二级及以上，统一作为一级评论的子评论）
            List<ForumActivityCommentQueryVO> allChildComments = descendantCommentMap.getOrDefault(comment.getId(), Collections.emptyList());
            comment.setChildComments(allChildComments);
        });

        // 5. 封装最终分页结果并返回
        forumActivityCommentPageList.setRecords(firstLevelComments);
        return forumActivityCommentPageList;
    }
    /**
     * 递归收集某个评论的所有后代评论（三级及以上，不限制层级，统一归为同一级）
     */
    private List<ForumActivityCommentQueryVO> collectAllDescendantComments(Long parentCommentId, Map<Long, List<ForumActivityCommentQueryVO>> descendantCommentMap) {
        // 存储所有后代评论（最终返回，不嵌套）
        List<ForumActivityCommentQueryVO> allDescendants = new ArrayList<>();

        // 获取当前父评论的直接子评论
        List<ForumActivityCommentQueryVO> directChildren = descendantCommentMap.getOrDefault(parentCommentId, Collections.emptyList());
        if (CollectionUtils.isEmpty(directChildren)) {
            return allDescendants;
        }

        // 1. 添加直接子评论到结果集（三级、四级等，统一平铺）
        allDescendants.addAll(directChildren);

        // 2. 递归查询直接子评论的后代，继续添加到结果集（不嵌套，仅平铺）
        directChildren.forEach(child -> {
            allDescendants.addAll(collectAllDescendantComments(child.getId(), descendantCommentMap));
        });

        return allDescendants;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createComment(Long userId, Long activityId, ForumActivityComment comment) {
        // 2. 设置评论属性
        comment.setUserId(userId);
        comment.setActivityId(activityId);
        comment.setStatus(1);

        // 3. 处理评论层级关系
        Long parentId = comment.getParentId();
        Integer frontLevel = comment.getLevel();
        Long frontRootId = comment.getRootId();

        if (parentId != null && parentId > 0) {
            if(frontLevel != null && frontLevel == 0){
                throw new IllegalArgumentException("子评论不允许level=0");
            }
            if(frontRootId != null && frontRootId <= 0){
                throw new IllegalArgumentException("子评论的rootId必须是有效数字");
            }
            // 3.1 回复评论（非根评论）
            ForumActivityComment parentComment = getById(parentId);
            if (parentComment == null) {
                throw new ResourceNotFoundException("父级评论不存在");
            }

            // 验证rootId（前端传的rootId需匹配父评论的根）
            Long reqRootId = comment.getRootId();
            Long parentRootId = parentComment.getRootId();
            if (reqRootId != null) {
                boolean isParentRoot = parentComment.getLevel() != null && parentComment.getLevel() == 0;
                if (!reqRootId.equals(parentRootId) && !(isParentRoot && reqRootId.equals(parentComment.getId()))) {
                    throw new IllegalArgumentException("根评论ID不正确");
                }
            }
            // 设置根评论ID和层级
            if (parentComment.getLevel() != null && parentComment.getLevel() == 0) {
                // 父评论是根评论
                comment.setRootId(parentComment.getId());
                comment.setLevel(1);
            } else {
                // 父评论是回复评论
                if(parentComment.getRootId() == null){
                    throw new IllegalArgumentException("父评论的rootId异常");
                }
                comment.setRootId(parentComment.getRootId());
                comment.setLevel(Math.min(2, parentComment.getLevel() + 1)); // 最多到二级回复
            }

            // 增加父评论的回复数
            parentComment.setReplyCount((parentComment.getReplyCount() != null ? parentComment.getReplyCount() : 0) + 1);
            updateById(parentComment);
        }else{
            if(frontLevel != null && frontLevel != 0){
                throw new IllegalArgumentException("根评论level为0");
            }
            if(frontRootId != null){
                throw new IllegalArgumentException("根评论的rootId自动生成");
            }
            comment.setParentId(0L);
            comment.setLevel(0);
            comment.setRootId(null);
        }

        // 4. 保存评论
        save(comment);

        forumActivityMapper.updateCommentCount(activityId,1);

        if(comment.getLevel() == 0) {
            if(!comment.getParentId().equals(0L)){
                throw new IllegalArgumentException("根评论的parentId必须是0");
            }
            comment.setRootId(comment.getId());
            updateById(comment);
        }

    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        ForumActivityComment comment = getById(commentId);
        if (comment == null) {
            throw new ResourceNotFoundException("评论不存在");
        }
        if (!comment.getUserId().equals(userId)) {
            throw new ForbiddenException("用户无权限删除该评论");
        }

        if(comment.getLevel() != null && comment.getLevel() == 0){
            int totalCommentsToDelete = 1+countChildComments(commentId);
            forumActivityMapper.updateCommentCount(comment.getActivityId(),-totalCommentsToDelete);
        }else {
            forumActivityMapper.updateCommentCount(comment.getActivityId(),-1);
            if(comment.getParentId() != null && comment.getParentId() > 0){
                ForumActivityComment parentComment = getById(comment.getParentId());
                if(parentComment != null){
                    parentComment.setReplyCount(Math.max(parentComment.getReplyCount(), 0));
                    updateById(parentComment);
                }
            }
        }
        comment.setStatus(2);
        comment.setDeletedBy(userId);
        comment.setDeletedAt(LocalDateTime.now());
        updateById(comment);
    }
    private int countChildComments(Long commentId){
        LambdaQueryWrapper<ForumActivityComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumActivityComment::getParentId,commentId)
                .eq(ForumActivityComment::getStatus,1)
                .isNull(ForumActivityComment::getDeletedAt);
        Long directRepliesLong = count(queryWrapper);
        int directReplies = directRepliesLong.intValue();

        List<ForumActivityComment> directReplyComments = list(queryWrapper);
        int totalChildComments = directReplies;
        for(ForumActivityComment directReplyComment : directReplyComments){
            totalChildComments += countChildComments(directReplyComment.getId());
        }
        return totalChildComments;
    }

    @Override
    public void deleteCommentByPostId(Long userId, Long activityId) {
        if(userId == null){
            throw new ForbiddenException("用户未登录");
        }
        baseMapper.updateDeleteByActivityId(activityId,userId);
    }

    @Override
    public void likeComment(Long userId, Long commentId) {

        ForumActivityComment comment = getById(commentId);
        if (comment == null) {
            throw new ResourceNotFoundException("评论不存在");
        }
        comment.setLikeCount((comment.getLikeCount() != null ? comment.getLikeCount() : 0) + 1);
        comment.setUpdatedAt(LocalDateTime.now());
        // 单人点赞
        ForumLikeDTO likeDTO = new ForumLikeDTO();
        likeDTO.setUserId(userId);
        likeDTO.setLikeType(3); // 活动评论类型
        likeDTO.setLikeId(commentId);
        // 是否点过赞
        ForumLikeRecord likeRecord = forumLikeRecordMapper.getLikeRecord(likeDTO);
        if(likeRecord != null){
            throw new DuplicateException("用户已点过赞");
        }
        forumLikeRecordMapper.addLikeRecord(likeDTO);
        updateById(comment);
    }

    @Override
    public void unlikeComment(Long userId, Long commentId) {
        ForumActivityComment comment = getById(commentId);
        if (comment == null) {
            throw new ResourceNotFoundException("评论不存在");
        }
        // 检查是否点过赞
        ForumLikeDTO likeDTO = new ForumLikeDTO();
        likeDTO.setUserId(userId);
        likeDTO.setLikeType(3); // 活动评论类型
        likeDTO.setLikeId(commentId);

        ForumLikeRecord likeRecord = forumLikeRecordMapper.getLikeRecord(likeDTO);
        if (likeRecord == null) {
            throw new ResourceNotFoundException("用户未点过赞");
        }

        // 删除点赞记录
        forumLikeRecordMapper.subLikeRecord(likeDTO);

        // 减少点赞数（不能小于0）
        int currentLikeCount = comment.getLikeCount() != null ? comment.getLikeCount() : 0;
        if (currentLikeCount > 0) {
            comment.setLikeCount(currentLikeCount - 1);
            comment.setUpdatedAt(LocalDateTime.now());
            updateById(comment);
        }
    }

}
