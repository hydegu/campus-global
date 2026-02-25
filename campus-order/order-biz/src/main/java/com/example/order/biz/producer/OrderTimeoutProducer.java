package com.example.order.biz.producer;

import com.example.order.api.constants.OrderTimeoutConstants;
import com.example.order.api.dto.OrderTimeoutMessage;
import com.example.order.api.enums.TimeoutTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 订单超时消息生产者
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderTimeoutProducer {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送支付超时延迟消息
     * 
     * @param orderId 订单ID
     * @param orderNo 订单编号
     * @param userId  用户ID
     */
    public void sendPayTimeoutMessage(Long orderId, String orderNo, Long userId) {
        sendPayTimeoutMessage(orderId, orderNo, userId, OrderTimeoutConstants.PAY_TIMEOUT_MS);
    }

    /**
     * 发送支付超时延迟消息（自定义延迟时间）
     */
    public void sendPayTimeoutMessage(Long orderId, String orderNo, Long userId, long delayMs) {
        OrderTimeoutMessage message = buildMessage(orderId, orderNo, userId, TimeoutTypeEnum.PAY_TIMEOUT);
        sendDelayMessage(message, delayMs, OrderTimeoutConstants.PAY_TIMEOUT_ROUTING_KEY);
        log.info("发送支付超时消息，订单ID：{}，延迟：{}ms", orderId, delayMs);
    }

    /**
     * 发送接单超时延迟消息
     */
    public void sendAcceptTimeoutMessage(Long orderId, String orderNo, Long userId) {
        sendAcceptTimeoutMessage(orderId, orderNo, userId, OrderTimeoutConstants.ACCEPT_TIMEOUT_MS);
    }

    /**
     * 发送接单超时延迟消息（自定义延迟时间）
     */
    public void sendAcceptTimeoutMessage(Long orderId, String orderNo, Long userId, long delayMs) {
        OrderTimeoutMessage message = buildMessage(orderId, orderNo, userId, TimeoutTypeEnum.ACCEPT_TIMEOUT);
        sendDelayMessage(message, delayMs, OrderTimeoutConstants.ACCEPT_TIMEOUT_ROUTING_KEY);
        log.info("发送接单超时消息，订单ID：{}，延迟：{}ms", orderId, delayMs);
    }

    /**
     * 发送确认完成超时延迟消息
     */
    public void sendConfirmTimeoutMessage(Long orderId, String orderNo, Long userId) {
        sendConfirmTimeoutMessage(orderId, orderNo, userId, OrderTimeoutConstants.CONFIRM_TIMEOUT_MS);
    }

    /**
     * 发送确认完成超时延迟消息（自定义延迟时间）
     */
    public void sendConfirmTimeoutMessage(Long orderId, String orderNo, Long userId, long delayMs) {
        // 需要在 TimeoutTypeEnum 中添加 CONFIRM_TIMEOUT 枚举
        OrderTimeoutMessage message = buildMessage(orderId, orderNo, userId, TimeoutTypeEnum.ACCEPT_TIMEOUT);
        sendDelayMessage(message, delayMs, OrderTimeoutConstants.CONFIRM_TIMEOUT_ROUTING_KEY);
        log.info("发送确认完成超时消息，订单ID：{}，延迟：{}ms", orderId, delayMs);
    }

    /**
     * 构建消息体
     */
    private OrderTimeoutMessage buildMessage(Long orderId, String orderNo, Long userId, TimeoutTypeEnum timeoutType) {
        OrderTimeoutMessage message = new OrderTimeoutMessage();
        message.setMessageId(UUID.randomUUID().toString());
        message.setOrderId(orderId);
        message.setOrderNo(orderNo);
        message.setUserId(userId);
        message.setTimeoutType(timeoutType.getCode());
        message.setCreateTime(LocalDateTime.now());
        return message;
    }

    /**
     * 发送延迟消息
     * 
     * @param message    消息体
     * @param delayMs    延迟时间（毫秒）
     * @param routingKey 路由键
     */
    private void sendDelayMessage(OrderTimeoutMessage message, long delayMs, String routingKey) {
        MessagePostProcessor processor = msg -> {
            // 设置延迟时间
            msg.getMessageProperties().setDelayLong(delayMs);
            return msg;
        };
        
        rabbitTemplate.convertAndSend(
                OrderTimeoutConstants.DELAYED_EXCHANGE,
                routingKey,
                message,
                processor
        );
    }

}