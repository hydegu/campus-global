package com.example.finance.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 提现状态枚举
 */
@Getter
@AllArgsConstructor
public enum WithdrawalStatusEnum {

    /**
     * 待审核
     */
    PENDING(0, "待审核"),

    /**
     * 审核通过
     */
    APPROVED(1, "审核通过"),

    /**
     * 审核拒绝
     */
    REJECTED(2, "审核拒绝"),

    /**
     * 打款中
     */
    PAYING(3, "打款中"),

    /**
     * 打款成功
     */
    SUCCESS(4, "打款成功"),

    /**
     * 打款失败
     */
    FAILED(5, "打款失败");

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
     * @return 提现状态枚举
     */
    public static WithdrawalStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (WithdrawalStatusEnum statusEnum : values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
}