package com.example.common.core.enums;

import lombok.Getter;

@Getter
public enum UserType {

	SYSTEM(1, "系统用户"),
	APP(2, "APP用户"),
	MERCHANT(3, "商家用户"),
	RIDER(4, "骑手用户"),
	PARTNER(5, "合伙人用户");

	private final Integer code;

	private final String desc;

	UserType(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static UserType getByCode(Integer code) {
		for (UserType type : values()) {
			if (type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
