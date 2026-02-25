package com.example.order.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrderTimeoutMessage implements Serializable {
    private String messageId;
    private Long orderId;
    private String orderNo;
    private Integer timeoutType;  // 1:支付超时 2:接单超时
    private Long userId;
    private LocalDateTime createTime;
}