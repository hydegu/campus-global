package com.example.forum.biz.controller;

import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import com.example.common.security.util.SecurityUtils;
import com.example.forum.api.dto.ForumLikeDTO;
import com.example.forum.api.vo.ForumLikeVO;
import com.example.forum.biz.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "点赞管理", description = "帖子、评论等点赞相关操作")
@StandardApiResponses
@RestController
@RequestMapping("/api/forum/likes")
@RequiredArgsConstructor
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)

public class ForumLikeController {
    private final ForumPostService forumPostService;
    private final ForumPostCommentService forumPostCommentService;
    private final ForumActivityCommentService forumActivityCommentService;
    private final ForumActivityService forumActivityService;
    private final ForumLikeService forumLikeService;

    /**
     * 查看点赞记录
     * @return 点赞记录
     * */
    @GetMapping("/records")
    @Operation(
            summary = "查看点赞记录",
            description = "查看用户对帖子、评论、活动进行点赞的记录。"
    )
    public Result<List<ForumLikeVO>> getLikeRecords(
            @Parameter(description = "点赞类型(1=活动, 2=帖子, 3=活动评论, 4=帖子评论)", example = "1")
            @RequestParam(value = "likeType", required = false) Integer likeType) {
        if(likeType == null){
            throw new IllegalArgumentException("点赞类型不能为空");
        }
        Long userId = SecurityUtils.getCurrentUserId();
        ForumLikeDTO forumLikeDTO = new ForumLikeDTO();
        forumLikeDTO.setUserId(userId);
        forumLikeDTO.setLikeType(likeType);
        List<ForumLikeVO> likeRecords = forumLikeService.getLikeRecords(userId, forumLikeDTO);
        return Result.ok(likeRecords);
    }

    /**
     * 点赞帖子
     * @param id 帖子ID
     * @return 点赞结果
     */
    @PostMapping("/posts/{id}")
    @Operation(
            summary = "点赞帖子",
            description = "用户对帖子进行点赞操作。"
    )
    public Result<Void> likePost(
            @Parameter(description = "帖子ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumPostService.likePost(userId,id);
        return Result.ok();
    }

    /**
     * 取消点赞帖子
     * @param id 帖子ID
     * @return 取消点赞结果
     */
    @DeleteMapping("/posts/{id}")
    @Operation(
            summary = "取消点赞帖子",
            description = "用户对已点赞的帖子进行取消点赞操作。"
    )
    public Result<Void> unlikePost(
            @Parameter(description = "帖子ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumPostService.unlikePost(userId,id);
        return Result.ok();
    }

    /**
     * 点赞帖子评论
     * @param id 评论ID
     * @return 点赞结果
     */
    @PostMapping("/post-comments/{id}")
    @Operation(
            summary = "点赞帖子评论",
            description = "用户对帖子评论进行点赞操作。"
    )
    public Result<Void> likePostComment(
            @Parameter(description = "评论ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumPostCommentService.likeComment(userId, id);
        return Result.ok();
    }

    /**
     * 取消点赞帖子评论
     * @param id 评论ID
     * @return 取消点赞结果
     */
    @DeleteMapping("/post-comments/{id}")
    @Operation(
            summary = "取消点赞帖子评论",
            description = "用户对已点赞的帖子评论进行取消点赞操作。"
    )
    public Result<Void> unlikePostComment(
            @Parameter(description = "评论ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumPostCommentService.unlikeComment(userId, id);
        return Result.ok();
    }

    /**
     * 点赞活动
     * @param id 活动ID
     * @return 点赞结果
     * */
    @PostMapping("/activities/{id}")
    @Operation(
            summary = "点赞活动",
            description = "用户对活动进行点赞操作。"
    )
    public Result<Void> likeActivity(
            @Parameter(description = "活动ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumActivityService.likeActivity(userId,id);
        return Result.ok();
    }
    /**
     * 取消点赞活动
     * @param id 活动ID
     *           @return 取消点赞结果
     * */
    @DeleteMapping("/activities/{id}")
    @Operation(
            summary = "取消点赞活动",
            description = "用户对已点赞的活动进行取消点赞操作。"
    )
    public Result<Void> unlikeActivity(
            @Parameter(description = "活动ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumActivityService.unLikeActivity(userId,id);
        return Result.ok();
    }

    /**
     * 点赞活动评论
     * @param id 活动评论ID
     * @return 点赞结果
     */
    @PostMapping("/activity-comments/{id}")
    @Operation(
            summary = "点赞活动评论",
            description = "用户对活动评论进行点赞操作。"
    )
    public Result<Void> likeActivityComment(
            @Parameter(description = "活动评论ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumActivityCommentService.likeComment(userId, id);
        return Result.ok();
    }

    /**
     * 取消点赞活动评论
     * @param id 活动评论ID
     * @return 取消点赞结果
     */
    @DeleteMapping("/activity-comments/{id}")
    @Operation(
            summary = "取消点赞活动评论",
            description = "用户对已点赞的活动评论进行取消点赞操作。"
    )
    public Result<Void> unlikeActivityComment(
            @Parameter(description = "活动评论ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumActivityCommentService.unlikeComment(userId, id);
        return Result.ok();
    }
}
