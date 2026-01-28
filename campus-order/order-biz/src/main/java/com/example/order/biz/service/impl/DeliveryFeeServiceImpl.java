package com.example.order.biz.service.impl;

import com.example.admin.api.feign.RemoteAddressService;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.util.Result;
import com.example.order.api.dto.DeliveryCalculateDTO;
import com.example.order.api.feign.RemoteMerchantService;
import com.example.order.api.vo.DeliveryCalculateVO;
import com.example.order.biz.service.AmapService;
import com.example.order.biz.service.DeliveryFeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryFeeServiceImpl implements DeliveryFeeService {

	private final RemoteMerchantService remoteMerchantService;
	private final RemoteAddressService remoteAddressService;
	private final AmapService amapService;

	@Override
	public DeliveryCalculateVO calculateDeliveryFee(DeliveryCalculateDTO calculateDTO) {
		if (calculateDTO == null) {
			throw new BusinessException("INVALID_PARAM", "配送费计算参数不能为空");
		}

		if (calculateDTO.getMerchantId() == null) {
			throw new BusinessException("INVALID_PARAM", "商家ID不能为空");
		}

		if (calculateDTO.getDeliveryAddressId() == null) {
			throw new BusinessException("INVALID_PARAM", "收货地址ID不能为空");
		}

		if (calculateDTO.getGoodsAmount() == null || calculateDTO.getGoodsAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new BusinessException("INVALID_PARAM", "商品金额必须大于0");
		}

		String origin = getMerchantAddress(calculateDTO.getMerchantId());
		String destination = getDeliveryAddress(calculateDTO.getDeliveryAddressId());

		BigDecimal distance = amapService.getDistance(origin, destination);
		if (distance == null || distance.compareTo(BigDecimal.ZERO) <= 0) {
			throw new BusinessException("CALCULATE_FAILED", "获取配送距离失败");
		}

		Integer duration = amapService.getDuration(origin, destination);
		if (duration == null || duration <= 0) {
			duration = 30;
		}

		BigDecimal deliveryFee = calculateFeeByConfig(calculateDTO, distance);

		LocalDateTime estimatedDeliveryTime = LocalDateTime.now().plusMinutes(duration);

		DeliveryCalculateVO vo = new DeliveryCalculateVO();
		vo.setDistance(distance);
		vo.setDeliveryFee(deliveryFee);
		vo.setEstimatedDeliveryTime(estimatedDeliveryTime);

		log.info("配送费计算成功，商家ID：{}，距离：{}km，配送费：{}元", 
				calculateDTO.getMerchantId(), distance, deliveryFee);

		return vo;
	}

	private String getMerchantAddress(Long merchantId) {
		if (merchantId == null) {
			return null;
		}
		try {
			Result<String> result = remoteAddressService.getMerchantAddressCoordinate(merchantId);
			if (result != null && result.getData() != null) {
				return result.getData();
			}
		} catch (Exception e) {
			log.warn("获取商家地址失败，merchantId：{}，使用默认值", merchantId, e);
		}
		// 降级处理：返回默认值（天安门坐标）
		return "116.397128,39.916527";
	}

	private String getDeliveryAddress(Long deliveryAddressId) {
		if (deliveryAddressId == null) {
			return null;
		}
		try {
			Result<String> result = remoteAddressService.getAddressCoordinate(deliveryAddressId);
			if (result != null && result.getData() != null) {
				return result.getData();
			}
		} catch (Exception e) {
			log.warn("获取收货地址失败，deliveryAddressId：{}，使用默认值", deliveryAddressId, e);
		}
		// 降级处理：返回默认值（北京火车站坐标）
		return "116.407526,39.904030";
	}

	private BigDecimal calculateFeeByConfig(DeliveryCalculateDTO calculateDTO, BigDecimal distance) {
		try {
			Result<DeliveryCalculateVO> result =
					remoteMerchantService.calculateDeliveryFee(calculateDTO);
			if (result != null && result.getData() != null) {
				return result.getData().getDeliveryFee();
			}
		} catch (Exception e) {
			log.warn("调用商家服务获取配送费配置失败，使用默认计算方式", e);
		}

		return calculateDefaultFee(distance);
	}

	private BigDecimal calculateDefaultFee(BigDecimal distance) {
		BigDecimal baseFee = new BigDecimal("5.00");
		BigDecimal baseDistance = new BigDecimal("3.00");
		BigDecimal pricePerKm = new BigDecimal("2.00");

		if (distance.compareTo(baseDistance) <= 0) {
			return baseFee;
		}

		BigDecimal extraDistance = distance.subtract(baseDistance);
		BigDecimal extraFee = extraDistance.multiply(pricePerKm);

		return baseFee.add(extraFee);
	}
}
