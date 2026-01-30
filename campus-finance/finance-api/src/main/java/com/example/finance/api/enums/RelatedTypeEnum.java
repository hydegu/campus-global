package com.example.finance.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 关联业务类型枚举
 */
@Getter
@AllArgsConstructor
public enum RelatedTypeEnum {

    /**
     * 订单
     */
    ORDER(1, "订单"),

    /**
     * 提现
     */
    WITHDRAWAL(2, "提现"),

    /**
     * 退款
     */
    REFUND(3, "退款"),

    /**
     * 打款
     */
    PAYMENT(4, "打款");

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
     * @return 关联业务类型枚举
     */
    public static RelatedTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (RelatedTypeEnum typeEnum : values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}