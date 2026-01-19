package com.example.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.ForbiddenException;
import com.example.common.core.exception.ResourceNotFoundException;
import com.example.common.security.util.SecurityUtils;
import com.example.forum.common.utils.PageResult;
import com.example.forum.common.utils.PageUtil;
import com.example.forum.dto.ForumLikeDTO;
import com.example.forum.dto.forumcomment.ForumCommentListDTO;
import com.example.forum.dto.forumpost.ForumPostQueryDTO;
import com.example.forum.entity.ForumLikeRecord;
import com.example.forum.entity.ForumPost;
import com.example.forum.entity.ForumPostComment;
import com.example.forum.mapper.ForumLikeRecordMapper;
import com.example.forum.mapper.ForumPostCommentMapper;
import com.example.forum.mapper.ForumPostMapper;
import com.example.forum.service.ForumPostCommentService;
import com.example.forum.vo.ForumPostCommentQueryVO;
import com.example.forum.vo.MyCommentVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author 22417
* @description 针对表【forum_post_comment(帖子评论表)】的数据库操作Service实现
* @createDate 2025-12-09 17:36:38
*/
@Service
@RequiredArgsConstructor
public class ForumPostCommentServiceImpl extends ServiceImpl<ForumPostCommentMapper, ForumPostComment>
    implements ForumPostCommentService {

    private final ForumPostMapper forumPostMapper;
    private final ForumLikeRecordMapper forumLikeRecordMapper;

    @Override
    public PageResult<MyCommentVO> getMyComments(ForumPostQueryDTO queryDTO) {
        // 从请求头中获取当前用户ID
        Long currentUserId = SecurityUtils.getCurrentUserId();

        // 构建分页对象
        IPage<ForumPostComment> page = PageUtil.createPage(queryDTO.getPage(), queryDTO.getSize());

        // 构建查询条件
        LambdaQueryWrapper<ForumPostComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumPostComment::getUserId, currentUserId)
                .isNull(ForumPostComment::getDeletedAt)  // 只查询未删除的评论
                .orderByDesc(ForumPostComment::getCreatedAt); // 按创建时间倒序

        // 执行分页查询
        IPage<ForumPostComment> resultPage = page(page, queryWrapper);

        // 转换为VO对象
        PageResult<MyCommentVO> pageResult = new PageResult<>();
        pageResult.setCurrent(resultPage.getCurrent());
        pageResult.setPageSize(resultPage.getSize());
        pageResult.setTotal(resultPage.getTotal());
        pageResult.setPages(resultPage.getPages());

        // 转换数据列表
        pageResult.setList(resultPage.getRecords().stream().map(this::convertToMyCommentVO).collect(Collectors.toList()));

        return pageResult;
    }

    /**
     * 将ForumPostComment实体转换为MyCommentVO
     * @param comment 评论实体
     * @return MyCommentVO
     */
    private MyCommentVO convertToMyCommentVO(ForumPostComment comment) {
        MyCommentVO vo = new MyCommentVO();
        vo.setId(comment.getId());
        vo.setPostId(comment.getPostId());
        vo.setCommentContent(comment.getCommentContent());
        vo.setLikeCount(comment.getLikeCount());
        vo.setReplyCount(comment.getReplyCount());
        vo.setCreatedAt(comment.getCreatedAt());
        vo.setUpdatedAt(comment.getUpdatedAt());

        // 获取帖子标题
        ForumPost post = forumPostMapper.selectById(comment.getPostId());
        if (post != null) {
            vo.setPostTitle(post.getPostTitle());
        }

        return vo;
    }
    @Override
    public IPage<ForumPostCommentQueryVO> getForumPostCommentPageList(Long activityId, ForumCommentListDTO queryDTO) {
        IPage<ForumPostCommentQueryVO> page = PageUtil.createPage(
                queryDTO.getPage(),
                queryDTO.getSize()
        );
        // 查询一级评论
        IPage<ForumPostCommentQueryVO> forumPostCommentPageList = baseMapper.selectCommentPageByPostId(page, activityId);
        List<ForumPostCommentQueryVO> firstLevelComments  = forumPostCommentPageList.getRecords();
        if (CollectionUtils.isEmpty(firstLevelComments)) {
            return forumPostCommentPageList;
        }
        // 提取所有一级评论的ID，批量查询其直接子评论（二级评论，第二层）
        List<Long> firstLevelCommentIds  = firstLevelComments.stream()
                .map(ForumPostCommentQueryVO::getId)
                .collect(Collectors.toList());
        // 查询所有匹配的子评论
        List<ForumPostCommentQueryVO> allDescendantComments  = baseMapper.selectAllDescendantCommentsByParentIds(activityId, firstLevelCommentIds);
        if(CollectionUtils.isEmpty(allDescendantComments)) {
            return forumPostCommentPageList;
        }
        // 对所有后代评论按【根评论ID】分组，方便快速查找子评论（提高查询效率）
        Map<Long, List<ForumPostCommentQueryVO>> descendantCommentMap = allDescendantComments.stream()
                .collect(Collectors.groupingBy(ForumPostCommentQueryVO::getRootId));
        // 处理层级关系
        firstLevelComments.forEach(comment -> {
            // 为一级评论匹配所有后代评论（二级及以上，统一作为一级评论的子评论）
            List<ForumPostCommentQueryVO> allChildComments = descendantCommentMap.getOrDefault(comment.getId(), Collections.emptyList());
            comment.setChildComments(allChildComments);
        });

        // 5. 封装最终分页结果并返回
        forumPostCommentPageList.setRecords(firstLevelComments);
        return forumPostCommentPageList;
    }
    /**
     * 递归收集某个评论的所有后代评论（三级及以上，不限制层级，统一归为同一级）
     */
    private List<ForumPostCommentQueryVO> collectAllDescendantComments(Long parentCommentId, Map<Long, List<ForumPostCommentQueryVO>> descendantCommentMap) {
        // 存储所有后代评论（最终返回，不嵌套）
        List<ForumPostCommentQueryVO> allDescendants = new ArrayList<>();

        // 获取当前父评论的直接子评论
        List<ForumPostCommentQueryVO> directChildren = descendantCommentMap.getOrDefault(parentCommentId, Collections.emptyList());
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
    public void createComment(Long userId,Long postId,ForumPostComment comment) {
        // 1. 获取当前用户ID
        if (userId == null) {
            throw new ForbiddenException("用户未登录");
        }

        // 2. 设置评论属性
        comment.setUserId(userId);
        comment.setPostId(postId);
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
            ForumPostComment parentComment = getById(parentId);
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
        
        // 5. 更新帖子的评论数
        forumPostMapper.updateCommentCount(postId, 1);

        if(comment.getLevel() == 0) {
            if(!comment.getParentId().equals(0L)){
                throw new IllegalArgumentException("根评论的parentId必须是0");
            }
            comment.setRootId(comment.getId());
            updateById(comment);
        }

    }
    /**
     * 计算指定评论下的子评论总数
     * @param commentId 评论ID
     * @return 子评论总数
     */
    private int countChildComments(Long commentId) {
        LambdaQueryWrapper<ForumPostComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumPostComment::getParentId, commentId)
                .eq(ForumPostComment::getStatus, 1) // 只统计正常的评论
                .isNull(ForumPostComment::getDeletedAt);
        Long directRepliesLong = count(queryWrapper);
        int directReplies = directRepliesLong.intValue();

        // 递归计算更深层的回复
        List<ForumPostComment> directReplyComments = list(queryWrapper);
        int totalChildComments = directReplies;
        for (ForumPostComment replyComment : directReplyComments) {
            totalChildComments += countChildComments(replyComment.getId());
        }

        return totalChildComments;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(Long userId,Long commentId) {
        // 1. 获取当前用户ID
        if (userId == null) {
            throw new ForbiddenException("用户未登录");
        }

        // 2. 检查评论是否存在
        ForumPostComment existingComment = getById(commentId);
        if (existingComment == null) {
            throw new ResourceNotFoundException("评论不存在");
        }

        // 3. 检查用户是否有权限删除该评论
        if (!userId.equals(existingComment.getUserId())) {
            throw new ForbiddenException("无权限删除该评论");
        }

        // 4. 如果是一级评论，需要先处理子评论
        if (existingComment.getLevel() != null && existingComment.getLevel() == 0) {
            // 计算要删除的评论总数（包括子评论）
            int totalCommentsToDelete = 1 + countChildComments(existingComment.getId());
            // 减少对应帖子的评论数
            forumPostMapper.updateCommentCount(existingComment.getPostId(), -totalCommentsToDelete);
        } else {
            // 减少帖子评论数
            forumPostMapper.updateCommentCount(existingComment.getPostId(), -1);
            
            // 如果有父评论，减少父评论的回复数
            if (existingComment.getParentId() != null && existingComment.getParentId() > 0) {
                ForumPostComment parentComment = getById(existingComment.getParentId());
                if (parentComment != null) {
                    parentComment.setReplyCount(Math.max(0, (parentComment.getReplyCount() != null ? parentComment.getReplyCount() : 0) - 1));
                    updateById(parentComment);
                }
            }
        }

        // 5. 软删除评论
        existingComment.setStatus(2); // 2-用户删除
        existingComment.setDeletedBy(userId);
        existingComment.setDeletedAt(LocalDateTime.now());
        updateById(existingComment);
    }

    @Override
    public void deleteCommentByPostId(Long userId,Long postId) {
        if(userId == null){
            throw new ForbiddenException("用户未登录");
        }
        baseMapper.updateDeleteByPostId(postId,userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void likeComment(Long userId,Long commentId) {
        // 1. 检查评论是否存在
        ForumPostComment comment = getById(commentId);
        if (comment == null) {
            throw new ResourceNotFoundException("评论不存在");
        }
        ForumLikeDTO likeDTO = new ForumLikeDTO();
        likeDTO.setUserId(userId);
        likeDTO.setLikeId(commentId);
        likeDTO.setLikeType(4);
        ForumLikeRecord likeRecord = forumLikeRecordMapper.getLikeRecord(likeDTO);
        if(likeRecord != null){
            throw new ResourceNotFoundException("该评论已点赞");
        }
        // 2. 增加点赞数
        comment.setLikeCount((comment.getLikeCount() != null ? comment.getLikeCount() : 0) + 1);
        comment.setUpdatedAt(LocalDateTime.now());
        updateById(comment);
        forumLikeRecordMapper.addLikeRecord(likeDTO);
    }

    @Override
    public void unlikeComment(Long userId, Long commentId) {
        ForumPostComment comment = getById(commentId);
        if (comment == null) {
            throw new ResourceNotFoundException("评论不存在");
        }
        if(userId == null){
            throw new ForbiddenException("用户未登录");
        }
        ForumLikeDTO likeDTO = new ForumLikeDTO();
        likeDTO.setUserId(userId);
        likeDTO.setLikeId(commentId);
        likeDTO.setLikeType(4);
        ForumLikeRecord likeRecord = forumLikeRecordMapper.getLikeRecord(likeDTO);
        if(likeRecord == null){
            throw new ResourceNotFoundException("该评论未点赞");
        }
        comment.setLikeCount((comment.getLikeCount() != null ? comment.getLikeCount() : 0) - 1);
        comment.setUpdatedAt(LocalDateTime.now());
        updateById(comment);
        forumLikeRecordMapper.subLikeRecord(likeDTO);
    }
}

