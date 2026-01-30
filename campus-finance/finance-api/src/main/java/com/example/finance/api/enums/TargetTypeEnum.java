package com.example.finance.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 打款目标类型枚举
 */
@Getter
@AllArgsConstructor
public enum TargetTypeEnum {

    /**
     * 商家
     */
    MERCHANT(1, "商家"),

    /**
     * 骑手
     */
    RIDER(2, "骑手"),

    /**
     * 合伙人
     */
    PARTNER(3, "合伙人");

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
     * @return 打款目标类型枚举
     */
    public static TargetTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (TargetTypeEnum typeEnum : values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}