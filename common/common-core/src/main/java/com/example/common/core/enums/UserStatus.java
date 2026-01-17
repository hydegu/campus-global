package com.example.common.core.enums;

import lombok.Getter;

@Getter
public enum UserStatus {

	DISABLED(0, "禁用"),
	ENABLED(1, "启用");

	private final Integer code;

	private final String desc;

	UserStatus(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static UserStatus getByCode(Integer code) {
		for (UserStatus status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
}
