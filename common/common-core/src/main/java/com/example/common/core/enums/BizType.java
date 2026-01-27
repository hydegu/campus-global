package com.example.common.core.enums;

import lombok.Getter;

@Getter
public enum BizType {

	MERCHANT_SETTLE("MERCHANT_SETTLE", "商家入驻"),
	WITHDRAW("WITHDRAW", "提现"),
	GOODS("GOODS", "商品上架"),
	STAFF_APPLY("STAFF_APPLY", "服务人员"),
	RIDER_APPLY("RIDER_APPLY", "骑手");

	private final String code;

	private final String desc;

	BizType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static BizType getByCode(String code) {
		for (BizType bizType : values()) {
			if (bizType.getCode().equals(code)) {
				return bizType;
			}
		}
		return null;
	}

	public static String getText(String code) {
		BizType bizType = getByCode(code);
		return bizType != null ? bizType.getDesc() : "未知";
	}
}