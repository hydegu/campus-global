package com.example.finance.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 提现方式枚举
 */
@Getter
@AllArgsConstructor
public enum WithdrawalTypeEnum {

    /**
     * 微信
     */
    WECHAT(1, "微信"),

    /**
     * 支付宝
     */
    ALIPAY(2, "支付宝"),

    /**
     * 银行卡
     */
    BANK(3, "银行卡");

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
     * @return 提现方式枚举
     */
    public static WithdrawalTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (WithdrawalTypeEnum typeEnum : values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}