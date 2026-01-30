package com.example.order.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.core.exception.BusinessException;
import com.example.common.security.util.SecurityUtils;
import com.example.order.api.dto.OrderAcceptDTO;
import com.example.order.api.dto.OrderConfirmDTO;
import com.example.order.api.dto.OrderCountQueryDTO;
import com.example.order.api.dto.OrderCreateDTO;
import com.example.order.api.dto.OrderDeliverDTO;
import com.example.order.api.dto.OrderPayDTO;
import com.example.order.api.dto.OrderPickupDTO;
import com.example.order.api.dto.OrderQueryDTO;
import com.example.order.api.entity.OrderDelivery;
import com.example.order.api.entity.OrderMain;
import com.example.order.api.enums.OrderStatusEnum;
import com.example.order.api.enums.OrderTypeEnum;
import com.example.order.api.enums.PayStatusEnum;
import com.example.order.api.feign.RemoteUserService;
import com.example.common.core.constant.CommonConstants;
import com.example.common.core.util.Result;
import com.example.finance.api.dto.FinanceTransactionAddDTO;
import com.example.finance.api.enums.RelatedTypeEnum;
import com.example.finance.api.enums.TransactionTypeEnum;
import com.example.order.api.feign.RemoteFinanceService;
import com.example.order.api.vo.OrderCountVO;
import com.example.order.api.vo.OrderDetailVO;
import com.example.order.api.vo.OrderVO;
import com.example.order.biz.mapper.OrderDeliveryMapper;
import com.example.order.biz.mapper.OrderMainMapper;
import com.example.order.biz.service.AmapService;
import com.example.order.biz.service.DeliveryFeeService;
import com.example.order.biz.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderMainMapper orderMainMapper;
	private final OrderDeliveryMapper orderDeliveryMapper;
	private final RemoteUserService remoteUserService;
	private final RemoteFinanceService remoteFinanceService;
	private final DeliveryFeeService deliveryFeeService;
	private final AmapService amapService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long createOrder(OrderCreateDTO createDTO) {
		if (createDTO == null) {
			throw new BusinessException("INVALID_PARAM", "订单创建参数不能为空");
		}

		if (createDTO.getMerchantId() == null) {
			throw new BusinessException("INVALID_PARAM", "商家ID不能为空");
		}

		if (createDTO.getDeliveryAddressId() == null) {
			throw new BusinessException("INVALID_PARAM", "收货地址ID不能为空");
		}

		Long currentUserId = SecurityUtils.getCurrentUserId();
		if (currentUserId == null) {
			throw new BusinessException("NOT_LOGIN", "用户未登录");
		}

		BigDecimal goodsAmount = calculateGoodsAmount(createDTO);
		if (goodsAmount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new BusinessException("INVALID_PARAM", "商品金额必须大于0");
		}

		OrderMain orderMain = new OrderMain();
		orderMain.setOrderNo(generateOrderNo());
		orderMain.setOrderType(1);
		orderMain.setUserId(currentUserId);
		orderMain.setTotalAmount(goodsAmount);
		orderMain.setActualAmount(goodsAmount);
		orderMain.setPayStatus(PayStatusEnum.UNPAID.getCode());
		orderMain.setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode());
		orderMain.setServiceProviderType(1);
		orderMain.setServiceProviderId(createDTO.getMerchantId());
		orderMain.setRemark(createDTO.getRemark());
		orderMain.setVersion(0);

		orderMainMapper.insert(orderMain);

		OrderDelivery orderDelivery = new OrderDelivery();
		orderDelivery.setOrderId(orderMain.getId());
		orderDelivery.setMerchantId(createDTO.getMerchantId());
		orderDelivery.setDeliveryAddressId(createDTO.getDeliveryAddressId());
		orderDelivery.setGoodsAmount(goodsAmount);

		orderDeliveryMapper.insert(orderDelivery);

		log.info("订单创建成功，订单ID：{}，订单编号：{}，用户ID：{}", 
				orderMain.getId(), orderMain.getOrderNo(), currentUserId);

		return orderMain.getId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void acceptOrder(OrderAcceptDTO acceptDTO) {
		if (acceptDTO == null || acceptDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		Long riderId = SecurityUtils.getCurrentUserId();
		if (riderId == null) {
			throw new BusinessException("NOT_LOGIN", "骑手未登录");
		}

		OrderMain orderMain = orderMainMapper.selectById(acceptDTO.getOrderId());
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.TAKEOUT.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		if (!OrderStatusEnum.WAIT_ACCEPT.getCode().equals(orderMain.getOrderStatus())) {
			throw new BusinessException("INVALID_ORDER_STATUS", "订单状态不允许接单");
		}

		orderMain.setOrderStatus(OrderStatusEnum.WAIT_PICKUP.getCode());
		orderMain.setServiceProviderType(2);
		orderMain.setServiceProviderId(riderId);
		orderMain.setEstimatedStartTime(LocalDateTime.now());

		orderMainMapper.updateById(orderMain);

		OrderDelivery orderDelivery = orderDeliveryMapper.selectOne(
				Wrappers.lambdaQuery(OrderDelivery.class).eq(OrderDelivery::getOrderId, acceptDTO.getOrderId())
		);
		if (orderDelivery != null) {
			orderDelivery.setRiderId(riderId);
			orderDeliveryMapper.updateById(orderDelivery);
		}

		log.info("骑手接单成功，订单ID：{}，骑手ID：{}", acceptDTO.getOrderId(), riderId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pickupOrder(OrderPickupDTO pickupDTO) {
		if (pickupDTO == null || pickupDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		Long riderId = SecurityUtils.getCurrentUserId();
		if (riderId == null) {
			throw new BusinessException("NOT_LOGIN", "骑手未登录");
		}

		OrderMain orderMain = orderMainMapper.selectById(pickupDTO.getOrderId());
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.TAKEOUT.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		if (!OrderStatusEnum.WAIT_PICKUP.getCode().equals(orderMain.getOrderStatus())) {
			throw new BusinessException("INVALID_ORDER_STATUS", "订单状态不允许取货");
		}

		if (!riderId.equals(orderMain.getServiceProviderId())) {
			throw new BusinessException("PERMISSION_DENIED", "无权操作该订单");
		}

		orderMain.setOrderStatus(OrderStatusEnum.DELIVERING.getCode());

		orderMainMapper.updateById(orderMain);

		log.info("骑手取货成功，订单ID：{}，骑手ID：{}", pickupDTO.getOrderId(), riderId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deliverOrder(OrderDeliverDTO deliverDTO) {
		if (deliverDTO == null || deliverDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		Long riderId = SecurityUtils.getCurrentUserId();
		if (riderId == null) {
			throw new BusinessException("NOT_LOGIN", "骑手未登录");
		}

		OrderMain orderMain = orderMainMapper.selectById(deliverDTO.getOrderId());
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.TAKEOUT.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		if (!OrderStatusEnum.DELIVERING.getCode().equals(orderMain.getOrderStatus())) {
			throw new BusinessException("INVALID_ORDER_STATUS", "订单状态不允许送达");
		}

		if (!riderId.equals(orderMain.getServiceProviderId())) {
			throw new BusinessException("PERMISSION_DENIED", "无权操作该订单");
		}

		orderMain.setOrderStatus(OrderStatusEnum.DELIVERED.getCode());
		orderMain.setActualDeliveryTime(LocalDateTime.now());

		orderMainMapper.updateById(orderMain);

		log.info("骑手送达成功，订单ID：{}，骑手ID：{}", deliverDTO.getOrderId(), riderId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancelOrder(Long orderId, Integer cancelType) {
		if (orderId == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		OrderMain orderMain = orderMainMapper.selectById(orderId);
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.TAKEOUT.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		if (OrderStatusEnum.CANCELLED.getCode().equals(orderMain.getOrderStatus()) 
				|| OrderStatusEnum.COMPLETED.getCode().equals(orderMain.getOrderStatus())) {
			throw new BusinessException("INVALID_ORDER_STATUS", "订单状态不允许取消");
		}

		orderMain.setOrderStatus(OrderStatusEnum.CANCELLED.getCode());
		orderMain.setCancelType(cancelType);
		orderMain.setCancelTime(LocalDateTime.now());

		orderMainMapper.updateById(orderMain);

		log.info("订单取消成功，订单ID：{}，取消类型：{}", orderId, cancelType);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void payOrder(OrderPayDTO payDTO) {
		if (payDTO == null || payDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		OrderMain orderMain = orderMainMapper.selectById(payDTO.getOrderId());
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.TAKEOUT.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		if (!PayStatusEnum.UNPAID.getCode().equals(orderMain.getPayStatus())) {
			throw new BusinessException("INVALID_ORDER_STATUS", "订单状态不允许支付");
		}

		// ========== 记录用户消费流水 ==========
		FinanceTransactionAddDTO userTransaction = new FinanceTransactionAddDTO();
		userTransaction.setTransactionNo(generateTransactionNo());
		userTransaction.setUserId(orderMain.getUserId());
		userTransaction.setTransactionType(TransactionTypeEnum.CONSUMPTION.getCode());
		userTransaction.setAmount(orderMain.getActualAmount().negate()); // 消费为负数
		userTransaction.setRelatedType(RelatedTypeEnum.ORDER.getCode());
		userTransaction.setRelatedId(orderMain.getId());
		userTransaction.setRemark("外卖订单支付：" + orderMain.getOrderNo());
		
		Result<Long> userResult = remoteFinanceService.createTransaction(userTransaction);
		if (userResult.getCode() != CommonConstants.SUCCESS) {
			throw new BusinessException("FINANCE_ERROR", "记录用户消费流水失败");
		}
		
		// ========== 记录各方收益流水 ==========
		BigDecimal providerIncome = orderMain.getEstimatedProviderIncome();
		BigDecimal partnerIncome = orderMain.getEstimatedPartnerIncome();
		BigDecimal platformIncome = orderMain.getEstimatedPlatformIncome();
		
		// 服务提供方收入
		if (providerIncome != null && providerIncome.compareTo(BigDecimal.ZERO) > 0) {
			FinanceTransactionAddDTO providerTransaction = new FinanceTransactionAddDTO();
			providerTransaction.setTransactionNo(generateTransactionNo());
			providerTransaction.setUserId(orderMain.getServiceProviderId());
			providerTransaction.setTransactionType(TransactionTypeEnum.PAYMENT.getCode());
			providerTransaction.setAmount(providerIncome); // 收入为正数
			providerTransaction.setRelatedType(RelatedTypeEnum.ORDER.getCode());
			providerTransaction.setRelatedId(orderMain.getId());
			providerTransaction.setRemark("外卖订单商家收入：" + orderMain.getOrderNo());
			
			remoteFinanceService.createTransaction(providerTransaction);
		}
		
		// 合伙人收入
		if (partnerIncome != null && partnerIncome.compareTo(BigDecimal.ZERO) > 0) {
			FinanceTransactionAddDTO partnerTransaction = new FinanceTransactionAddDTO();
			partnerTransaction.setTransactionNo(generateTransactionNo());
			partnerTransaction.setUserId(orderMain.getPartnerId());
			partnerTransaction.setTransactionType(TransactionTypeEnum.PAYMENT.getCode());
			partnerTransaction.setAmount(partnerIncome);
			partnerTransaction.setRelatedType(RelatedTypeEnum.ORDER.getCode());
			partnerTransaction.setRelatedId(orderMain.getId());
			partnerTransaction.setRemark("外卖订单合伙人收入：" + orderMain.getOrderNo());
			
			remoteFinanceService.createTransaction(partnerTransaction);
		}
		
		// 平台收入
		if (platformIncome != null && platformIncome.compareTo(BigDecimal.ZERO) > 0) {
			FinanceTransactionAddDTO platformTransaction = new FinanceTransactionAddDTO();
			platformTransaction.setTransactionNo(generateTransactionNo());
			platformTransaction.setUserId(0L); // 平台ID设为0
			platformTransaction.setTransactionType(TransactionTypeEnum.PAYMENT.getCode());
			platformTransaction.setAmount(platformIncome);
			platformTransaction.setRelatedType(RelatedTypeEnum.ORDER.getCode());
			platformTransaction.setRelatedId(orderMain.getId());
			platformTransaction.setRemark("外卖订单平台收入：" + orderMain.getOrderNo());
			
			remoteFinanceService.createTransaction(platformTransaction);
		}

		// 更新订单支付状态
		orderMain.setPayStatus(PayStatusEnum.PAID.getCode());
		orderMain.setPayMethod(payDTO.getPayMethod());
		orderMain.setPayTime(LocalDateTime.now());
		orderMain.setPayChannelNo(payDTO.getPayChannelNo());
		orderMain.setOrderStatus(OrderStatusEnum.WAIT_ACCEPT.getCode());

		orderMainMapper.updateById(orderMain);

		log.info("订单支付成功，订单ID：{}，支付方式：{}，支付流水号：{}",
				payDTO.getOrderId(), payDTO.getPayMethod(), payDTO.getPayChannelNo());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirmOrder(OrderConfirmDTO confirmDTO) {
		if (confirmDTO == null || confirmDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		OrderMain orderMain = orderMainMapper.selectById(confirmDTO.getOrderId());
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.TAKEOUT.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		if (!OrderStatusEnum.DELIVERED.getCode().equals(orderMain.getOrderStatus())) {
			throw new BusinessException("INVALID_ORDER_STATUS", "订单状态不允许确认收货");
		}

		// 更新订单状态为已完成
		orderMain.setOrderStatus(OrderStatusEnum.COMPLETED.getCode());

		orderMainMapper.updateById(orderMain);

		log.info("外卖订单确认收货成功，订单ID：{}", confirmDTO.getOrderId());
	}

	@Override
	public OrderDetailVO getOrderDetail(Long orderId) {
		if (orderId == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		OrderMain orderMain = orderMainMapper.selectById(orderId);
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.TAKEOUT.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		OrderDelivery orderDelivery = orderDeliveryMapper.selectOne(
				Wrappers.lambdaQuery(OrderDelivery.class).eq(OrderDelivery::getOrderId, orderId)
		);

		OrderDetailVO vo = new OrderDetailVO();
		BeanUtils.copyProperties(orderMain, vo);

		if (orderDelivery != null) {
			vo.setDeliveryFee(orderDelivery.getDeliveryFee());
			if (orderDelivery.getRiderId() != null) {
				vo.setRiderName(getUserName(orderDelivery.getRiderId()));
				vo.setRiderPhone(getUserPhone(orderDelivery.getRiderId()));
			}
		}

		vo.setUserName(getUserName(orderMain.getUserId()));
		vo.setUserPhone(getUserPhone(orderMain.getUserId()));

		return vo;
	}

	@Override
	public Page<OrderVO> listOrders(OrderQueryDTO queryDTO) {
		if (queryDTO == null) {
			queryDTO = new OrderQueryDTO();
		}

		if (queryDTO.getPageNum() == null || queryDTO.getPageNum() < 1) {
			queryDTO.setPageNum(1);
		}

		if (queryDTO.getPageSize() == null || queryDTO.getPageSize() < 1) {
			queryDTO.setPageSize(10);
		}

		LambdaQueryWrapper<OrderMain> wrapper = buildQueryWrapper(queryDTO);

		IPage<OrderMain> orderPage = orderMainMapper.selectPage(
				new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()), wrapper
		);

		Page<OrderVO> resultPage = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
		resultPage.setTotal(orderPage.getTotal());

		if (orderPage.getRecords().isEmpty()) {
			return resultPage;
		}

		resultPage.setRecords(orderPage.getRecords().stream().map(order -> {
			OrderVO vo = new OrderVO();
			BeanUtils.copyProperties(order, vo);
			vo.setServiceProviderName(getServiceProviderName(order.getServiceProviderId(), order.getServiceProviderType()));
			return vo;
		}).collect(java.util.stream.Collectors.toList()));

		return resultPage;
	}

	private String generateOrderNo() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) 
				+ UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}

	private BigDecimal calculateGoodsAmount(OrderCreateDTO createDTO) {
		if (createDTO.getGoodsList() == null || createDTO.getGoodsList().isEmpty()) {
			return BigDecimal.ZERO;
		}

		return createDTO.getGoodsList().stream()
				.map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private LambdaQueryWrapper<OrderMain> buildQueryWrapper(OrderQueryDTO queryDTO) {
		LambdaQueryWrapper<OrderMain> wrapper = Wrappers.lambdaQuery();

		if (queryDTO.getQueryType() != null) {
			if (queryDTO.getQueryType() == 1) {
				Long userId = SecurityUtils.getCurrentUserId();
				if (userId == null) {
					throw new BusinessException("NOT_LOGIN", "用户未登录");
				}
				wrapper.eq(OrderMain::getUserId, userId);
			} else if (queryDTO.getQueryType() == 2) {
				Long serviceProviderId = queryDTO.getServiceProviderId();
				if (serviceProviderId == null) {
					serviceProviderId = SecurityUtils.getCurrentUserId();
					if (serviceProviderId == null) {
						throw new BusinessException("NOT_LOGIN", "用户未登录");
					}
				}
				if (queryDTO.getServiceProviderType() != null) {
					wrapper.eq(OrderMain::getServiceProviderType, queryDTO.getServiceProviderType());
				}
				wrapper.eq(OrderMain::getServiceProviderId, serviceProviderId);
			} else if (queryDTO.getQueryType() == 3) {
				Long serviceProviderId = queryDTO.getServiceProviderId();
				if (serviceProviderId == null) {
					serviceProviderId = SecurityUtils.getCurrentUserId();
					if (serviceProviderId == null) {
						throw new BusinessException("NOT_LOGIN", "用户未登录");
					}
				}
				wrapper.eq(OrderMain::getServiceProviderType, 1);
				wrapper.eq(OrderMain::getServiceProviderId, serviceProviderId);
			}
		}

		if (queryDTO.getOrderNo() != null) {
			wrapper.like(OrderMain::getOrderNo, queryDTO.getOrderNo());
		}

		if (queryDTO.getOrderStatus() != null) {
			wrapper.eq(OrderMain::getOrderStatus, queryDTO.getOrderStatus());
		}

		if (queryDTO.getOrderType() != null) {
			wrapper.eq(OrderMain::getOrderType, queryDTO.getOrderType());
		}

		if (queryDTO.getStartTime() != null) {
			wrapper.ge(OrderMain::getCreateAt, queryDTO.getStartTime());
		}

		if (queryDTO.getEndTime() != null) {
			wrapper.le(OrderMain::getCreateAt, queryDTO.getEndTime());
		}

		wrapper.orderByDesc(OrderMain::getCreateAt);

		return wrapper;
	}

	private String getUserName(Long userId) {
		if (userId == null) {
			return "未知用户";
		}
		try {
			com.example.common.core.util.Result<com.example.admin.api.dto.UserInfo> result = 
					remoteUserService.getUserInfoById(userId);
			if (result != null && result.getData() != null) {
				return result.getData().getNickname();
			}
		} catch (Exception e) {
			log.warn("获取用户名称失败，userId：{}", userId, e);
		}
		return "用户" + userId;
	}

	private String getUserPhone(Long userId) {
		if (userId == null) {
			return null;
		}
		try {
			com.example.common.core.util.Result<com.example.admin.api.dto.UserInfo> result = 
					remoteUserService.getUserInfoById(userId);
			if (result != null && result.getData() != null) {
				return result.getData().getPhone();
			}
		} catch (Exception e) {
			log.warn("获取用户电话失败，userId：{}", userId, e);
		}
		return null;
	}

	private String getServiceProviderName(Long serviceProviderId, Integer serviceProviderType) {
		if (serviceProviderId == null) {
			return "未知服务提供方";
		}
		return getUserName(serviceProviderId);
	}

	@Override
	public OrderCountVO countOrders(OrderCountQueryDTO queryDTO) {
		if (queryDTO == null) {
			queryDTO = new OrderCountQueryDTO();
		}

		Long userId = queryDTO.getUserId();
		if (userId == null) {
			userId = SecurityUtils.getCurrentUserId();
			if (userId == null) {
				throw new BusinessException("NOT_LOGIN", "用户未登录");
			}
		}

		LambdaQueryWrapper<OrderMain> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(OrderMain::getUserId, userId);

		if (queryDTO.getOrderType() != null) {
			wrapper.eq(OrderMain::getOrderType, queryDTO.getOrderType());
		}

		if (queryDTO.getOrderStatus() != null) {
			wrapper.eq(OrderMain::getOrderStatus, queryDTO.getOrderStatus());
		}

		if (queryDTO.getStartDate() != null) {
			wrapper.ge(OrderMain::getCreateAt, queryDTO.getStartDate());
		}

		if (queryDTO.getEndDate() != null) {
			wrapper.le(OrderMain::getCreateAt, queryDTO.getEndDate());
		}

		Long totalCount = orderMainMapper.selectCount(wrapper);

		Long waitPayCount = orderMainMapper.selectCount(
				Wrappers.lambdaQuery(OrderMain.class)
						.eq(OrderMain::getUserId, userId)
						.eq(OrderMain::getOrderStatus, OrderStatusEnum.WAIT_PAY.getCode())
						.apply(queryDTO.getOrderType() != null, "order_type = {0}", queryDTO.getOrderType())
						.ge(queryDTO.getStartDate() != null, OrderMain::getCreateAt, queryDTO.getStartDate())
						.le(queryDTO.getEndDate() != null, OrderMain::getCreateAt, queryDTO.getEndDate())
		);

		Long waitAcceptCount = orderMainMapper.selectCount(
				Wrappers.lambdaQuery(OrderMain.class)
						.eq(OrderMain::getUserId, userId)
						.eq(OrderMain::getOrderStatus, OrderStatusEnum.WAIT_ACCEPT.getCode())
						.apply(queryDTO.getOrderType() != null, "order_type = {0}", queryDTO.getOrderType())
						.ge(queryDTO.getStartDate() != null, OrderMain::getCreateAt, queryDTO.getStartDate())
						.le(queryDTO.getEndDate() != null, OrderMain::getCreateAt, queryDTO.getEndDate())
		);

		Long waitPickupCount = orderMainMapper.selectCount(
				Wrappers.lambdaQuery(OrderMain.class)
						.eq(OrderMain::getUserId, userId)
						.eq(OrderMain::getOrderStatus, OrderStatusEnum.WAIT_PICKUP.getCode())
						.apply(queryDTO.getOrderType() != null, "order_type = {0}", queryDTO.getOrderType())
						.ge(queryDTO.getStartDate() != null, OrderMain::getCreateAt, queryDTO.getStartDate())
						.le(queryDTO.getEndDate() != null, OrderMain::getCreateAt, queryDTO.getEndDate())
		);

		Long deliveringCount = orderMainMapper.selectCount(
				Wrappers.lambdaQuery(OrderMain.class)
						.eq(OrderMain::getUserId, userId)
						.eq(OrderMain::getOrderStatus, OrderStatusEnum.DELIVERING.getCode())
						.apply(queryDTO.getOrderType() != null, "order_type = {0}", queryDTO.getOrderType())
						.ge(queryDTO.getStartDate() != null, OrderMain::getCreateAt, queryDTO.getStartDate())
						.le(queryDTO.getEndDate() != null, OrderMain::getCreateAt, queryDTO.getEndDate())
		);

		Long deliveredCount = orderMainMapper.selectCount(
				Wrappers.lambdaQuery(OrderMain.class)
						.eq(OrderMain::getUserId, userId)
						.eq(OrderMain::getOrderStatus, OrderStatusEnum.DELIVERED.getCode())
						.apply(queryDTO.getOrderType() != null, "order_type = {0}", queryDTO.getOrderType())
						.ge(queryDTO.getStartDate() != null, OrderMain::getCreateAt, queryDTO.getStartDate())
						.le(queryDTO.getEndDate() != null, OrderMain::getCreateAt, queryDTO.getEndDate())
		);

		Long cancelledCount = orderMainMapper.selectCount(
				Wrappers.lambdaQuery(OrderMain.class)
						.eq(OrderMain::getUserId, userId)
						.eq(OrderMain::getOrderStatus, OrderStatusEnum.CANCELLED.getCode())
						.apply(queryDTO.getOrderType() != null, "order_type = {0}", queryDTO.getOrderType())
						.ge(queryDTO.getStartDate() != null, OrderMain::getCreateAt, queryDTO.getStartDate())
						.le(queryDTO.getEndDate() != null, OrderMain::getCreateAt, queryDTO.getEndDate())
		);

		Long completedCount = orderMainMapper.selectCount(
				Wrappers.lambdaQuery(OrderMain.class)
						.eq(OrderMain::getUserId, userId)
						.eq(OrderMain::getOrderStatus, OrderStatusEnum.COMPLETED.getCode())
						.apply(queryDTO.getOrderType() != null, "order_type = {0}", queryDTO.getOrderType())
						.ge(queryDTO.getStartDate() != null, OrderMain::getCreateAt, queryDTO.getStartDate())
						.le(queryDTO.getEndDate() != null, OrderMain::getCreateAt, queryDTO.getEndDate())
		);

		Long afterSaleCount = orderMainMapper.selectCount(
				Wrappers.lambdaQuery(OrderMain.class)
						.eq(OrderMain::getUserId, userId)
						.eq(OrderMain::getOrderStatus, OrderStatusEnum.AFTER_SALE.getCode())
						.apply(queryDTO.getOrderType() != null, "order_type = {0}", queryDTO.getOrderType())
						.ge(queryDTO.getStartDate() != null, OrderMain::getCreateAt, queryDTO.getStartDate())
						.le(queryDTO.getEndDate() != null, OrderMain::getCreateAt, queryDTO.getEndDate())
		);

		return OrderCountVO.builder()
				.totalCount(totalCount)
				.waitPayCount(waitPayCount)
				.waitAcceptCount(waitAcceptCount)
				.waitPickupCount(waitPickupCount)
				.deliveringCount(deliveringCount)
				.deliveredCount(deliveredCount)
				.cancelledCount(cancelledCount)
				.completedCount(completedCount)
				.afterSaleCount(afterSaleCount)
				.build();
	}

	/**
	 * 生成流水号
	 * 格式：FT + yyyyMMddHHmmss + 8位随机大写字符
	 */
	private String generateTransactionNo() {
		return "FT" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
			   + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}
}