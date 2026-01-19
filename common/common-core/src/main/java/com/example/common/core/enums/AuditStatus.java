package com.example.common.core.enums;

import lombok.Getter;

@Getter
public enum AuditStatus {

	PENDING(0, "待审核"),
	APPROVED(1, "已通过"),
	REJECTED(2, "已拒绝");

	private final Integer code;

	private final String desc;

	AuditStatus(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static AuditStatus getByCode(Integer code) {
		for (AuditStatus status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}

	public static String getText(Integer code) {
		AuditStatus status = getByCode(code);
		return status != null ? status.getDesc() : "未知";
	}
}
