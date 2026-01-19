package com.example.finance.common.enums;

import lombok.Getter;

/**
 * 提现方式枚举
 */
@Getter
public enum WithdrawType {

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
    BANK_CARD(3, "银行卡");

    /**
     * 类型值
     */
    private final Integer code;

    /**
     * 类型名称
     */
    private final String name;

    WithdrawType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据code获取名称
     *
     * @param code 类型值
     * @return 类型名称
     */
    public static String getNameByCode(Integer code) {
        if (code == null) {
            return "未知";
        }
        for (WithdrawType type : WithdrawType.values()) {
            if (type.getCode().equals(code)) {
                return type.getName();
            }
        }
        return "未知";
    }

    /**
     * 根据code获取枚举
     *
     * @param code 类型值
     * @return 枚举对象
     */
    public static WithdrawType getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (WithdrawType type : WithdrawType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
