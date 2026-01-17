package com.example.common.core.enums;

import lombok.Getter;

@Getter
public enum Gender {

	MALE(1, "男"),
	FEMALE(2, "女"),
	UNKNOWN(0, "未知");

	private final Integer code;

	private final String desc;

	Gender(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static Gender getByCode(Integer code) {
		for (Gender gender : values()) {
			if (gender.getCode().equals(code)) {
				return gender;
			}
		}
		return UNKNOWN;
	}

	public static String getText(Integer code) {
		Gender gender = getByCode(code);
		return gender != null ? gender.getDesc() : "未知";
	}
}
