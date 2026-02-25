package com.example.order.api.constants;

/**
 * 订单超时配置常量
 */
public class OrderTimeoutConstants {
    
    private OrderTimeoutConstants() {}
    
    // ==================== 超时时间配置 ====================
    
    /**
     * 支付超时时间（毫秒）- 15分钟
     */
    public static final long PAY_TIMEOUT_MS = 15 * 60 * 1000L;
    
    /**
     * 接单超时时间（毫秒）- 5分钟
     */
    public static final long ACCEPT_TIMEOUT_MS = 5 * 60 * 1000L;
    
    /**
     * 确认完成超时时间（毫秒）- 30分钟
     */
    public static final long CONFIRM_TIMEOUT_MS = 30 * 60 * 1000L;
    
    // ==================== MQ 配置 ====================
    
    /**
     * 延迟交换机名称
     */
    public static final String DELAYED_EXCHANGE = "order.delayed.exchange";
    
    /**
     * 支付超时队列
     */
    public static final String PAY_TIMEOUT_QUEUE = "order.pay.timeout.queue";
    
    /**
     * 支付超时路由键
     */
    public static final String PAY_TIMEOUT_ROUTING_KEY = "order.pay.timeout";
    
    /**
     * 接单超时队列
     */
    public static final String ACCEPT_TIMEOUT_QUEUE = "order.accept.timeout.queue";
    
    /**
     * 接单超时路由键
     */
    public static final String ACCEPT_TIMEOUT_ROUTING_KEY = "order.accept.timeout";
    
    /**
     * 确认完成超时队列
     */
    public static final String CONFIRM_TIMEOUT_QUEUE = "order.confirm.timeout.queue";
    
    /**
     * 确认完成超时路由键
     */
    public static final String CONFIRM_TIMEOUT_ROUTING_KEY = "order.confirm.timeout";
}