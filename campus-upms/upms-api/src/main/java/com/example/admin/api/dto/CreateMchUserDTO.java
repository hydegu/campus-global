package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 创建商家用户DTO
 */
@Data
@Schema(description = "创建商家用户DTO")
public class CreateMchUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户名", example = "mch001", required = true)
	@NotBlank(message = "用户名不能为空")
	private String username;

	@Schema(description = "密码", example = "123456", required = true)
	@NotBlank(message = "密码不能为空")
	private String password;

	@Schema(description = "手机号", example = "13800138000", required = true)
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
	private String phone;

	@Schema(description = "邮箱", example = "mch@example.com")
	private String email;

	@Schema(description = "昵称", example = "美食店")
	private String nickname;

	@Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "状态，1-正常，0-停用", example = "1")
	private Integer status;

	@Schema(description = "商户名", example = "张三美食店", required = true)
	@NotBlank(message = "商户名不能为空")
	private String mchName;

	@Schema(description = "联系人姓名", example = "张三")
	private String contactName;

	@Schema(description = "商户logo", example = "https://example.com/logo.jpg")
	private String logo;

	@Schema(description = "营业执照路径，多个路径以英文逗号分隔", example = "https://example.com/license1.jpg,https://example.com/license2.jpg")
	private String businessLicenseUrls;

	@Schema(description = "合伙人ID", example = "1")
	private Long partnerId;

	@Schema(description = "身份证号", example = "110101199001011234")
	private String idCard;

	@Schema(description = "最低起送金额", example = "20.00")
	private BigDecimal minimumOrderAmount;

	@Schema(description = "银行卡号", example = "6222021234567890123")
	private String cardNumber;

	@Schema(description = "余额", example = "0.00")
	private BigDecimal balance;

	@Schema(description = "累计总收入", example = "0.00")
	private BigDecimal totalAmount;

	@Schema(description = "角色ID列表", example = "[1, 2, 3]")
	private List<Long> roleIds;
}