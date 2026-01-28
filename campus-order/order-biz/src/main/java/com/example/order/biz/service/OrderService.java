package com.example.order.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.order.api.dto.*;
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
}
