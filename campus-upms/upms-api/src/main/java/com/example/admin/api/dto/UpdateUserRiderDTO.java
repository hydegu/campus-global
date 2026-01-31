package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "编辑骑手用户DTO")
public class UpdateUserRiderDTO implements Serializable {

	@Schema(description = "用户名", example = "rider001")
	private String username;

	@Schema(description = "手机号", example = "13800138000")
	private String phone;

	@Schema(description = "头像", example = "https://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "状态", example = "1")
	private Integer status;

	@Schema(description = "真实姓名", example = "李四")
	private String riderName;

	@Schema(description = "身份证号", example = "110101199001011234")
	private String idCard;

	@Schema(description = "性别", example = "1")
	private Integer gender;

	@Schema(description = "银行卡号", example = "6222021234567890123")
	private String cardNumber;

	@Schema(description = "省", example = "北京市")
	private String province;

	@Schema(description = "市", example = "北京市")
	private String city;

	@Schema(description = "区", example = "海淀区")
	private String district;

	@Schema(description = "详细地址", example = "中关村大街1号")
	private String detailAddress;

	@Schema(description = "紧急联系人姓名", example = "张三")
	private String emergencyContactName;

	@Schema(description = "紧急联系人电话", example = "13900139000")
	private String emergencyContactPhone;

	@Schema(description = "身份证正面URL", example = "https://example.com/id-front.jpg")
	private String idCardFrontUrl;

	@Schema(description = "身份证反面URL", example = "https://example.com/id-back.jpg")
	private String idCardBackUrl;

	@Schema(description = "分佣比例(%)", example = "20.00")
	private BigDecimal commissionRate;

	@Schema(description = "角色ID列表", example = "[1, 2, 3]")
	private List<Long> roleIds;
}
