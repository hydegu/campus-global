package com.example.finance.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 收款账户类型枚举
 * 用于标识收款方（商家/骑手/合伙人）的账户类型
 */
@Getter
@AllArgsConstructor
public enum PaymentAccountTypeEnum {

    /**
     * 银行卡
     */
    BANK(1, "银行卡"),

    /**
     * 微信
     */
    WECHAT(2, "微信"),

    /**
     * 支付宝
     */
    ALIPAY(3, "支付宝");

    /**
     * 类型编码
     */
    private final Integer code;

    /**
     * 类型描述
     */
    private final String desc;

    /**
     * 根据编码获取枚举
     *
     * @param code 类型编码
     * @return 收款账户类型枚举
     */
    public static PaymentAccountTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (PaymentAccountTypeEnum typeEnum : values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}