package com.example.admin.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商家余额更新DTO
 */
@Data
@Schema(description = "用户余额更新DTO")
public class MerchantBalanceUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户ID（商家/骑手/合伙人）", required = true, example = "1")
	@NotNull(message = "用户ID不能为空")
	private Long userId;

	@Schema(description = "用户类型：1-商家，2-骑手，3-合伙人", required = true, example = "1")
	@NotNull(message = "用户类型不能为空")
	private Integer userType;

	@Schema(description = "金额", required = true, example = "100.00")
	@NotNull(message = "金额不能为空")
	private BigDecimal amount;

	@Schema(description = "更新类型：1-余额更新，2-累计总收入更新", required = true, example = "1")
	@NotNull(message = "更新类型不能为空")
	private Integer updateType;
}