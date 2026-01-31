package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 创建合伙人用户DTO
 */
@Data
@Schema(description = "创建合伙人用户DTO")
public class CreatePartnerUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户名", example = "partner001", required = true)
	@NotBlank(message = "用户名不能为空")
	private String username;

	@Schema(description = "密码", example = "123456", required = true)
	@NotBlank(message = "密码不能为空")
	private String password;

	@Schema(description = "手机号", example = "13800138000", required = true)
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
	private String phone;

	@Schema(description = "邮箱", example = "partner@example.com")
	private String email;

	@Schema(description = "昵称", example = "合伙人001")
	private String nickname;

	@Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
	private String avatar;

	@Schema(description = "状态，1-正常，0-停用", example = "1")
	private Integer status;

	@Schema(description = "合伙人姓名", example = "王五", required = true)
	@NotBlank(message = "合伙人姓名不能为空")
	private String partnerName;

	@Schema(description = "银行卡号", example = "6222021234567890123")
	private String cardNumber;

	@Schema(description = "分佣比例(%)", example = "5.00")
	private BigDecimal commissionRate;

	@Schema(description = "上级合伙人ID，0表示无上级", example = "0")
	private Long parentId;

	@Schema(description = "余额", example = "0.00")
	private BigDecimal balance;

	@Schema(description = "累计总收入", example = "0.00")
	private BigDecimal totalAmount;

	@Schema(description = "角色ID列表", example = "[1, 2, 3]")
	private List<Long> roleIds;
}
