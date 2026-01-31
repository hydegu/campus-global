package com.example.order.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.admin.api.dto.MerchantBalanceUpdateDTO;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.api.feign.RemoteUserService;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.util.Result;
import com.example.common.security.util.SecurityUtils;
import com.example.order.api.dto.ErrandAcceptDTO;
import com.example.order.api.dto.ErrandCreateDTO;
import com.example.order.api.dto.ErrandDeliverDTO;
import com.example.order.api.dto.ErrandPayDTO;
import com.example.order.api.dto.ErrandPickupDTO;
import com.example.order.api.dto.ErrandQueryDTO;
import com.example.order.api.entity.OrderErrand;
import com.example.order.api.entity.OrderMain;
import com.example.order.api.enums.OrderStatusEnum;
import com.example.order.api.enums.OrderTypeEnum;
import com.example.order.api.enums.PayStatusEnum;
import com.example.order.api.feign.RemoteErrandCategoryService;
import com.example.common.core.constant.CommonConstants;
import com.example.finance.api.dto.FinanceTransactionAddDTO;
import com.example.finance.api.enums.RelatedTypeEnum;
import com.example.finance.api.enums.TransactionTypeEnum;
import com.example.order.api.feign.RemoteFinanceService;
import com.example.order.api.vo.ErrandDetailVO;
import com.example.order.api.vo.ErrandListVO;
import com.example.order.biz.mapper.OrderErrandMapper;
import com.example.order.biz.mapper.OrderMainMapper;
import com.example.order.biz.service.AmapService;
import com.example.order.biz.service.ErrandService;
import com.example.service.api.vo.ErrandCategoryVO;
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
public class ErrandServiceImpl implements ErrandService {

	private final OrderMainMapper orderMainMapper;
	private final OrderErrandMapper orderErrandMapper;
	private final RemoteUserService remoteUserService;
	private final RemoteFinanceService remoteFinanceService;
	private final RemoteErrandCategoryService remoteErrandCategoryService;
	private final AmapService amapService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long createErrand(ErrandCreateDTO createDTO) {
		if (createDTO == null) {
			throw new BusinessException("INVALID_PARAM", "服务订单创建参数不能为空");
		}

		if (createDTO.getServiceTypeId() == null) {
			throw new BusinessException("INVALID_PARAM", "服务分类ID不能为空");
		}

		if (createDTO.getPickupAddressId() == null || createDTO.getPickupAddressId().isEmpty()) {
			throw new BusinessException("INVALID_PARAM", "取货地址ID不能为空");
		}

		if (createDTO.getDeliveryAddressId() == null || createDTO.getDeliveryAddressId().isEmpty()) {
			throw new BusinessException("INVALID_PARAM", "送货地址ID不能为空");
		}

		if (createDTO.getDeliveryFee() == null || createDTO.getDeliveryFee().compareTo(BigDecimal.ZERO) <= 0) {
			throw new BusinessException("INVALID_PARAM", "配送费必须大于0");
		}

		Long currentUserId = SecurityUtils.getCurrentUserId();
		if (currentUserId == null) {
			throw new BusinessException("NOT_LOGIN", "用户未登录");
		}

		BigDecimal totalAmount = createDTO.getDeliveryFee();
		if (createDTO.getDeliveryFee() != null && createDTO.getDeliveryFee().compareTo(BigDecimal.ZERO) > 0) {
			totalAmount = totalAmount.add(createDTO.getDeliveryFee());
		}

		OrderMain orderMain = new OrderMain();
		orderMain.setOrderNo(generateOrderNo());
		orderMain.setOrderType(OrderTypeEnum.SERVICE.getCode());
		orderMain.setUserId(currentUserId);
		orderMain.setTotalAmount(totalAmount);
		orderMain.setActualAmount(totalAmount);
		orderMain.setPayStatus(PayStatusEnum.UNPAID.getCode());
		orderMain.setOrderStatus(OrderStatusEnum.WAIT_ACCEPT.getCode());
		orderMain.setServiceProviderType(2);
		orderMain.setRemark(createDTO.getRemark());
		orderMain.setVersion(0);

		orderMainMapper.insert(orderMain);

		OrderErrand orderErrand = new OrderErrand();
		orderErrand.setOrderId(orderMain.getId());
		orderErrand.setServiceFee(createDTO.getDeliveryFee());
		orderErrand.setServiceTypeId(createDTO.getServiceTypeId());
		orderErrand.setPickupAddressId(createDTO.getPickupAddressId());
		orderErrand.setDeliveryAddressId(createDTO.getDeliveryAddressId());
		orderErrand.setItemDescription(createDTO.getItemDescription());
		orderErrand.setItemWeight(createDTO.getItemWeight());
		orderErrand.setLength(createDTO.getLength());
		orderErrand.setWidth(createDTO.getWidth());
		orderErrand.setHeight(createDTO.getHeight());

		if (createDTO.getLength() != null && createDTO.getWidth() != null && createDTO.getHeight() != null) {
			BigDecimal volume = createDTO.getLength()
					.multiply(createDTO.getWidth())
					.multiply(createDTO.getHeight());
			orderErrand.setVolume(volume);
		}

		orderErrandMapper.insert(orderErrand);

		String pickupAddress = createDTO.getPickupAddressId();
		String deliveryAddress = createDTO.getDeliveryAddressId();

		try {
			BigDecimal distance = amapService.getDistance(pickupAddress, deliveryAddress);
			if (distance != null) {
				orderMain.setDistance(distance);
				orderMainMapper.updateById(orderMain);
			}

			Integer duration = amapService.getDuration(pickupAddress, deliveryAddress);
			if (duration != null && duration > 0) {
				LocalDateTime estimatedDeliveryTime = LocalDateTime.now().plusMinutes(duration);
				orderMain.setEstimatedDeliveryTime(estimatedDeliveryTime);
				orderMainMapper.updateById(orderMain);
			}
		} catch (Exception e) {
			log.warn("获取预计送达时间失败，pickupAddress：{}，deliveryAddress：{}", pickupAddress, deliveryAddress, e);
		}

		log.info("服务订单创建成功，订单ID：{}，订单编号：{}，用户ID：{}", 
				orderMain.getId(), orderMain.getOrderNo(), currentUserId);

		return orderMain.getId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void acceptErrand(ErrandAcceptDTO acceptDTO) {
		if (acceptDTO == null || acceptDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		Long staffId = SecurityUtils.getCurrentUserId();
		if (staffId == null) {
			throw new BusinessException("NOT_LOGIN", "服务人员未登录");
		}

		OrderMain orderMain = orderMainMapper.selectById(acceptDTO.getOrderId());
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.SERVICE.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		if (!OrderStatusEnum.WAIT_ACCEPT.getCode().equals(orderMain.getOrderStatus())) {
			throw new BusinessException("INVALID_ORDER_STATUS", "订单状态不允许接单");
		}

		orderMain.setOrderStatus(OrderStatusEnum.WAIT_PICKUP.getCode());
		orderMain.setServiceProviderType(2);
		orderMain.setServiceProviderId(staffId);
		orderMain.setEstimatedStartTime(LocalDateTime.now());

		orderMainMapper.updateById(orderMain);

		OrderErrand orderErrand = orderErrandMapper.selectOne(
				Wrappers.lambdaQuery(OrderErrand.class).eq(OrderErrand::getOrderId, acceptDTO.getOrderId())
		);
		if (orderErrand != null) {
			orderErrand.setStaffId(staffId);
			orderErrandMapper.updateById(orderErrand);
		}

		log.info("服务人员接单成功，订单ID：{}，服务人员ID：{}", acceptDTO.getOrderId(), staffId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pickupErrand(ErrandPickupDTO pickupDTO) {
		if (pickupDTO == null || pickupDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		Long staffId = SecurityUtils.getCurrentUserId();
		if (staffId == null) {
			throw new BusinessException("NOT_LOGIN", "服务人员未登录");
		}

		OrderMain orderMain = orderMainMapper.selectById(pickupDTO.getOrderId());
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.SERVICE.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		if (!OrderStatusEnum.WAIT_PICKUP.getCode().equals(orderMain.getOrderStatus())) {
			throw new BusinessException("INVALID_ORDER_STATUS", "订单状态不允许取货");
		}

		if (!staffId.equals(orderMain.getServiceProviderId())) {
			throw new BusinessException("PERMISSION_DENIED", "无权操作该订单");
		}

		orderMain.setOrderStatus(OrderStatusEnum.DELIVERING.getCode());

		orderMainMapper.updateById(orderMain);

		log.info("服务人员取货成功，订单ID：{}，服务人员ID：{}", pickupDTO.getOrderId(), staffId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deliverErrand(ErrandDeliverDTO deliverDTO) {
		if (deliverDTO == null || deliverDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		Long staffId = SecurityUtils.getCurrentUserId();
		if (staffId == null) {
			throw new BusinessException("NOT_LOGIN", "服务人员未登录");
		}

		OrderMain orderMain = orderMainMapper.selectById(deliverDTO.getOrderId());
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.SERVICE.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		if (!OrderStatusEnum.DELIVERING.getCode().equals(orderMain.getOrderStatus())) {
			throw new BusinessException("INVALID_ORDER_STATUS", "订单状态不允许送达");
		}

		if (!staffId.equals(orderMain.getServiceProviderId())) {
			throw new BusinessException("PERMISSION_DENIED", "无权操作该订单");
		}

		orderMain.setOrderStatus(OrderStatusEnum.COMPLETED.getCode());
		orderMain.setActualDeliveryTime(LocalDateTime.now());

		orderMainMapper.updateById(orderMain);

		log.info("服务人员送达成功，订单完成，订单ID：{}，服务人员ID：{}", deliverDTO.getOrderId(), staffId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancelErrand(Long orderId, Integer cancelType) {
		if (orderId == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		OrderMain orderMain = orderMainMapper.selectById(orderId);
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.SERVICE.getCode().equals(orderMain.getOrderType())) {
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

		log.info("服务订单取消成功，订单ID：{}，取消类型：{}", orderId, cancelType);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void payErrand(ErrandPayDTO payDTO) {
		if (payDTO == null || payDTO.getOrderId() == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		OrderMain orderMain = orderMainMapper.selectById(payDTO.getOrderId());
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.SERVICE.getCode().equals(orderMain.getOrderType())) {
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
		userTransaction.setRemark("跑腿订单支付：" + orderMain.getOrderNo());
		
		Result<Long> userResult = remoteFinanceService.createTransaction(userTransaction);
		if (userResult.getCode() != CommonConstants.SUCCESS) {
			throw new BusinessException("FINANCE_ERROR", "记录用户消费流水失败");
		}
		
		// ========== 记录各方收益流水 ==========
		BigDecimal providerIncome = orderMain.getEstimatedProviderIncome();
		BigDecimal partnerIncome = orderMain.getEstimatedPartnerIncome();
		BigDecimal platformIncome = orderMain.getEstimatedPlatformIncome();

		// 服务提供方收入（服务人员）
		if (providerIncome != null && providerIncome.compareTo(BigDecimal.ZERO) > 0) {
			FinanceTransactionAddDTO providerTransaction = new FinanceTransactionAddDTO();
			providerTransaction.setTransactionNo(generateTransactionNo());
			providerTransaction.setUserId(orderMain.getServiceProviderId());
			providerTransaction.setTransactionType(TransactionTypeEnum.PAYMENT.getCode());
			providerTransaction.setAmount(providerIncome);
			providerTransaction.setRelatedType(RelatedTypeEnum.ORDER.getCode());
			providerTransaction.setRelatedId(orderMain.getId());
			providerTransaction.setRemark("跑腿订单服务人员收入：" + orderMain.getOrderNo());

			remoteFinanceService.createTransaction(providerTransaction);

			// 更新服务人员余额（骑手）
			MerchantBalanceUpdateDTO balanceUpdateDTO = new MerchantBalanceUpdateDTO();
			balanceUpdateDTO.setUserId(orderMain.getServiceProviderId());
			balanceUpdateDTO.setUserType(2);
			balanceUpdateDTO.setAmount(providerIncome);
			balanceUpdateDTO.setUpdateType(1);
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
			partnerTransaction.setRemark("跑腿订单合伙人收入：" + orderMain.getOrderNo());

			remoteFinanceService.createTransaction(partnerTransaction);

			// 更新合伙人余额
			MerchantBalanceUpdateDTO partnerBalanceDTO = new MerchantBalanceUpdateDTO();
			partnerBalanceDTO.setUserId(orderMain.getPartnerId());
			partnerBalanceDTO.setUserType(3);
			partnerBalanceDTO.setAmount(partnerIncome);
			partnerBalanceDTO.setUpdateType(1);
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
			platformTransaction.setRemark("跑腿订单平台收入：" + orderMain.getOrderNo());
			
			remoteFinanceService.createTransaction(platformTransaction);
		}

		// 更新订单支付状态
		orderMain.setPayStatus(PayStatusEnum.PAID.getCode());
		orderMain.setPayMethod(payDTO.getPayMethod());
		orderMain.setPayTime(LocalDateTime.now());
		orderMain.setPayChannelNo(payDTO.getPayChannelNo());
		// 注意：跑腿订单的支付是独立的，不改变订单状态

		orderMainMapper.updateById(orderMain);

		log.info("服务订单支付成功，订单ID：{}，支付方式：{}，支付流水号：{}",
				payDTO.getOrderId(), payDTO.getPayMethod(), payDTO.getPayChannelNo());
	}

	@Override
	public ErrandDetailVO getErrandDetail(Long orderId) {
		if (orderId == null) {
			throw new BusinessException("INVALID_PARAM", "订单ID不能为空");
		}

		OrderMain orderMain = orderMainMapper.selectById(orderId);
		if (orderMain == null) {
			throw new BusinessException("ORDER_NOT_FOUND", "订单不存在");
		}

		if (!OrderTypeEnum.SERVICE.getCode().equals(orderMain.getOrderType())) {
			throw new BusinessException("INVALID_ORDER_TYPE", "订单类型不正确");
		}

		OrderErrand orderErrand = orderErrandMapper.selectOne(
				Wrappers.lambdaQuery(OrderErrand.class).eq(OrderErrand::getOrderId, orderId)
		);

		ErrandDetailVO vo = new ErrandDetailVO();
		BeanUtils.copyProperties(orderMain, vo);

		if (orderErrand != null) {
			vo.setServiceFee(orderErrand.getServiceFee());
			vo.setPickupAddress(orderErrand.getPickupAddressId());
			vo.setDeliveryAddress(orderErrand.getDeliveryAddressId());
			vo.setItemDescription(orderErrand.getItemDescription());
			vo.setItemWeight(orderErrand.getItemWeight());
			vo.setVolume(orderErrand.getVolume());

			if (orderErrand.getLength() != null && orderErrand.getWidth() != null && orderErrand.getHeight() != null) {
				String itemSize = String.format("%.0fx%.0fx%.0f", 
						orderErrand.getLength(), orderErrand.getWidth(), orderErrand.getHeight());
				vo.setItemSize(itemSize);
			}

			vo.setServiceTypeName(getServiceTypeName(orderErrand.getServiceTypeId()));

			if (orderErrand.getStaffId() != null) {
				vo.setStaffName(getUserName(orderErrand.getStaffId()));
				vo.setStaffPhone(getUserPhone(orderErrand.getStaffId()));
			}
		}

		vo.setUserName(getUserName(orderMain.getUserId()));
		vo.setUserPhone(getUserPhone(orderMain.getUserId()));

		return vo;
	}

	@Override
	public Page<ErrandListVO> listErrands(ErrandQueryDTO queryDTO) {
		if (queryDTO == null) {
			queryDTO = new ErrandQueryDTO();
		}

		if (queryDTO.getPageNum() == null || queryDTO.getPageNum() < 1) {
			queryDTO.setPageNum(1);
		}

		if (queryDTO.getPageSize() == null || queryDTO.getPageSize() < 1) {
			queryDTO.setPageSize(10);
		}

		LambdaQueryWrapper<OrderMain> wrapper = buildErrandQueryWrapper(queryDTO);

		IPage<OrderMain> orderPage = orderMainMapper.selectPage(
				new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()), wrapper
		);

		Page<ErrandListVO> resultPage = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
		resultPage.setTotal(orderPage.getTotal());

		if (orderPage.getRecords().isEmpty()) {
			return resultPage;
		}

		resultPage.setRecords(orderPage.getRecords().stream().map(order -> {
			ErrandListVO vo = new ErrandListVO();
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

	private LambdaQueryWrapper<OrderMain> buildErrandQueryWrapper(ErrandQueryDTO queryDTO) {
		LambdaQueryWrapper<OrderMain> wrapper = Wrappers.lambdaQuery();

		wrapper.eq(OrderMain::getOrderType, OrderTypeEnum.SERVICE.getCode());

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
				throw new BusinessException("INVALID_QUERY_TYPE", "服务订单不支持商家查询");
			}
		}

		if (queryDTO.getOrderNo() != null) {
			wrapper.like(OrderMain::getOrderNo, queryDTO.getOrderNo());
		}

		if (queryDTO.getOrderStatus() != null) {
			wrapper.eq(OrderMain::getOrderStatus, queryDTO.getOrderStatus());
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
			Result<UserInfo> result =
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
			Result<UserInfo> result =
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

	private String getServiceTypeName(Long serviceTypeId) {
		if (serviceTypeId == null) {
			return "未知服务分类";
		}
		// 通过 Feign 调用 service 模块获取分类详情
		Result<ErrandCategoryVO> result =
				remoteErrandCategoryService.getCategoryDetail(serviceTypeId);
		if (result != null && result.getData() != null) {
			return result.getData().getCategoryName();
		}
		// 降级处理：返回ID拼接的名称
		return "服务分类" + serviceTypeId;
	}

	@Override
	public boolean hasOrdersByServiceTypeId(Long serviceTypeId) {
		if (serviceTypeId == null) {
			return false;
		}
		LambdaQueryWrapper<OrderErrand> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(OrderErrand::getServiceTypeId, serviceTypeId);
		Long count = orderErrandMapper.selectCount(wrapper);
		return count != null && count > 0;
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