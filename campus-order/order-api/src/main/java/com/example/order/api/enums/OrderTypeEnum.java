package com.example.order.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderTypeEnum {

	TAKEOUT(1, "外卖"),
	SERVICE(2, "服务"),
	OTHER(3, "其他");

	private final Integer code;

	private final String desc;

	public static OrderTypeEnum getByCode(Integer code) {
		for (OrderTypeEnum typeEnum : values()) {
			if (typeEnum.getCode().equals(code)) {
				return typeEnum;
			}
		}
		return null;
	}
}
