package com.example.merchant.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 配送费规则返回对象VO
 *
 * @author campus-merchant
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "DeliveryFeeRuleVO", description = "配送费规则返回对象VO")
public class DeliveryFeeRuleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "规则ID", example = "1")
	private Integer id;

	@Schema(description = "关联配置ID", example = "1")
	private Long configId;

	@Schema(description = "规则类型：1-距离，2-时间", example = "1")
	private Integer ruleType;

	@Schema(description = "规则类型名称", example = "距离规则")
	private String ruleTypeName;

	@Schema(description = "起始距离(公里)", example = "3.00")
	private BigDecimal distanceStart;

	@Schema(description = "结束距离(公里)", example = "10.00")
	private BigDecimal distanceEnd;

	@Schema(description = "每公里价格", example = "1.50")
	private BigDecimal pricePerKm;

	@Schema(description = "开始时间", example = "08:00:00")
	private LocalTime timeStart;

	@Schema(description = "结束时间", example = "20:00:00")
	private LocalTime timeEnd;

	@Schema(description = "时段附加费", example = "2.00")
	private BigDecimal timeFee;

	@Schema(description = "时段类型：1-白天，2-夜间", example = "1")
	private Integer timeType;

	@Schema(description = "时段类型名称", example = "白天")
	private String timeTypeName;

	@Schema(description = "排序", example = "10")
	private Integer sortOrder;

	@Schema(description = "创建时间", example = "2025-01-01 10:00:00")
	private LocalDateTime createAt;

	@Schema(description = "更新时间", example = "2025-01-01 10:00:00")
	private LocalDateTime updateAt;

}