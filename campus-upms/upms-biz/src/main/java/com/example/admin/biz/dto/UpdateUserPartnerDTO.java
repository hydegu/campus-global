package com.example.admin.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "编辑合伙人用户DTO")
public class UpdateUserPartnerDTO implements Serializable {

	@Schema(description = "用户名", example = "partner001")
	private String username;

	@Schema(description = "手机号", example = "13800138000")
	private String phone;

	@Schema(description = "头像", example = "https://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "状态", example = "1")
	private Integer status;

	@Schema(description = "合伙人姓名", example = "赵六")
	private String partnerName;

	@Schema(description = "银行卡号", example = "6222021234567890123")
	private String cardNumber;

	@Schema(description = "分佣比例(%)", example = "10.00")
	private BigDecimal commissionRate;

	@Schema(description = "上级合伙人ID", example = "1")
	private Long parentId;
}
