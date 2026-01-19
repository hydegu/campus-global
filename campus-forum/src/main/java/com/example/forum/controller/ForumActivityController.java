package com.example.forum.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.core.util.Result;
import com.example.common.security.util.SecurityUtils;
import com.example.forum.dto.forumActivity.ForumActivityAddDTO;
import com.example.forum.dto.forumActivity.ForumActivityListDTO;
import com.example.forum.dto.forumcomment.ForumCommentAddDTO;
import com.example.forum.dto.forumcomment.ForumCommentListDTO;
import com.example.forum.common.interfaces.StandardApiResponses;
import com.example.forum.common.utils.PageUtil;
import com.example.forum.vo.ForumActivityCommentQueryVO;
import com.example.forum.vo.ForumActivityDetailVO;
import com.example.forum.vo.ForumActivityQueryVO;
import com.example.forum.entity.ForumActivityComment;
import com.example.forum.service.ForumActivityCommentService;
import com.example.forum.service.ForumActivityRegistrationService;
import com.example.forum.service.ForumActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Tag(name = "活动管理", description = "活动列表")
@StandardApiResponses
@RestController
@RequestMapping("/api/forum/activities")
@RequiredArgsConstructor
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ForumActivityController {
    private final ForumActivityService forumActivityService;
    private final ForumActivityCommentService forumActivityCommentService;
    private final ForumActivityRegistrationService forumActivityRegistrationService;

    /**
     * 获取活动列表
     * @param queryDTO 查询条件（分页参数等）
     * @return 活动列表
     */
    @Operation(
        summary = "获取活动列表",
        description = "根据时间排序，查询当前用户的活动列表，支持分页查询。" +
                "返回活动基本信息、参与人数、活动时间等。"
    )
    @GetMapping()
    public Result<PageUtil.PageResult<ForumActivityQueryVO>> getForumActivityList(@ModelAttribute @ParameterObject @Valid ForumActivityListDTO queryDTO) {
        IPage<ForumActivityQueryVO> iPage = forumActivityService.getForumActivityList(queryDTO);
        return Result.ok(PageUtil.toPageResult(iPage));
    }
    
    /**
     * 获取活动详情
     * @param id 活动ID
     * @return 活动详情
     */
    @Operation(
        summary = "获取活动详情",
        description = "根据ID获取活动详情，包括活动标题、内容、场地、时间、参与人数、" +
                "报名时间、图片等详细信息。"
    )
    @GetMapping("/{id}")
    public Result<ForumActivityDetailVO> getForumActivityDetail(
        @Parameter(description = "活动ID", example = "1")
        @PathVariable Long id
    ) {
        ForumActivityDetailVO vo = forumActivityService.getForumActivityDetail(id);
        return Result.ok(vo);
    }

    /**
     * 发布活动
     * @param dto 活动dto
     * @return 活动发布结果
     */

    @Operation(
        summary = "发布活动",
        description = "用户发布活动，活动发布成功后，用户将获得发布活动积分。"
    )
    @PostMapping(value = "/add")
    public Result<Void> createForumActivity(@Valid @RequestBody ForumActivityAddDTO dto) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumActivityService.createForumActivity(userId, dto);
        return Result.ok();
    }



    /**
     * 评论活动
     *  @param dto 评论dto
     *
     * */
    @Operation(
        summary = "评论活动",
        description = "用户评论活动，评论成功后，用户将获得评论积分。"
    )
    @PostMapping("/{id}/comments")
    public Result<Void> commentActivity(
        @Parameter(description = "活动ID", example = "1")
        @PathVariable Long id,
        @Valid @RequestBody ForumCommentAddDTO dto
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumActivityCommentService.createComment(userId,id,dtoToEntity(dto));
        return Result.ok();
    }
    private ForumActivityComment dtoToEntity(ForumCommentAddDTO dto) {
        ForumActivityComment comment = new ForumActivityComment();
        comment.setCommentContent(dto.getCommentContent());
        comment.setParentId(dto.getParentId());
        comment.setRootId(dto.getRootId());
        comment.setLevel(dto.getLevel());
        return comment;
    }

    /**
     * 获取活动评论列表
     * @param id 活动ID
     * @return 活动评论列表
     * */
    @GetMapping("/{id}/comments")
    @Operation(
        summary = "获取活动评论列表",
        description = "根据活动ID获取评论列表，支持分页查询，按照评论时间倒序排列。"
    )
    public Result<PageUtil.PageResult<ForumActivityCommentQueryVO>> getForumActivityCommentList(
        @Parameter(
            description = "活动ID",
            example = "1",
            required = true
        )
        @PathVariable Long id,
        @Parameter(
            description = "分页查询参数",
            schema = @Schema(implementation = ForumCommentListDTO.class)
        )
        @ModelAttribute @ParameterObject @Valid ForumCommentListDTO queryDTO
    ) {
        IPage<ForumActivityCommentQueryVO> pageResult = forumActivityCommentService.getForumActivityCommentPageList(id,queryDTO);
        return Result.ok(PageUtil.toPageResult(pageResult));
    }

    /**
     * 删除评论
     * @param commentId 评论ID
     *  删除评论
     * */
    @Operation(
            summary = "删除评论",
            description = "删除评论，只有评论创建者或管理员才能删除评论。"
    )
    @DeleteMapping(value = "/comments/{commentId}")
    public Result<Void> deleteComment(
            @Parameter(description = "评论ID", example = "1")
            @PathVariable Long commentId
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumActivityCommentService.deleteComment(userId,commentId);
        return Result.ok();
    }

}