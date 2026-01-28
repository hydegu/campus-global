package com.example.merchant.biz.service;

import com.example.order.api.dto.DeliveryCalculateDTO;
import com.example.order.api.vo.DeliveryCalculateVO;

/**
 * 配送费计算服务接口
 */
public interface DeliveryFeeCalculateService {

	/**
	 * 计算配送费
	 * @param calculateDTO 配送费计算参数
	 * @return 配送费计算结果
	 */
	DeliveryCalculateVO calculateDeliveryFee(DeliveryCalculateDTO calculateDTO);
}