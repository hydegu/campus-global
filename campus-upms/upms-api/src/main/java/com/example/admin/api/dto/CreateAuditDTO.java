package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "创建审核记录DTO")
public class CreateAuditDTO {

	@NotBlank(message = "业务类型不能为空")
	@Schema(description = "业务类型（如：ACTIVITY_PUBLISH）", requiredMode = Schema.RequiredMode.REQUIRED, example = "ACTIVITY_PUBLISH")
	private String bizType;

	@NotNull(message = "申请人ID不能为空")
	@Schema(description = "申请人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
	private Long applicantId;
}
