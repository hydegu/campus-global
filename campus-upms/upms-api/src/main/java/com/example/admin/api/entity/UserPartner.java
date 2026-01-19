package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "合伙人表")
public class UserPartner implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "合伙人ID")
	private Long id;

	@TableField("base_user_id")
	@Schema(description = "关联base_user表ID")
	private Long baseUserId;

	@TableField("partner_name")
	@Schema(description = "合伙人姓名")
	private String partnerName;

	@TableField("invite_code")
	@Schema(description = "推广码(邀请合伙人)")
	private String inviteCode;

	@TableField("invite_code_path")
	@Schema(description = "推广码相对路径")
	private String inviteCodePath;

	@TableField("card_number")
	@Schema(description = "银行卡号")
	private String cardNumber;

	@TableField("commission_rate")
	@Schema(description = "分佣比例(%)")
	private BigDecimal commissionRate;

	@TableField("parent_id")
	@Schema(description = "上级合伙人ID(0代表无上级)")
	private Long parentId;

	@TableField("audit_id")
	@Schema(description = "关联的审核表id")
	private Long auditId;

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
