package com.example.forum.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import com.example.common.security.util.SecurityUtils;
import com.example.forum.biz.utils.PageUtil;
import com.example.forum.api.dto.forumcomment.ForumCommentAddDTO;
import com.example.forum.api.dto.forumcomment.ForumCommentListDTO;
import com.example.forum.api.dto.forumpost.ForumPostAddDTO;
import com.example.forum.api.dto.forumpost.ForumPostListDTO;
import com.example.forum.api.dto.forumpost.ForumPostQueryDTO;
import com.example.forum.api.entity.ForumPost;
import com.example.forum.api.entity.ForumPostComment;
import com.example.forum.api.vo.ForumPostCommentQueryVO;
import com.example.forum.api.vo.ForumPostCommentTreeVO;
import com.example.forum.api.vo.ForumPostQueryVO;
import com.example.forum.biz.service.ForumPostCommentService;
import com.example.forum.biz.service.ForumPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "帖子管理", description = "帖子列表")
@StandardApiResponses
@RestController
@RequestMapping("/api/forum")
@RequiredArgsConstructor
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)

public class ForumPostController {

    private final ForumPostService forumPostService;
    private final ForumPostCommentService forumPostCommentService;

    /**
     * 查询当前用户发布的帖子
     * GET /api/app/my/posts
     *
     * @param queryDTO 查询参数
     * @return 帖子列表
     */
    @GetMapping("/my/posts")
    @Operation(summary = "查询当前用户发布的帖子", description = "分页查询当前用户发布的所有帖子")
    public Result<?> getMyPosts(
            @Parameter(description = "查询参数")
            @Valid ForumPostQueryDTO queryDTO) {
        // 使用SecurityUtils获取当前用户ID
        Long currentUserId = SecurityUtils.getCurrentUserId();
        return Result.ok(forumPostService.getMyPosts(queryDTO));
    }

    /**
     * 查询当前用户的评论记录
     * GET /api/app/my/comments
     *
     * @param queryDTO 查询参数
     * @return 评论列表
     */
    @GetMapping("/my/comments")
    @Operation(summary = "查询当前用户的评论记录", description = "分页查询当前用户发表的所有评论")
    public Result<?> getMyComments(
            @Parameter(description = "查询参数")
            @Valid ForumPostQueryDTO queryDTO) {
        // 使用SecurityUtils获取当前用户ID
        Long currentUserId = SecurityUtils.getCurrentUserId();
        return Result.ok(forumPostCommentService.getMyComments(queryDTO));
    }
    /**
     * 获取帖子列表（按热度排序）
     * GET /api/app/forum/posts/hot
     *
     * @param queryDTO 查询条件（分页参数等）
     * @return 帖子列表
     */
    @GetMapping("/posts/hot")
    @Operation(
            summary = "获取帖子列表（按热度排序）",
            description = "根据热度排序，查询当前用户的帖子列表，支持分页查询。" +
                    "返回帖子基本信息、作者信息、浏览量、点赞数、评论数等。"
    )
    public Result<PageUtil.PageResult<ForumPostQueryVO>> getForumPostList(@ModelAttribute @ParameterObject @Valid ForumPostListDTO queryDTO) {
        IPage<ForumPostQueryVO> iPage = forumPostService.getForumPostHotList(queryDTO);
        return Result.ok(PageUtil.toPageResult(iPage));
    }

    /**
     * 获取帖子列表（按时间排序）
     * GET /api/app/forum/posts/time
     *
     * @param queryDTO 查询条件（分页参数等）
     * @return 帖子列表
     */
    @GetMapping("/posts/time")
    @Operation(
            summary = "获取帖子列表（按时间排序）",
            description = "按时间排序，查询当前用户的帖子列表，支持分页查询。" +
                    "返回帖子基本信息、作者信息、浏览量、点赞数、评论数等。"
    )
    public Result<PageUtil.PageResult<ForumPostQueryVO>> getForumPostListTime(@ModelAttribute @ParameterObject @Valid ForumPostListDTO queryDTO) {
        IPage<ForumPostQueryVO> iPage = forumPostService.getForumPostTimeList(queryDTO);
        return Result.ok(PageUtil.toPageResult(iPage));
    }

    /**
     * 获取帖子详情
     * GET /api/app/forum/posts/{id}
     *
     * @param id 帖子ID
     * @return 帖子详情
     */
    @GetMapping("/posts/{id}")
    @Operation(
            summary = "获取帖子详情",
            description = "根据ID获取帖子详情，包括帖子标题、内容、图片、标签、作者信息、" +
                    "浏览量、点赞数、评论数等详细信息，以及该帖子的所有评论。"
    )
    public Result<ForumPostCommentTreeVO> getForumPostDetail(
            @Parameter(description = "帖子ID", example = "1")
            @PathVariable Long id
    ) {
        // 帖子
        ForumPostCommentTreeVO forumPost = forumPostService.getForumPostDetail(id);
        return Result.ok(forumPost);
    }

    /**
     * 用户发布帖子 (JSON请求)
     * POST /api/app/forum/posts
     *
     * @param postdto 帖子对象
     * @return 创建结果
     */
    @PostMapping("/posts")
    @Operation(
            summary = "创建帖子",
            description = "用户创建新帖子，系统会自动设置创建时间、初始统计数据等信息。"
    )
    public Result<Void> createPost(
            @Valid @RequestBody ForumPostAddDTO postdto
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumPostService.createPost(userId, convertDTOToEntity(postdto, postdto.getImageUrls()));
        return Result.ok();
    }


    //将 ForumPostAddDTO转换为实体（带图片URL）
    private ForumPost convertDTOToEntity(ForumPostAddDTO postdto, List<String> imageUrls) {
        ForumPost forumPost = new ForumPost();
        forumPost.setPostTitle(postdto.getPostTitle());
        forumPost.setPostContent(postdto.getPostContent());
        forumPost.setImageUrls(imageUrls);
        return forumPost;
    }

    //将 ForumPostAddDTO转换为实体（原方法，保留向后兼容）
    private ForumPost dtoToEntity(ForumPostAddDTO postdto) {
        return convertDTOToEntity(postdto, postdto.getImageUrls());
    }


    /**
     * 删除帖子
     * DELETE /api/app/forum/posts/{id}
     *
     * @param id 帖子ID
     * @return 删除结果
     */
    @DeleteMapping("/posts/{id}")
    @Operation(
            summary = "删除帖子",
            description = "用户删除自己发布的帖子，系统会执行软删除操作（同时删除该帖子的所有评论）。"
    )
    public Result<Void> deletePost(
            @Parameter(description = "帖子ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumPostService.deletePost(id, userId);
        return Result.ok();
    }

    /**
     * 创建评论
     * POST /api/app/forum/posts/{postId}/comments
     *
     * @param  commentdto 评论对象
     * @return 创建结果
     */
    @PostMapping(value = "/posts/{postId}/comments")
    @Operation(
            summary = "创建评论",
            description = "用户对帖子进行评论或回复他人评论。"
    )
    public Result<Void> createComment(
            @Valid @PathVariable Long postId,
            @Valid @RequestBody ForumCommentAddDTO commentdto
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumPostCommentService.createComment(userId,postId,dtoToEntity(commentdto));
        return Result.ok();
    }
    // 辅助方法，将DTO转换为实体
    private ForumPostComment dtoToEntity(ForumCommentAddDTO commentdto) {
        ForumPostComment comment = new ForumPostComment();
        comment.setCommentContent(commentdto.getCommentContent());
        comment.setParentId(commentdto.getParentId());
        comment.setRootId(commentdto.getRootId());
        comment.setLevel(commentdto.getLevel());
        return comment;
    }

    /**
     * 获取帖子评论列表
     * GET /api/app/forum/posts/{id}/comments
     *
     * @param postId 帖子ID
     * @return 帖子评论列表
     */
    @GetMapping("/posts/{postId}/comments")
    @Operation(
            summary = "获取帖子评论列表",
            description = "获取指定帖子的所有评论列表。"
    )
    public Result<PageUtil.PageResult<ForumPostCommentQueryVO>> getCommentList(
            @Parameter(description = "帖子ID", example = "1")
            @PathVariable Long postId,
            @Parameter(description = "分页查询参数")
            @ModelAttribute @ParameterObject ForumCommentListDTO querydto
    ) {
        IPage<ForumPostCommentQueryVO> pageResult = forumPostCommentService.getForumPostCommentPageList(postId, querydto);
        return Result.ok(PageUtil.toPageResult(pageResult));
    }

    /**
     * 删除评论
     * DELETE /api/app/forum/posts/comments/{commentId}
     *
     * @param commentId 评论ID
     * @return 删除结果
     */
    @DeleteMapping("/posts/comments/{commentId}")
    @Operation(
            summary = "删除评论",
            description = "用户删除自己发表的评论，系统会执行软删除操作。"
    )
    public Result<Void> deleteComment(
            @Parameter(description = "评论ID", example = "1")
            @PathVariable Long commentId
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumPostCommentService.deleteComment(userId,commentId);
        return Result.ok();
    }
}