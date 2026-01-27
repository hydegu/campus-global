package com.example.admin.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 审核记录VO
 */
@Data
@Schema(description = "审核记录VO")
public class AuditRecordVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "审核ID", example = "1")
	private Long id;

	@Schema(description = "审核编号", example = "AUD202401270001")
	private String auditNo;

	@Schema(description = "审核项: MERCHANT_SETTLE-商家入驻, WITHDRAW-提现, GOODS-商品上架, STAFF_APPLY-服务人员, RIDER_APPLY-骑手",
			example = "MERCHANT_SETTLE", allowableValues = {"MERCHANT_SETTLE", "WITHDRAW", "GOODS", "STAFF_APPLY", "RIDER_APPLY"})
	private String bizType;

	@Schema(description = "申请人ID", example = "1001")
	private Long applicantId;

	@Schema(description = "审核状态: 0-待审核, 1-审核通过, 2-审核拒绝", example = "0")
	private Integer status;

	@Schema(description = "审核人ID", example = "2001")
	private Long auditorId;

	@Schema(description = "审核意见", example = "申请材料齐全，符合入驻要求")
	private String remark;

	@Schema(description = "创建时间", example = "2024-01-27T10:30:00")
	private LocalDateTime createAt;

	@Schema(description = "更新时间", example = "2024-01-27T15:30:00")
	private LocalDateTime updateAt;

}