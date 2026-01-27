package com.example.order.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.order.api.dto.OrderAcceptDTO;
import com.example.order.api.dto.OrderCountQueryDTO;
import com.example.order.api.dto.OrderCreateDTO;
import com.example.order.api.dto.OrderDeliverDTO;
import com.example.order.api.dto.OrderPickupDTO;
import com.example.order.api.dto.OrderQueryDTO;
import com.example.order.api.vo.OrderCountVO;
import com.example.order.api.vo.OrderDetailVO;
import com.example.order.api.vo.OrderVO;

public interface OrderService {

	Long createOrder(OrderCreateDTO createDTO);

	void acceptOrder(OrderAcceptDTO acceptDTO);

	void pickupOrder(OrderPickupDTO pickupDTO);

	void deliverOrder(OrderDeliverDTO deliverDTO);

	void cancelOrder(Long orderId, Integer cancelType);

	OrderDetailVO getOrderDetail(Long orderId);

	Page<OrderVO> listOrders(OrderQueryDTO queryDTO);

	OrderCountVO countOrders(OrderCountQueryDTO queryDTO);
}
