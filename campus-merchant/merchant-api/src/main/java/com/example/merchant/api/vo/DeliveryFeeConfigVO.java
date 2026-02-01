package com.example.merchant.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 配送费配置返回对象VO
 *
 * @author campus-merchant
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "DeliveryFeeConfigVO", description = "配送费配置返回对象VO")
public class DeliveryFeeConfigVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "配置ID", example = "1")
	private Long id;

	@Schema(description = "配置名称", example = "标准配送")
	private String configName;

	@Schema(description = "起步价", example = "5.00")
	private BigDecimal baseFee;

	@Schema(description = "起步距离(公里)", example = "3.00")
	private BigDecimal baseDistance;

	@Schema(description = "状态：0-禁用，1-启用", example = "1")
	private Integer status;

	@Schema(description = "创建时间", example = "2025-01-01 10:00:00")
	private LocalDateTime createAt;

	@Schema(description = "更新时间", example = "2025-01-01 10:00:00")
	private LocalDateTime updateAt;

	@Schema(description = "关联的配送费规则列表")
	private List<DeliveryFeeRuleVO> rules;

}