package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "合伙人审核查询DTO")
public class PartnerAuditQueryDTO {

	@NotNull(message = "页码不能为空")
	@Min(value = 1, message = "页码最小为1")
	@Schema(description = "当前页码，从1开始", example = "1")
	private Integer page;

	@NotNull(message = "每页条数不能为空")
	@Min(value = 1, message = "每页条数最小为1")
	@Max(value = 100, message = "每页条数最大为100")
	@Schema(description = "每页条数", example = "10")
	private Integer size;

	@Schema(description = "合伙人姓名", example = "张三")
	private String partnerName;

	@Schema(description = "审核状态：0-待审核，1-审核通过，2-审核拒绝", example = "0")
	private Integer auditStatus;
}
