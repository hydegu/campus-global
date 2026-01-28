package com.example.order.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.util.Result;
import com.example.common.docs.annotation.StandardApiResponses;
import com.example.order.api.dto.OrderAcceptDTO;
import com.example.order.api.dto.OrderConfirmDTO;
import com.example.order.api.dto.OrderCountQueryDTO;
import com.example.order.api.dto.OrderCreateDTO;
import com.example.order.api.dto.OrderDeliverDTO;
import com.example.order.api.dto.OrderPickupDTO;
import com.example.order.api.dto.OrderQueryDTO;
import com.example.order.api.vo.OrderCountVO;
import com.example.order.api.vo.OrderDetailVO;
import com.example.order.api.vo.OrderVO;
import com.example.order.biz.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@StandardApiResponses
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "订单管理", description = "外卖订单创建、接单、取货、送达、取消等功能")
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/create")
	@Operation(summary = "创建订单", description = "用户创建外卖订单，支持选择商家、收货地址、商品等")
	public Result<Long> createOrder(@Valid @RequestBody OrderCreateDTO createDTO) {
		Long orderId = orderService.createOrder(createDTO);
		return Result.ok(orderId);
	}

	@PostMapping("/accept")
	@Operation(summary = "骑手接单", description = "骑手接单，更新订单状态为待取货")
	public Result<Void> acceptOrder(@Valid @RequestBody OrderAcceptDTO acceptDTO) {
		orderService.acceptOrder(acceptDTO);
		return Result.ok();
	}

	@PostMapping("/pickup")
	@Operation(summary = "骑手取货", description = "骑手取货，更新订单状态为配送中")
	public Result<Void> pickupOrder(@Valid @RequestBody OrderPickupDTO pickupDTO) {
		orderService.pickupOrder(pickupDTO);
		return Result.ok();
	}

	@PostMapping("/deliver")
	@Operation(summary = "骑手送达", description = "骑手送达，更新订单状态为已送达")
	public Result<Void> deliverOrder(@Valid @RequestBody OrderDeliverDTO deliverDTO) {
		orderService.deliverOrder(deliverDTO);
		return Result.ok();
	}

	@PostMapping("/confirm")
	@Operation(summary = "确认收货", description = "用户确认收货，更新订单状态为已完成")
	public Result<Void> confirmOrder(@Valid @RequestBody OrderConfirmDTO confirmDTO) {
		orderService.confirmOrder(confirmDTO);
		return Result.ok();
	}

	@PostMapping("/cancel")
	@Operation(summary = "取消订单", description = "取消订单，支持用户、商家、骑手、超时等取消类型")
	public Result<Void> cancelOrder(@RequestParam Long orderId, @RequestParam Integer cancelType) {
		orderService.cancelOrder(orderId, cancelType);
		return Result.ok();
	}

	@GetMapping("/list")
	@Operation(summary = "分页查询订单列表", description = "根据查询条件分页查询订单列表，支持按订单编号、状态、类型、时间范围等条件筛选")
	public Result<Page<OrderVO>> listOrders(@Valid @ParameterObject OrderQueryDTO queryDTO) {
		Page<OrderVO> page = orderService.listOrders(queryDTO);
		return Result.ok(page);
	}

	@GetMapping("/count")
	@Operation(summary = "查询订单数统计", description = "查询用户的订单数统计，支持按订单类型、状态、时间范围等条件筛选")
	public Result<OrderCountVO> countOrders(@Valid @ParameterObject OrderCountQueryDTO queryDTO) {
		OrderCountVO countVO = orderService.countOrders(queryDTO);
		return Result.ok(countVO);
	}

	@GetMapping("/{id}")
	@Operation(summary = "查询订单详情", description = "根据订单ID查询订单详细信息")
	public Result<OrderDetailVO> getOrderDetail(@PathVariable Long id) {
		OrderDetailVO detailVO = orderService.getOrderDetail(id);
		return Result.ok(detailVO);
	}
}
