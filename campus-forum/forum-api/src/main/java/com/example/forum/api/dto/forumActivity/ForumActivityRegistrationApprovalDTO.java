package com.example.forum.api.dto.forumActivity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class ForumActivityRegistrationApprovalDTO {

    /**
     * 审批状态（0：待审核，1：已通过，2：已拒绝）
     */
    @NotNull(message = "审批状态不能为空")
    @Schema(description = "审批状态（0：待审核，1：已通过，2：已拒绝）", example = "1")
    private Integer approvalStatus;
    /**
     * 报名人ID
     */
    @NotNull(message = "报名人ID不能为空")
    @Schema(description = "报名人ID", example = "1")
     private Long registrationId;
}
