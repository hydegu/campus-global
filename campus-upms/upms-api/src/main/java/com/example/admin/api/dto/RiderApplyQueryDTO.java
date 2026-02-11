package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "骑手申请查询DTO")
public class RiderApplyQueryDTO {

	@Min(value = 1, message = "页码最小为1")
	@Schema(description = "页码", example = "1")
	private Integer page = 1;

	@Min(value = 1, message = "每页条数最小为1")
	@Max(value = 100, message = "每页条数最大为100")
	@Schema(description = "每页条数", example = "10")
	private Integer size = 10;

	@Schema(description = "骑手姓名", example = "李四骑手")
	private String riderName;

	@Schema(description = "骑手手机号", example = "13811111111")
	private String riderPhone;

	@Schema(description = "审核状态：0-待审核，1-已通过，2-已拒绝", example = "0")
	private Integer auditStatus;
}
