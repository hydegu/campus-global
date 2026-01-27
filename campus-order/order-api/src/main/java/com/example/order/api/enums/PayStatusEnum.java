package com.example.order.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayStatusEnum {

	UNPAID(0, "待支付"),
	PAID(1, "已支付"),
	PARTIAL_REFUND(2, "部分退款"),
	FULL_REFUND(3, "全额退款");

	private final Integer code;

	private final String desc;

	public static PayStatusEnum getByCode(Integer code) {
		for (PayStatusEnum statusEnum : values()) {
			if (statusEnum.getCode().equals(code)) {
				return statusEnum;
			}
		}
		return null;
	}
}
