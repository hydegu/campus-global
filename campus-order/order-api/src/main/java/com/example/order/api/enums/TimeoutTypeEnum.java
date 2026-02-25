package com.example.order.api.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimeoutTypeEnum {
    PAY_TIMEOUT(1, "支付超时"),
    ACCEPT_TIMEOUT(2, "接单超时");

    private final Integer code;

    private final String desc;


}
