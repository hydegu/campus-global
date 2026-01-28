package com.example.behavior.enums;

import lombok.Getter;

/**
 * 浏览记录内容类型枚举
 * 用于标识浏览的内容类型：论坛帖子或外卖商家
 */
@Getter
public enum ContentTypeEnum {
    
    /**
     * 论坛帖子
     */
    FORUM_POST(1, "论坛帖子"),
    
    /**
     * 外卖商家
     */
    MERCHANT(2, "外卖商家");
    
    /**
     * 类型编码
     */
    private final Integer code;
    
    /**
     * 类型描述
     */
    private final String desc;
    
    ContentTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    /**
     * 根据编码获取枚举对象
     * @param code 类型编码
     * @return 枚举对象，未找到时返回null
     */
    public static ContentTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ContentTypeEnum typeEnum : values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
    
    /**
     * 根据编码获取描述
     * @param code 类型编码
     * @return 类型描述，未找到时返回"未知"
     */
    public static String getText(Integer code) {
        ContentTypeEnum typeEnum = getByCode(code);
        return typeEnum != null ? typeEnum.getDesc() : "未知";
    }
}