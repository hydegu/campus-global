package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "审核记录表")
public class AuditRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "审核ID")
	private Long id;

	@TableField("audit_no")
	@Schema(description = "审核编号")
	private String auditNo;

	@TableField("biz_type")
	@Schema(description = "审核项:MERCHANT_SETTLE-商家入驻 WITHDRAW-提现 GOODS-商品上架 STAFF_APPLY-服务人员 RIDER_APPLY-骑手")
	private String bizType;

	@TableField("applicant_id")
	@Schema(description = "申请人ID")
	private Long applicantId;

	@Schema(description = "审核状态:0-待审核 1-审核通过 2-审核拒绝")
	private Integer status;

	@TableField("auditor_id")
	@Schema(description = "审核人ID")
	private Long auditorId;

	@TableField("remark")
	@Schema(description = "审核意见")
	private String remark;

	@TableField(value = "create_at", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "创建时间")
	private LocalDateTime createAt;

	@TableField(value = "update_at", fill = FieldFill.UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "更新时间")
	private LocalDateTime updateAt;

	@TableLogic
	@TableField("delete_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "软删除时间")
	private LocalDateTime deleteAt;
}
