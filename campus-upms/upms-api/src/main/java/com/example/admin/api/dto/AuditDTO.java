package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "通用审核DTO")
public class AuditDTO {

	@NotNull(message = "审核结果不能为空")
	@Min(value = 1, message = "审核结果必须为1或2")
	@Max(value = 2, message = "审核结果必须为1或2")
	@Schema(description = "审核结果（1-审核通过，2-审核拒绝）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Integer auditStatus;

	@Size(max = 500, message = "审核意见最多500字符")
	@Schema(description = "审核意见（可选）", example = "资质齐全，审核通过")
	private String auditOpinion;
}
