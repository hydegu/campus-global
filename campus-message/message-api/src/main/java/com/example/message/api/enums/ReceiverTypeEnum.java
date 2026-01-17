package com.example.message.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReceiverTypeEnum {

	USER(1, "用户"),
	RIDER(2, "骑手"),
	MERCHANT(3, "商家"),
	PARTNER(4, "合伙人");

	private final Integer code;

	private final String desc;

	public static ReceiverTypeEnum getByCode(Integer code) {
		for (ReceiverTypeEnum typeEnum : values()) {
			if (typeEnum.getCode().equals(code)) {
				return typeEnum;
			}
		}
		return null;
	}
}
