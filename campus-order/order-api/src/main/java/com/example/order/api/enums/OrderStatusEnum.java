package com.example.order.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

	WAIT_PAY(1, "待支付"),
	WAIT_ACCEPT(2, "待接单"),
	WAIT_PICKUP(3, "待取货"),
	DELIVERING(4, "配送中"),
	DELIVERED(5, "已送达"),
	CANCELLED(6, "已取消"),
	COMPLETED(7, "已完成"),
	AFTER_SALE(8, "售后中");

	private final Integer code;

	private final String desc;

	public static OrderStatusEnum getByCode(Integer code) {
		for (OrderStatusEnum statusEnum : values()) {
			if (statusEnum.getCode().equals(code)) {
				return statusEnum;
			}
		}
		return null;
	}
}
