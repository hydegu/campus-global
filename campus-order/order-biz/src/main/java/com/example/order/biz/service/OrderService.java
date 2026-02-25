package com.example.order.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.order.api.dto.*;
import com.example.order.api.enums.TimeoutTypeEnum;
import com.example.order.api.vo.OrderCountVO;
import com.example.order.api.vo.OrderDetailVO;
import com.example.order.api.vo.OrderVO;

public interface OrderService {

	Long createOrder(OrderCreateDTO createDTO);

	void acceptOrder(OrderAcceptDTO acceptDTO);

	void pickupOrder(OrderPickupDTO pickupDTO);

	void deliverOrder(OrderDeliverDTO deliverDTO);

	void cancelOrder(Long orderId, Integer cancelType);

	void payOrder(OrderPayDTO payDTO);

	void confirmOrder(OrderConfirmDTO confirmDTO);

	OrderDetailVO getOrderDetail(Long orderId);

	Page<OrderVO> listOrders(OrderQueryDTO queryDTO);

	OrderCountVO countOrders(OrderCountQueryDTO queryDTO);


	/**
	 * 检查订单是否待支付
	 */
	boolean isPendingPayment(Long orderId);

	/**
	 * 检查订单是否待接单
	 */
	boolean isPendingAccept(Long orderId);

	/**
	 * 检查订单是否待确认
	 */
	boolean isPendingConfirm(Long orderId);

	/**
	 * 取消超时订单
	 *
	 * @param orderId     订单ID
	 * @param timeoutType 超时类型
	 */
	void cancelTimeoutOrder(Long orderId, TimeoutTypeEnum timeoutType);

	/**
	 * 自动确认订单
	 */
	void autoConfirmOrder(Long orderId);
}
