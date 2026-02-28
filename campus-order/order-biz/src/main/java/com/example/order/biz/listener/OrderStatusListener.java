package com.example.order.biz.listener;

import com.example.common.core.exception.BusinessException;
import com.example.common.core.exception.ResourceNotFoundException;
import com.example.order.api.entity.OrderMain;
import com.example.order.api.entity.OrderOperationLog;
import com.example.order.api.enums.OrderEventsEnum;
import com.example.order.api.enums.OrderStatusEnum;
import com.example.order.biz.mapper.OrderMainMapper;
import com.example.order.biz.mapper.OrderOperationLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component("orderStateListener")
@Slf4j
@WithStateMachine(name="orderStateMachine")
@RequiredArgsConstructor
public class OrderStatusListener {

    private final OrderMainMapper orderMainMapper;
    private final OrderOperationLogMapper orderOperationLogMapper;

    /**
     * 记录订单操作审计日志
     */
    private void logOperation(OrderMain order, String operationType, Integer beforeStatus, Integer afterStatus, Long operatorId, String operatorName, Integer operatorType) {
        OrderOperationLog log = new OrderOperationLog();
        log.setOrderId(order.getId());
        log.setOrderNo(order.getOrderNo());
        log.setOperationType(operationType);
        log.setBeforeStatus(beforeStatus);
        log.setAfterStatus(afterStatus);
        log.setOperatorId(operatorId);
        log.setOperatorName(operatorName);
        log.setOperatorType(operatorType);
        log.setCreateAt(LocalDateTime.now());
        orderOperationLogMapper.insert(log);
    }

    @OnTransition(source="WAIT_ACCEPT",target = "WAIT_PICKUP")
    public void accept(Message<OrderEventsEnum> message){
        OrderMain order = (OrderMain)message.getHeaders().get("order");
        Integer beforeStatus = order.getOrderStatus();
        // 幂等检查：当前状态必须还是 WAIT_ACCEPT，才能继续
        OrderMain dbOrder = Optional.ofNullable(orderMainMapper.selectById(order.getId())).orElseThrow(() -> new ResourceNotFoundException("未找到此订单"));
        if (!OrderStatusEnum.WAIT_ACCEPT.getCode().equals(dbOrder.getOrderStatus())) {
            throw new BusinessException("TO_SLOW","手慢了！该订单已被其他骑手接单");
        }
        order.setOrderStatus(OrderStatusEnum.WAIT_PICKUP.getCode());
        // 记录审计日志
        Long riderId = order.getServiceProviderId();
        logOperation(order, "ACCEPT", beforeStatus, OrderStatusEnum.WAIT_PICKUP.getCode(), riderId, "骑手", 2);
        log.info("第{}号订单已接单",order.getId());
    }

    @OnTransition(source="WAIT_PICKUP",target = "DELIVERING")
    public void pickUp(Message<OrderEventsEnum> message){
        OrderMain order = (OrderMain)message.getHeaders().get("order");
        Integer beforeStatus = order.getOrderStatus();
        OrderMain dbOrder = Optional.ofNullable(orderMainMapper.selectById(order.getId())).orElseThrow(() -> new ResourceNotFoundException("未找到此订单"));
        if (!OrderStatusEnum.WAIT_PICKUP.getCode().equals(dbOrder.getOrderStatus())) {
            throw new BusinessException("INVALID_STATUS","订单状态异常，无法取货");
        }
        order.setOrderStatus(OrderStatusEnum.DELIVERING.getCode());
        logOperation(order, "PICKUP", beforeStatus, OrderStatusEnum.DELIVERING.getCode(), order.getServiceProviderId(), "骑手", 2);
        log.info("第{}号订单已取货，开始配送",order.getId());
    }

    @OnTransition(source="DELIVERING",target = "DELIVERED")
    public void arrive(Message<OrderEventsEnum> message){
        OrderMain order = (OrderMain)message.getHeaders().get("order");
        Integer beforeStatus = order.getOrderStatus();
        OrderMain dbOrder = Optional.ofNullable(orderMainMapper.selectById(order.getId())).orElseThrow(() -> new ResourceNotFoundException("未找到此订单"));
        if (!OrderStatusEnum.DELIVERING.getCode().equals(dbOrder.getOrderStatus())) {
            throw new BusinessException("INVALID_STATUS","订单状态异常，无法确认送达");
        }
        order.setOrderStatus(OrderStatusEnum.DELIVERED.getCode());
        logOperation(order, "DELIVER", beforeStatus, OrderStatusEnum.DELIVERED.getCode(), order.getServiceProviderId(), "骑手", 2);
        log.info("第{}号订单已送达，等待用户确认",order.getId());
    }

    @OnTransition(source="DELIVERED",target = "COMPLETED")
    public void confirm(Message<OrderEventsEnum> message){
        OrderMain order = (OrderMain)message.getHeaders().get("order");
        Integer beforeStatus = order.getOrderStatus();
        OrderMain dbOrder = Optional.ofNullable(orderMainMapper.selectById(order.getId())).orElseThrow(() -> new ResourceNotFoundException("未找到此订单"));
        if (!OrderStatusEnum.DELIVERED.getCode().equals(dbOrder.getOrderStatus())) {
            throw new BusinessException("INVALID_STATUS","订单状态异常，无法确认收货");
        }
        order.setOrderStatus(OrderStatusEnum.COMPLETED.getCode());
        logOperation(order, "CONFIRM", beforeStatus, OrderStatusEnum.COMPLETED.getCode(), order.getUserId(), "用户", 1);
        log.info("第{}号订单已确认收货，订单完成",order.getId());
    }

    @OnTransition(source="WAIT_ACCEPT",target = "CANCELLED")
    public void cancelFromWaitAccept(Message<OrderEventsEnum> message){
        OrderMain order = (OrderMain)message.getHeaders().get("order");
        Integer beforeStatus = order.getOrderStatus();
        OrderMain dbOrder = Optional.ofNullable(orderMainMapper.selectById(order.getId())).orElseThrow(() -> new ResourceNotFoundException("未找到此订单"));
        if (!OrderStatusEnum.WAIT_ACCEPT.getCode().equals(dbOrder.getOrderStatus())) {
            throw new BusinessException("INVALID_STATUS","订单状态异常，无法取消");
        }
        order.setOrderStatus(OrderStatusEnum.CANCELLED.getCode());
        logOperation(order, "CANCEL", beforeStatus, OrderStatusEnum.CANCELLED.getCode(), order.getUserId(), "用户", 1);
        log.info("第{}号订单已取消",order.getId());
    }

    @OnTransition(source="WAIT_PICKUP",target = "CANCELLED")
    public void cancelFromWaitPickup(Message<OrderEventsEnum> message){
        OrderMain order = (OrderMain)message.getHeaders().get("order");
        Integer beforeStatus = order.getOrderStatus();
        OrderMain dbOrder = Optional.ofNullable(orderMainMapper.selectById(order.getId())).orElseThrow(() -> new ResourceNotFoundException("未找到此订单"));
        if (!OrderStatusEnum.WAIT_PICKUP.getCode().equals(dbOrder.getOrderStatus())) {
            throw new BusinessException("INVALID_STATUS","订单状态异常，无法取消");
        }
        order.setOrderStatus(OrderStatusEnum.CANCELLED.getCode());
        logOperation(order, "CANCEL", beforeStatus, OrderStatusEnum.CANCELLED.getCode(), order.getUserId(), "用户", 1);
        log.info("第{}号订单已取消",order.getId());
    }

    @OnTransition(source="DELIVERING",target = "CANCELLED")
    public void cancelFromDelivering(Message<OrderEventsEnum> message){
        OrderMain order = (OrderMain)message.getHeaders().get("order");
        Integer beforeStatus = order.getOrderStatus();
        OrderMain dbOrder = Optional.ofNullable(orderMainMapper.selectById(order.getId())).orElseThrow(() -> new ResourceNotFoundException("未找到此订单"));
        if (!OrderStatusEnum.DELIVERING.getCode().equals(dbOrder.getOrderStatus())) {
            throw new BusinessException("INVALID_STATUS","订单状态异常，无法取消");
        }
        order.setOrderStatus(OrderStatusEnum.CANCELLED.getCode());
        logOperation(order, "CANCEL", beforeStatus, OrderStatusEnum.CANCELLED.getCode(), order.getUserId(), "用户", 1);
        log.info("第{}号订单已取消",order.getId());
    }
}
