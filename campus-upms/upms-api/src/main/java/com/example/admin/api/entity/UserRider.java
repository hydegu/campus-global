package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "骑手用户表")
public class UserRider implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "主键ID")
	private Long id;

	@TableField("base_user_id")
	@Schema(description = "关联base_user表ID")
	private Long baseUserId;

	@Schema(description = "性别")
	private Integer gender;

	@TableField("emergency_contact_name")
	@Schema(description = "紧急联系人姓名")
	private String emergencyContactName;

	@TableField("emergency_contact_phone")
	@Schema(description = "紧急联系人电话")
	private String emergencyContactPhone;

	@TableField("real_name")
	@Schema(description = "真实姓名")
	private String realName;

	@Schema(description = "身份证号")
	private String idCard;

	@TableField("id_card_front")
	@Schema(description = "身份证正面url")
	private String idCardFront;

	@TableField("id_card_back")
	@Schema(description = "身份证反面url")
	private String idCardBack;

	@TableField("card_number")
	@Schema(description = "银行卡号")
	private String cardNumber;

	@Schema(description = "微信openid")
	private String openid;

	@TableField("audit_id")
	@Schema(description = "联表审核表id（审核骑手资质）")
	private Long auditId;

	@TableField("balance")
	@Schema(description = "余额")
	private BigDecimal balance;

	@TableField("commission_total")
	@Schema(description = "累计总收入")
	private BigDecimal totalAmount;

	@TableField("address_id")
	@Schema(description = "联表地址id")
	private Long addressId;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "更新时间")
	private LocalDateTime updateTime;

	@TableLogic
	@TableField("delete_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "删除时间")
	private LocalDateTime deleteTime;
}
