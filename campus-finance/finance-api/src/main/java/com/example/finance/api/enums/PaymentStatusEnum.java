package com.example.finance.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 打款状态枚举
 */
@Getter
@AllArgsConstructor
public enum PaymentStatusEnum {

    /**
     * 待确认
     */
    PENDING_CONFIRM(0, "待确认"),

    /**
     * 待打款
     */
    PENDING_PAYMENT(1, "待打款"),

    /**
     * 已打款
     */
    PAID(2, "已打款"),

    /**
     * 打款失败
     */
    FAILED(3, "打款失败");

    /**
     * 状态编码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String desc;

    /**
     * 根据编码获取枚举
     *
     * @param code 状态编码
     * @return 打款状态枚举
     */
    public static PaymentStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (PaymentStatusEnum statusEnum : values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
}