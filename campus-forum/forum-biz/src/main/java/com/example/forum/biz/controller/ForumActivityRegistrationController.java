package com.example.forum.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.core.util.Result;
import com.example.common.security.util.SecurityUtils;
import com.example.forum.api.dto.forumActivity.ForumActivityRegistrationApprovalDTO;
import com.example.forum.api.dto.forumActivity.ForumActivityRegistrationListDTO;
import com.example.forum.api.vo.ForumActivityRegistrationQueryVO;
import com.example.forum.api.vo.UserRegisteredActivityVO;
import com.example.forum.biz.service.ForumActivityRegistrationService;
import com.example.forum.biz.utils.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动报名控制器
 */
@Tag(name = "活动报名管理", description = "活动报名相关接口")
@RestController
@RequestMapping("/api/my")
@RequiredArgsConstructor
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)

public class ForumActivityRegistrationController {

    private final ForumActivityRegistrationService forumActivityRegistrationService;

    /**
     * 查询当前用户报名的活动列表
     * @return 活动报名列表
     */
    @GetMapping("/activities/joined")
    @Operation(summary = "查询当前用户报名的活动列表", description = "查询当前用户报名的所有活动")
    public Result<List<UserRegisteredActivityVO>> getMyRegisteredActivities() {
        // 使用SecurityUtils获取当前用户ID
        Long currentUserId = SecurityUtils.getCurrentUserId();
        List<UserRegisteredActivityVO> registeredActivities = forumActivityRegistrationService.getRegisteredActivitiesByUserId(currentUserId);
        return Result.ok(registeredActivities);
    }

    /**
     * 报名活动
     * @param id 活动ID
     * @return 报名结果
     */
    @Operation(
            summary = "报名活动",
            description = "用户报名活动，活动报名成功后，用户将获得活动积分。"
    )
    @PostMapping("/{id}/join")
    public Result<Void> registerForActivity(
            @Parameter(description = "活动ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumActivityRegistrationService.registerForActivity(userId,id);
        return Result.ok();
    }

    /**
     * 取消报名活动
     * @param id 活动ID
     * @return 取消报名结果
     */
    @Operation(
            summary = "取消报名活动",
            description = "用户取消报名活动，取消报名成功后，用户将获得取消报名积分。"
    )
    @PostMapping("/{id}/cancel")
    public Result<Void> cancelRegistration(
            @Parameter(description = "活动ID", example = "1")
            @PathVariable Long id
    ) {
        Long userId = SecurityUtils.getCurrentUserId();
        forumActivityRegistrationService.cancelRegistration(userId,id);
        return Result.ok();
    }

    /**
     * 获取报名人审核列表
     * @param queryDTO 查询条件（分页参数等）
     * @return 报名人审核列表
     */
    @Operation(
            summary = "获取报名人审核列表",
            description = "根据活动ID查询报名人审核列表，包括报名人信息、审核状态等。"
    )
    @GetMapping("/{activityId}/registrations")
    public Result<PageUtil.PageResult<ForumActivityRegistrationQueryVO>> getForumActivityRegistrationList(
            @Parameter(description = "活动ID", example = "1")
            @PathVariable Long activityId,
            @ModelAttribute @ParameterObject @Valid ForumActivityRegistrationListDTO queryDTO
    ) {
        IPage<ForumActivityRegistrationQueryVO> iPage = forumActivityRegistrationService.getRegisteredActivitiesByActivityId(activityId, queryDTO);
        return Result.ok(PageUtil.toPageResult(iPage));
    }

    /**
     * 审批报名活动
     * 审批报名活动DTO
     * @return 审批结果
     */
    @Operation(
            summary = "审批报名活动",
            description = "根据活动ID和报名人ID审批报名活动，包括通过和拒绝。"
    )
    @PostMapping("/registrations")
    public Result<Void> approveForumActivityRegistration(
            @ParameterObject @Valid ForumActivityRegistrationApprovalDTO approvalDTO
    ) {
        forumActivityRegistrationService.approveRegistration(approvalDTO);
        return Result.ok();
    }


}