package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "用户状态修改DTO")
public class UserStatusDTO {

	@NotNull(message = "状态不能为空")
	@Min(value = 0, message = "状态值最小为0")
	@Max(value = 1, message = "状态值最大为1")
	@Schema(description = "目标状态：0-拉黑，1-解封", example = "0")
	private Integer status;

	@Schema(description = "操作原因（可选但建议填写）", example = "违规操作")
	private String reason;
}
