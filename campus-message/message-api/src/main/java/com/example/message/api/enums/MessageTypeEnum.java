package com.example.message.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

	SYSTEM("system", "系统通知"),
	REMIND("remind", "提醒"),
	PRIVATE("private", "私信");

	private final String code;

	private final String desc;

	public static MessageTypeEnum getByCode(String code) {
		for (MessageTypeEnum typeEnum : values()) {
			if (typeEnum.getCode().equals(code)) {
				return typeEnum;
			}
		}
		return null;
	}
}
