package com.example.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Schema(description = "商家用户表")
public class UserMch implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	@Schema(description = "主键ID")
	private Long id;

	@TableField("base_user_id")
	@Schema(description = "关联base_user表ID")
	private Long baseUserId;

	@TableField("mch_name")
	@Schema(description = "商户名")
	private String mchName;

	@Schema(description = "商户logo")
	private String logo;

	@TableField("contact_name")
	@Schema(description = "联系人姓名")
	private String contactName;

	@TableField("business_license_urls")
	@Schema(description = "营业执照路径，以\",\"分割")
	private String businessLicenseUrls;

	@TableField("partner_id")
	@Schema(description = "合伙人id")
	private Long partnerId;

	@Schema(description = "身份证号")
	private String idCard;

	@TableField("minimum_order_amount")
	@Schema(description = "最低起送金额(默认0)")
	private BigDecimal minimumOrderAmount;

	@TableField("card_number")
	@Schema(description = "银行卡号")
	private String cardNumber;

	@TableField("audit_id")
	@Schema(description = "审查表id（审查商户资格）")
	private Long auditId;

	@TableField("business_start_time")
	@JsonFormat(pattern = "HH:mm:ss")
	@Schema(description = "开始营业时间")
	private LocalTime businessStartTime;

	@TableField("business_end_time")
	@JsonFormat(pattern = "HH:mm:ss")
	@Schema(description = "结束营业时间")
	private LocalTime businessEndTime;

	@TableField("is_open")
	@Schema(description = "是否营业 0-休息 1-营业（默认休息）")
	private Integer isOpen;

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
