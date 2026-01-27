package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建审核记录DTO
 */
@Data
@Schema(description = "创建审核记录DTO")
@Builder
public class CreateAuditRecordDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "审核项: MERCHANT_SETTLE-商家入驻, WITHDRAW-提现, GOODS-商品上架, STAFF_APPLY-服务人员, RIDER_APPLY-骑手",
			example = "MERCHANT_SETTLE", required = true, allowableValues = {"MERCHANT_SETTLE", "WITHDRAW", "GOODS", "STAFF_APPLY", "RIDER_APPLY"})
	@NotBlank(message = "审核项不能为空")
	private String bizType;

	@Schema(description = "申请人ID", example = "1001", required = true)
	@NotNull(message = "申请人ID不能为空")
	private Long applicantId;

}