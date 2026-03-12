package com.example.order.biz.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.example.order.api.constants.OrderTimeoutConstants.*;

// order-biz 配置类
@Configuration
public class OrderMqConfig {
    
    // 延迟交换机
    @Bean
    public CustomExchange orderDelayedExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("order.delayed.exchange", "x-delayed-message", true, false, args);
    }
    
    // 支付超时队列
    @Bean
    public Queue orderPayTimeoutQueue() {
        return QueueBuilder.durable(PAY_TIMEOUT_QUEUE).build();
    }

    // 接单超时队列
    @Bean
    public Queue orderAcceptTimeoutQueue(){ return QueueBuilder.durable(ACCEPT_TIMEOUT_QUEUE).build(); }

    @Bean
    public Queue orderConfirmTimeoutQueue() {
        return QueueBuilder.durable(CONFIRM_TIMEOUT_QUEUE).build();
    }


    // 绑定支付超时队列
    @Bean
    public Binding payTimeoutBinding(Queue orderPayTimeoutQueue, CustomExchange orderDelayedExchange) {
        return BindingBuilder.bind(orderPayTimeoutQueue).to(orderDelayedExchange).with(PAY_TIMEOUT_QUEUE).noargs();
    }

    // 绑定接单超时队列
    @Bean
    public Binding orderAcceptTimeoutBinding(Queue orderAcceptTimeoutQueue, CustomExchange orderDelayedExchange){
        return BindingBuilder.bind(orderAcceptTimeoutQueue).to(orderDelayedExchange).with(ACCEPT_TIMEOUT_QUEUE).noargs();
    }

    @Bean
    public Binding orderConfirmTimeoutBinding(Queue orderConfirmTimeoutQueue, CustomExchange orderDelayedExchange) {
        return BindingBuilder.bind(orderConfirmTimeoutQueue).to(orderDelayedExchange)
                .with(CONFIRM_TIMEOUT_QUEUE).noargs();
    }
}