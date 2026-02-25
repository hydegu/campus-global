package com.example.order.biz.consumer;

import com.example.order.api.constants.OrderTimeoutConstants;
import com.example.order.api.dto.OrderTimeoutMessage;
import com.example.order.api.entity.OrderMain;
import com.example.order.api.enums.OrderStatusEnum;
import com.example.order.api.enums.TimeoutTypeEnum;
import com.example.order.biz.mapper.OrderMainMapper;
import com.example.order.biz.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 订单超时消息消费者
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderTimeoutConsumer {

    private final OrderService orderService;
    private final OrderMainMapper orderMainMapper;

    /**
     * 处理支付超时消息
     */
    @RabbitListener(queues = OrderTimeoutConstants.PAY_TIMEOUT_QUEUE)
    public void handlePayTimeout(OrderTimeoutMessage message) {
        log.info("收到支付超时消息，订单ID：{}，消息ID：{}", message.getOrderId(), message.getMessageId());

        try {
            // 幂等性检查：检查订单状态是否为待支付
            if (!orderService.isPendingPayment(message.getOrderId())) {
                log.info("订单已支付或已取消，跳过处理。订单ID：{}", message.getOrderId());
                return;
            }

            // 取消超时订单
            orderService.cancelTimeoutOrder(message.getOrderId(), TimeoutTypeEnum.PAY_TIMEOUT);
            log.info("支付超时取消订单成功，订单ID：{}", message.getOrderId());

        } catch (Exception e) {
            log.error("处理支付超时失败，订单ID：{}", message.getOrderId(), e);
            // 抛出异常触发重试，达到重试上限后进入死信队列
            throw e;
        }
    }

    /**
     * 处理接单超时消息
     */
    @RabbitListener(queues = OrderTimeoutConstants.ACCEPT_TIMEOUT_QUEUE)
    public void handleAcceptTimeout(OrderTimeoutMessage message) {
        log.info("收到接单超时消息，订单ID：{}，消息ID：{}", message.getOrderId(), message.getMessageId());

        try {
            // 幂等性检查：检查订单状态是否为待接单
            if (!orderService.isPendingAccept(message.getOrderId())) {
                log.info("订单已被接单或已取消，跳过处理。订单ID：{}", message.getOrderId());
                return;
            }

            // 取消超时订单
            orderService.cancelTimeoutOrder(message.getOrderId(), TimeoutTypeEnum.ACCEPT_TIMEOUT);
            log.info("接单超时取消订单成功，订单ID：{}", message.getOrderId());

        } catch (Exception e) {
            log.error("处理接单超时失败，订单ID：{}", message.getOrderId(), e);
            throw e;
        }
    }

    /**
     * 处理确认完成超时消息
     */
    @RabbitListener(queues = OrderTimeoutConstants.CONFIRM_TIMEOUT_QUEUE)
    public void handleConfirmTimeout(OrderTimeoutMessage message) {
        log.info("收到确认完成超时消息，订单ID：{}，消息ID：{}", message.getOrderId(), message.getMessageId());

        try {
            // 幂等性检查：检查订单状态是否为待确认
            if (!orderService.isPendingConfirm(message.getOrderId())) {
                log.info("订单已确认或已取消，跳过处理。订单ID：{}", message.getOrderId());
                return;
            }

            // 自动确认完成或取消（根据业务需求）
            orderService.autoConfirmOrder(message.getOrderId());
            log.info("自动确认订单成功，订单ID：{}", message.getOrderId());

        } catch (Exception e) {
            log.error("处理确认完成超时失败，订单ID：{}", message.getOrderId(), e);
            throw e;
        }
    }
}