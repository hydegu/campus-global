package com.example.order.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.order.api.dto.ErrandAcceptDTO;
import com.example.order.api.dto.ErrandCreateDTO;
import com.example.order.api.dto.ErrandDeliverDTO;
import com.example.order.api.dto.ErrandPickupDTO;
import com.example.order.api.dto.ErrandQueryDTO;
import com.example.order.api.vo.ErrandDetailVO;
import com.example.order.api.vo.ErrandListVO;

public interface ErrandService {

	Long createErrand(ErrandCreateDTO createDTO);

	void acceptErrand(ErrandAcceptDTO acceptDTO);

	void pickupErrand(ErrandPickupDTO pickupDTO);

	void deliverErrand(ErrandDeliverDTO deliverDTO);

	void cancelErrand(Long orderId, Integer cancelType);

	ErrandDetailVO getErrandDetail(Long orderId);

	Page<ErrandListVO> listErrands(ErrandQueryDTO queryDTO);

	/**
	 * 检查服务分类是否有关联的订单
	 * @param serviceTypeId 服务分类ID
	 * @return true-有关联订单，false-无关联订单
	 */
	boolean hasOrdersByServiceTypeId(Long serviceTypeId);
}
