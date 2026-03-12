package com.example.order.biz.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.admin.api.feign.RemoteUserService;
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
import com.example.order.api.enums.*;
import com.example.admin.api.dto.MerchantBalanceUpdateDTO;
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
import com.example.order.biz.processor.OrderProcessor;
import com.example.order.biz.producer.OrderTimeoutProducer;
import com.example.order.biz.service.AmapService;
import com.example.order.biz.service.DeliveryFeeService;
import com.example.order.biz.service.OrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import com.example.service.api.entity.CommissionConfig;
import com.example.service.api.feign.RemoteCommissionConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.example.order.api.constants.OrderTimeoutConstants.ACCEPT_TIMEOUT_MS;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderMainMapper orderMainMapper;
	private final OrderDeliveryMapper orderDeliveryMapper;
	private final RemoteUserService remoteUserService;
	private final RemoteFinanceService remoteFinanceService;
	private final RemoteCommissionConfigService commissionConfigService;
	private final OrderProcessor orderProcessor;
	private final OrderTimeoutProducer orderTimeoutProducer;
	private final RedissonClient redissonClient;

	@Override
	@Transactional(rollbackFor = Exception.class)
	@SentinelResource(value = "createOrder", blockHandler = "createOrderBlockHandler")
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

		// ========== 计算各方收益 ==========
		// 获取分佣配置（外卖订单使用商家分佣）
		Map<Integer, CommissionConfig> commissionConfigs = commissionConfigService.getCommissionConfigs(null).getData();

		// 商家收益：订单金额 × 商家分佣比例 (configType=3)
		BigDecimal providerIncome = BigDecimal.ZERO;
		CommissionConfig merchantConfig = commissionConfigs.get(3);
		if (merchantConfig != null && merchantConfig.getCommissionRate() != null) {
			providerIncome = goodsAmount.multiply(merchantConfig.getCommissionRate())
					.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
		}

		// 合伙人收益：订单金额 × 合伙人分佣比例 (configType=4)
		BigDecimal partnerIncome = BigDecimal.ZERO;
		CommissionConfig partnerConfig = commissionConfigs.get(4);
		if (partnerConfig != null && partnerConfig.getCommissionRate() != null) {
			partnerIncome = goodsAmount.multiply(partnerConfig.getCommissionRate())
					.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
		}

		// 平台收益：订单金额 - 商家收益 - 合伙人收益
		BigDecimal platformIncome = goodsAmount.subtract(providerIncome).subtract(partnerIncome);
		if (platformIncome.compareTo(BigDecimal.ZERO) < 0) {
			platformIncome = BigDecimal.ZERO;
		}

		OrderMain orderMain = new OrderMain();
		orderMain.setOrderNo(generateOrderNo());
		orderMain.setOrderType(1);
		orderMain.setUserId(currentUserId);
		orderMain.setTotalAmount(goodsAmount);
		orderMain.setActualAmount(goodsAmount);
		orderMain.setPayStatus(PayStatusEnum.UNPAID.getCode());
		orderMain.setOrderStatus(OrderStatusEnum.WAIT_ACCEPT.getCode());
		orderMain.setServiceProviderType(1);
		orderMain.setServiceProviderId(createDTO.getMerchantId());
		orderMain.setRemark(createDTO.getRemark());
		orderMain.setVersion(0);
		// 设置各方预计收益
		orderMain.setEstimatedProviderIncome(providerIncome);
		orderMain.setEstimatedPartnerIncome(partnerIncome);
		orderMain.setEstimatedPlatformIncome(platformIncome);

		orderMainMapper.insert(orderMain);

		OrderDelivery orderDelivery = new OrderDelivery();
		orderDelivery.setOrderId(orderMain.getId());
		orderDelivery.setMerchantId(createDTO.getMerchantId());
		orderDelivery.setDeliveryAddressId(createDTO.getDeliveryAddressId());
		orderDelivery.setGoodsAmount(goodsAmount);

		orderDeliveryMapper.insert(orderDelivery);

		log.info("订单创建成功，订单ID：{}，订单编号：{}，用户ID：{}", 
				orderMain.getId(), orderMain.getOrderNo(), currentUserId);
		// 事务提交后发送消息
		TransactionSynchronizationManager.registerSynchronization(
				new TransactionSynchronization() {
					@Override
					public void afterCommit() {
						orderTimeoutProducer.sendPayTimeoutMessage(orderMain.getId(), orderMain.getOrderNo(), currentUserId);
						orderTimeoutProducer.sendAcceptTimeoutMessage(orderMain.getId(), orderMain.getOrderNo(), currentUserId);
					}
				}
		);
		return orderMain.getId();
	}

	/**
	 * createOrder 限流降级处理
	 */
	public Long createOrderBlockHandler(OrderCreateDTO createDTO, BlockException e) {
		throw new BusinessException("RATE_LIMITED", "订单创建请求过于频繁，请稍后重试");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@SentinelResource(value = "acceptOrder", blockHandler = "acceptOrderBlockHandler")
	public void acceptOrder(OrderAcceptDTO acceptDTO) {
		if (acceptDTO == null || acceptDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		Long riderId = SecurityUtils.getCurrentUserId();
		if (riderId == null) {
			throw new BusinessException("NOT_LOGIN", "骑手未登录");
		}

		// Redisson 分布式锁：防止多骑手并发抢单
		String lockKey = "order:accept:" + acceptDTO.getOrderId();
		RLock lock = redissonClient.getLock(lockKey);

		try {
			// 尝试获取锁，等待时间3秒，锁自动过期时间10秒（看门狗会自动续期）
			boolean locked = lock.tryLock(3, 10, TimeUnit.SECONDS);
			if (!locked) {
				throw new BusinessException("ORDER_ACCEPTTING", "订单正在被其他骑手抢接，请稍后重试");
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

			orderProcessor.process(orderMain, OrderEventsEnum.ACCEPT);
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
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new BusinessException("LOCK_INTERRUPTED", "获取锁时被中断");
		} finally {
			// 只有当前线程持有锁时才释放
			if (lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}

	/**
	 * acceptOrder 限流降级处理
	 */
	public void acceptOrderBlockHandler(OrderAcceptDTO acceptDTO, BlockException e) {
		throw new BusinessException("RATE_LIMITED", "接单请求过于频繁，请稍后重试");
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

		orderProcessor.process(orderMain, OrderEventsEnum.PICK_UP);

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

		orderProcessor.process(orderMain, OrderEventsEnum.ARRIVE);
		// 事务提交后发送消息
		TransactionSynchronizationManager.registerSynchronization(
				new TransactionSynchronization() {
					@Override
					public void afterCommit() {
						orderTimeoutProducer.sendConfirmTimeoutMessage(orderMain.getId(), orderMain.getOrderNo(), SecurityUtils.getCurrentUserId());
					}
				}
		);
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

		orderProcessor.process(orderMain, OrderEventsEnum.CANCEL);
		orderMain.setCancelType(cancelType);
		orderMain.setCancelTime(LocalDateTime.now());

		orderMainMapper.updateById(orderMain);

		log.info("订单取消成功，订单ID：{}，取消类型：{}", orderId, cancelType);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@SentinelResource(value = "payOrder", blockHandler = "payOrderBlockHandler")
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

		// 查询外卖订单配送信息，获取骑手ID和配送费
		OrderDelivery orderDelivery = orderDeliveryMapper.selectOne(
				Wrappers.lambdaQuery(OrderDelivery.class).eq(OrderDelivery::getOrderId, orderMain.getId())
		);

		// 骑手配送费收入
		if (orderDelivery != null && orderDelivery.getRiderId() != null && orderDelivery.getDeliveryFee() != null
				&& orderDelivery.getDeliveryFee().compareTo(BigDecimal.ZERO) > 0) {
			FinanceTransactionAddDTO riderTransaction = new FinanceTransactionAddDTO();
			riderTransaction.setTransactionNo(generateTransactionNo());
			riderTransaction.setUserId(orderDelivery.getRiderId());
			riderTransaction.setTransactionType(TransactionTypeEnum.PAYMENT.getCode());
			riderTransaction.setAmount(orderDelivery.getDeliveryFee());
			riderTransaction.setRelatedType(RelatedTypeEnum.ORDER.getCode());
			riderTransaction.setRelatedId(orderMain.getId());
			riderTransaction.setRemark("外卖订单骑手配送费：" + orderMain.getOrderNo());

			remoteFinanceService.createTransaction(riderTransaction);

			// 更新骑手余额
			MerchantBalanceUpdateDTO riderBalanceDTO = new MerchantBalanceUpdateDTO();
			riderBalanceDTO.setUserId(orderDelivery.getRiderId());
			riderBalanceDTO.setUserType(2);
			riderBalanceDTO.setAmount(orderDelivery.getDeliveryFee());
			riderBalanceDTO.setUpdateType(1);
			remoteUserService.updateUserBalance(riderBalanceDTO);

			// 更新骑手累计收益
			riderBalanceDTO.setUpdateType(2);
			remoteUserService.updateUserBalance(riderBalanceDTO);
		}

		// 服务提供方收入（商家）
		if (providerIncome != null && providerIncome.compareTo(BigDecimal.ZERO) > 0) {
			FinanceTransactionAddDTO providerTransaction = new FinanceTransactionAddDTO();
			providerTransaction.setTransactionNo(generateTransactionNo());
			providerTransaction.setUserId(orderMain.getServiceProviderId());
			providerTransaction.setTransactionType(TransactionTypeEnum.PAYMENT.getCode());
			providerTransaction.setAmount(providerIncome);
			providerTransaction.setRelatedType(RelatedTypeEnum.ORDER.getCode());
			providerTransaction.setRelatedId(orderMain.getId());
			providerTransaction.setRemark("外卖订单商家收入：" + orderMain.getOrderNo());

			remoteFinanceService.createTransaction(providerTransaction);

			// 更新商家余额
			MerchantBalanceUpdateDTO balanceUpdateDTO = new MerchantBalanceUpdateDTO();
			balanceUpdateDTO.setUserId(orderMain.getServiceProviderId());
			balanceUpdateDTO.setUserType(1);
			balanceUpdateDTO.setAmount(providerIncome);
			balanceUpdateDTO.setUpdateType(1);
			remoteUserService.updateUserBalance(balanceUpdateDTO);

			// 更新商家累计收益
			balanceUpdateDTO.setUpdateType(2);
			remoteUserService.updateUserBalance(balanceUpdateDTO);
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

			// 更新合伙人余额
			MerchantBalanceUpdateDTO partnerBalanceDTO = new MerchantBalanceUpdateDTO();
			partnerBalanceDTO.setUserId(orderMain.getPartnerId());
			partnerBalanceDTO.setUserType(3);
			partnerBalanceDTO.setAmount(partnerIncome);
			partnerBalanceDTO.setUpdateType(1);
			remoteUserService.updateUserBalance(partnerBalanceDTO);

			// 更新合伙人累计收益
			partnerBalanceDTO.setUpdateType(2);
			remoteUserService.updateUserBalance(partnerBalanceDTO);
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

		orderMainMapper.updateById(orderMain);

		log.info("订单支付成功，订单ID：{}，支付方式：{}，支付流水号：{}",
				payDTO.getOrderId(), payDTO.getPayMethod(), payDTO.getPayChannelNo());
	}

	/**
	 * payOrder 限流降级处理
	 */
	public void payOrderBlockHandler(OrderPayDTO payDTO, BlockException e) {
		throw new BusinessException("RATE_LIMITED", "支付请求过于频繁，请稍后重试");
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
		orderProcessor.process(orderMain, OrderEventsEnum.CONFIRM);
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
						.eq(OrderMain::getPayStatus, PayStatusEnum.UNPAID.getCode())
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

	@Override
	public boolean isPendingPayment(Long orderId) {
		if (orderId == null) {
			return false;
		}

		OrderMain order = orderMainMapper.selectById(orderId);
		if (order == null) {
			return false;
		}

		// 支付状态为待支付
		return PayStatusEnum.UNPAID.getCode().equals(order.getPayStatus());
	}

	@Override
	public boolean isPendingAccept(Long orderId) {
		if (orderId == null) {
			return false;
		}

		OrderMain order = orderMainMapper.selectById(orderId);
		if (order == null) {
			return false;
		}

		// 订单状态为待接单
		return OrderStatusEnum.WAIT_ACCEPT.getCode().equals(order.getOrderStatus());
	}

	@Override
	public boolean isPendingConfirm(Long orderId) {
		if (orderId == null) {
			return false;
		}

		OrderMain order = orderMainMapper.selectById(orderId);
		if (order == null) {
			return false;
		}

		// 订单状态为已送达
		return OrderStatusEnum.DELIVERED.getCode().equals(order.getOrderStatus());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancelTimeoutOrder(Long orderId, TimeoutTypeEnum timeoutType) {
		if (orderId == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		if (timeoutType == null) {
			throw new BusinessException("INVALID_PARAM", "超时类型不能为空");
		}

		OrderMain order = orderMainMapper.selectById(orderId);
		if (order == null) {
			log.warn("订单不存在，无法取消。订单ID：{}", orderId);
			return;
		}

		// 幂等性检查：订单已经是取消或完成状态，直接返回
		if (OrderStatusEnum.CANCELLED.getCode().equals(order.getOrderStatus())) {
			log.info("订单已取消，跳过处理。订单ID：{}", orderId);
			return;
		}

		if (OrderStatusEnum.COMPLETED.getCode().equals(order.getOrderStatus())) {
			log.info("订单已完成，跳过处理。订单ID：{}", orderId);
			return;
		}

		// 如果是接单超时且用户已支付，需要退款
		if (TimeoutTypeEnum.ACCEPT_TIMEOUT.equals(timeoutType)
				&& PayStatusEnum.PAID.getCode().equals(order.getPayStatus())) {
			refundToUser(order);
		}

		// 使用状态机取消订单
		orderProcessor.process(order, OrderEventsEnum.CANCEL);

		// 设置取消类型和取消时间
		order.setCancelType(CancelTypeEnum.TIMEOUT_CANCEL.getCode());
		order.setCancelTime(LocalDateTime.now());

		// 更新订单
		orderMainMapper.updateById(order);

		log.info("订单超时取消成功，订单ID：{}，超时类型：{}", orderId, timeoutType.getDesc());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void autoConfirmOrder(Long orderId) {
		if (orderId == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		OrderMain order = orderMainMapper.selectById(orderId);
		if (order == null) {
			log.warn("订单不存在，无法自动确认。订单ID：{}", orderId);
			return;
		}

		// 幂等性检查：订单不是已送达状态，跳过
		if (!OrderStatusEnum.DELIVERED.getCode().equals(order.getOrderStatus())) {
			log.info("订单状态不是已送达，跳过自动确认。订单ID：{}，当前状态：{}",
					orderId, order.getOrderStatus());
			return;
		}

		// 使用状态机确认订单
		orderProcessor.process(order, OrderEventsEnum.CONFIRM);

		log.info("订单自动确认完成，订单ID：{}", orderId);
	}

	/**
	 * 生成流水号
	 * 格式：FT + yyyyMMddHHmmss + 8位随机大写字符
	 */
	private String generateTransactionNo() {
		return "FT" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
			   + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}


	/**
	 * 退款给用户
	 */
	private void refundToUser(OrderMain order) {
		// 记录退款流水
		FinanceTransactionAddDTO refundTransaction = new FinanceTransactionAddDTO();
		refundTransaction.setTransactionNo(generateTransactionNo());
		refundTransaction.setUserId(order.getUserId());
		refundTransaction.setTransactionType(TransactionTypeEnum.REFUND.getCode());
		refundTransaction.setAmount(order.getActualAmount()); // 退款为正数
		refundTransaction.setRelatedType(RelatedTypeEnum.ORDER.getCode());
		refundTransaction.setRelatedId(order.getId());
		refundTransaction.setRemark("订单超时退款：" + order.getOrderNo());

		Result<Long> result = remoteFinanceService.createTransaction(refundTransaction);
		if (result.getCode() != CommonConstants.SUCCESS) {
			throw new BusinessException("REFUND_ERROR", "退款失败");
		}

		// 更新用户余额（增加）
		MerchantBalanceUpdateDTO balanceUpdateDTO = new MerchantBalanceUpdateDTO();
		balanceUpdateDTO.setUserId(order.getUserId());
		balanceUpdateDTO.setUserType(0); // 普通用户
		balanceUpdateDTO.setAmount(order.getActualAmount());
		balanceUpdateDTO.setUpdateType(1); // 增加余额
		remoteUserService.updateUserBalance(balanceUpdateDTO);

		//TODO 调用微信SDK执行退款

		// 更新订单支付状态为全额退款
		order.setPayStatus(PayStatusEnum.FULL_REFUND.getCode());

		log.info("订单超时退款成功，订单ID：{}，退款金额：{}", order.getId(), order.getActualAmount());
	}
}