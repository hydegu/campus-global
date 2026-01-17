package com.example.message.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SenderTypeEnum {

	SYSTEM(1, "系统"),
	ADMIN(2, "管理员"),
	USER(3, "用户"),
	RIDER(4, "骑手"),
	MERCHANT(5, "商家"),
	PARTNER(6, "合伙人");

	private final Integer code;

	private final String desc;

	public static SenderTypeEnum getByCode(Integer code) {
		for (SenderTypeEnum typeEnum : values()) {
			if (typeEnum.getCode().equals(code)) {
				return typeEnum;
			}
		}
		return null;
	}
}
