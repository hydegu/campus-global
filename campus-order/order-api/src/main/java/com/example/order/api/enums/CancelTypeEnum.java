package com.example.order.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CancelTypeEnum {

	USER_CANCEL(1, "用户取消"),
	MERCHANT_CANCEL(2, "商家取消"),
	RIDER_CANCEL(3, "骑手取消"),
	TIMEOUT_CANCEL(4, "超时取消");

	private final Integer code;

	private final String desc;

	public static CancelTypeEnum getByCode(Integer code) {
		for (CancelTypeEnum cancelTypeEnum : values()) {
			if (cancelTypeEnum.getCode().equals(code)) {
				return cancelTypeEnum;
			}
		}
		return null;
	}
}
