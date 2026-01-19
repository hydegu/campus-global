package com.example.order.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayMethodEnum {

	ONLINE(1, "在线支付"),
	WECHAT(2, "微信"),
	OFFLINE(3, "线下支付"),
	OTHER(4, "其他");

	private final Integer code;

	private final String desc;

	public static PayMethodEnum getByCode(Integer code) {
		for (PayMethodEnum methodEnum : values()) {
			if (methodEnum.getCode().equals(code)) {
				return methodEnum;
			}
		}
		return null;
	}
}
