package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * 审核记录查询DTO
 */
@Data
@Schema(description = "审核记录查询DTO")
public class AuditRecordQueryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Min(value = 1, message = "页码最小为1")
	@Schema(description = "当前页码，从1开始", example = "1")
	private Integer page = 1;

	@Min(value = 1, message = "每页条数最小为1")
	@Max(value = 100, message = "每页条数最大为100")
	@Schema(description = "每页条数", example = "10")
	private Integer size = 10;

	@Schema(description = "审核状态: 0-待审核, 1-审核通过, 2-审核拒绝", example = "0")
	private Integer status;

	@Schema(description = "审核项: MERCHANT_SETTLE-商家入驻, WITHDRAW-提现, GOODS-商品上架, STAFF_APPLY-服务人员, RIDER_APPLY-骑手",
			example = "MERCHANT_SETTLE", allowableValues = {"MERCHANT_SETTLE", "WITHDRAW", "GOODS", "STAFF_APPLY", "RIDER_APPLY"})
	private String bizType;

}