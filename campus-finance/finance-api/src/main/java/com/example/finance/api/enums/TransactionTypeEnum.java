package com.example.finance.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 交易类型枚举
 * 1-打款：系统向商家/骑手/合伙人打款
 * 2-消费：用户支付订单（通过正负金额区分收入/支出）
 * 3-退款：订单退款
 * 4-提现：C端用户提现
 */
@Getter
@AllArgsConstructor
public enum TransactionTypeEnum {

    /**
     * 打款
     */
    PAYMENT(1, "打款"),

    /**
     * 消费
     */
    CONSUMPTION(2, "消费"),

    /**
     * 退款
     */
    REFUND(3, "退款"),

    /**
     * 提现
     */
    WITHDRAWAL(4, "提现");

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
     * @return 交易类型枚举
     */
    public static TransactionTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (TransactionTypeEnum typeEnum : values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}