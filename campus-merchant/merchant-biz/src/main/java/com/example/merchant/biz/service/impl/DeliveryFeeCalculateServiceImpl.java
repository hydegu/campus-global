package com.example.merchant.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.admin.api.feign.RemoteAddressService;
import com.example.common.core.util.Result;
import com.example.merchant.api.entity.DeliveryFeeConfig;
import com.example.merchant.api.entity.DeliveryFeeRule;
import com.example.merchant.biz.mapper.DeliveryFeeConfigMapper;
import com.example.merchant.biz.mapper.DeliveryFeeRuleMapper;
import com.example.merchant.biz.service.DeliveryFeeCalculateService;
import com.example.order.api.dto.DeliveryCalculateDTO;
import com.example.order.api.vo.DeliveryCalculateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 配送费计算服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryFeeCalculateServiceImpl implements DeliveryFeeCalculateService {

	private final DeliveryFeeConfigMapper deliveryFeeConfigMapper;
	private final DeliveryFeeRuleMapper deliveryFeeRuleMapper;
	private final RemoteAddressService remoteAddressService;

	@Override
	public DeliveryCalculateVO calculateDeliveryFee(DeliveryCalculateDTO calculateDTO) {
		// 获取商家地址和收货地址的经纬度
		String merchantCoordinate = getMerchantCoordinate(calculateDTO.getMerchantId());
		String deliveryCoordinate = getDeliveryCoordinate(calculateDTO.getDeliveryAddressId());

		if (merchantCoordinate == null || deliveryCoordinate == null) {
			log.warn("获取地址坐标失败，使用默认计算方式");
			return calculateDefaultFee(calculateDTO);
		}

		// 计算距离（这里简化处理，实际应该调用地图服务）
		// 假设距离 = 3.5公里
		BigDecimal distance = calculateDistance(merchantCoordinate, deliveryCoordinate);

		// 查询启用的配送费配置
		LambdaQueryWrapper<DeliveryFeeConfig> configWrapper = Wrappers.lambdaQuery();
		configWrapper.eq(DeliveryFeeConfig::getStatus, 1);
		configWrapper.isNull(DeliveryFeeConfig::getDeleteAt);
		configWrapper.orderByDesc(DeliveryFeeConfig::getCreateAt);
		configWrapper.last("LIMIT 1");
		DeliveryFeeConfig config = deliveryFeeConfigMapper.selectOne(configWrapper);

		if (config == null) {
			log.warn("未找到配送费配置，使用默认计算方式");
			return calculateDefaultFee(calculateDTO);
		}

		// 查询配送费规则
		LambdaQueryWrapper<DeliveryFeeRule> ruleWrapper = Wrappers.lambdaQuery();
		ruleWrapper.eq(DeliveryFeeRule::getConfigId, config.getId());
		ruleWrapper.isNull(DeliveryFeeRule::getDeleteAt);
		ruleWrapper.orderByAsc(DeliveryFeeRule::getSortOrder);
		List<DeliveryFeeRule> rules = deliveryFeeRuleMapper.selectList(ruleWrapper);

		if (rules == null || rules.isEmpty()) {
			log.warn("未找到配送费规则，使用默认计算方式");
			return calculateDefaultFee(calculateDTO);
		}

		// 根据规则计算配送费
		return calculateFeeByRules(calculateDTO, config, rules, distance);
	}

	/**
	 * 获取商家地址经纬度
	 */
	private String getMerchantCoordinate(Long merchantId) {
		try {
			Result<String> result = remoteAddressService.getMerchantAddressCoordinate(merchantId);
			if (result != null && result.getData() != null) {
				return result.getData();
			}
		} catch (Exception e) {
			log.warn("获取商家地址失败，merchantId：{}", merchantId, e);
		}
		return null;
	}

	/**
	 * 获取收货地址经纬度
	 */
	private String getDeliveryCoordinate(Long deliveryAddressId) {
		try {
			Result<String> result = remoteAddressService.getAddressCoordinate(deliveryAddressId);
			if (result != null && result.getData() != null) {
				return result.getData();
			}
		} catch (Exception e) {
			log.warn("获取收货地址失败，deliveryAddressId：{}", deliveryAddressId, e);
		}
		return null;
	}

	/**
	 * 计算两点之间的距离（简化版，实际应该调用地图服务）
	 */
	private BigDecimal calculateDistance(String origin, String destination) {
		// 简化处理：解析坐标计算直线距离
		// 实际应该调用高德地图或百度地图的API
		String[] originCoords = origin.split(",");
		String[] destCoords = destination.split(",");
		
		if (originCoords.length != 2 || destCoords.length != 2) {
			return new BigDecimal("3.5"); // 默认距离
		}

		try {
			double lat1 = Double.parseDouble(originCoords[1]);
			double lng1 = Double.parseDouble(originCoords[0]);
			double lat2 = Double.parseDouble(destCoords[1]);
			double lng2 = Double.parseDouble(destCoords[0]);

			// 使用 Haversine 公式计算球面距离
			double earthRadius = 6371; // 地球半径（公里）
			double dLat = Math.toRadians(lat2 - lat1);
			double dLng = Math.toRadians(lng2 - lng1);
			double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
					* Math.sin(dLng / 2) * Math.sin(dLng / 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			double distance = earthRadius * c;

			// 实际路径距离约为直线距离的 1.3 倍
			return new BigDecimal(distance * 1.3).setScale(2, RoundingMode.HALF_UP);
		} catch (Exception e) {
			log.warn("计算距离失败，使用默认值", e);
			return new BigDecimal("3.5");
		}
	}

	/**
	 * 根据规则计算配送费
	 */
	private DeliveryCalculateVO calculateFeeByRules(DeliveryCalculateDTO calculateDTO,
													  DeliveryFeeConfig config,
													  List<DeliveryFeeRule> rules,
													  BigDecimal distance) {
		BigDecimal deliveryFee = config.getBaseFee();
		LocalTime now = LocalTime.now();

		// 1. 计算距离费用
		BigDecimal distanceFee = calculateDistanceFee(config, rules, distance);
		deliveryFee = deliveryFee.add(distanceFee);

		// 2. 计算时段附加费
		BigDecimal timeFee = calculateTimeFee(rules, now);
		deliveryFee = deliveryFee.add(timeFee);

		// 3. 计算预计送达时间（基于距离估算）
		int estimatedMinutes = calculateEstimatedTime(distance);
		LocalDateTime estimatedDeliveryTime = LocalDateTime.now().plusMinutes(estimatedMinutes);

		DeliveryCalculateVO vo = new DeliveryCalculateVO();
		vo.setDistance(distance);
		vo.setDeliveryFee(deliveryFee);
		vo.setEstimatedDeliveryTime(estimatedDeliveryTime);

		log.info("配送费计算完成，距离：{}km，基础费：{}元，距离费：{}元，时段费：{}元，总费用：{}元",
				distance, config.getBaseFee(), distanceFee, timeFee, deliveryFee);

		return vo;
	}

	/**
	 * 计算距离费用
	 */
	private BigDecimal calculateDistanceFee(DeliveryFeeConfig config, List<DeliveryFeeRule> rules, BigDecimal distance) {
		BigDecimal distanceFee = BigDecimal.ZERO;

		for (DeliveryFeeRule rule : rules) {
			if (rule.getRuleType() == 1 // 距离规则
					&& rule.getDistanceStart() != null
					&& rule.getPricePerKm() != null) {

				BigDecimal distanceStart = rule.getDistanceStart();
				BigDecimal distanceEnd = rule.getDistanceEnd() != null ? rule.getDistanceEnd() : new BigDecimal("9999");

				// 检查距离是否在规则范围内
				if (distance.compareTo(distanceStart) >= 0 && distance.compareTo(distanceEnd) <= 0) {
					// 超出基础距离的部分
					BigDecimal extraDistance = distance.subtract(config.getBaseDistance());
					if (extraDistance.compareTo(BigDecimal.ZERO) > 0) {
						// 计算超出部分的价格
						BigDecimal extraFee = extraDistance.multiply(rule.getPricePerKm())
								.setScale(2, RoundingMode.HALF_UP);
						distanceFee = distanceFee.add(extraFee);
					}
					break; // 找到匹配的规则后退出
				}
			}
		}

		return distanceFee;
	}

	/**
	 * 计算时段附加费
	 */
	private BigDecimal calculateTimeFee(List<DeliveryFeeRule> rules, LocalTime now) {
		BigDecimal timeFee = BigDecimal.ZERO;

		for (DeliveryFeeRule rule : rules) {
			if (rule.getRuleType() == 2 // 时间规则
					&& rule.getTimeStart() != null
					&& rule.getTimeEnd() != null
					&& rule.getTimeFee() != null) {

				LocalTime timeStart = rule.getTimeStart();
				LocalTime timeEnd = rule.getTimeEnd();

				// 检查当前时间是否在时段内
				boolean inTimeRange = false;
				if (timeStart.isBefore(timeEnd)) {
					// 同一天内（如 08:00 - 20:00）
					inTimeRange = now.isAfter(timeStart) && now.isBefore(timeEnd);
				} else {
					// 跨天（如 20:00 - 08:00）
					inTimeRange = now.isAfter(timeStart) || now.isBefore(timeEnd);
				}

				if (inTimeRange) {
					timeFee = timeFee.add(rule.getTimeFee());
					log.debug("匹配时段规则：{}-{}，附加费：{}元", timeStart, timeEnd, rule.getTimeFee());
				}
			}
		}

		return timeFee;
	}

	/**
	 * 计算预计送达时间（基于距离估算）
	 */
	private int calculateEstimatedTime(BigDecimal distance) {
		// 假设骑手平均速度为 20公里/小时 = 0.333公里/分钟
		BigDecimal speed = new BigDecimal("0.333");
		// 基础时间 + 距离时间
		int baseMinutes = 10; // 基础准备时间10分钟
		int travelMinutes = distance.divide(speed, 0, RoundingMode.CEILING).intValue();
		return baseMinutes + travelMinutes;
	}

	/**
	 * 默认配送费计算
	 */
	private DeliveryCalculateVO calculateDefaultFee(DeliveryCalculateDTO calculateDTO) {
		BigDecimal baseFee = new BigDecimal("5.00");
		BigDecimal goodsAmount = calculateDTO.getGoodsAmount();

		// 如果商品金额满30元，免配送费
		if (goodsAmount.compareTo(new BigDecimal("30.00")) >= 0) {
			baseFee = BigDecimal.ZERO;
		}

		// 默认距离3.5公里
		BigDecimal distance = new BigDecimal("3.5");

		DeliveryCalculateVO vo = new DeliveryCalculateVO();
		vo.setDistance(distance);
		vo.setDeliveryFee(baseFee);
		vo.setEstimatedDeliveryTime(LocalDateTime.now().plusMinutes(30));

		return vo;
	}
}