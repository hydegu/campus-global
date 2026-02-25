package com.example.order.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

	WAIT_ACCEPT(1, "待接单"),
	WAIT_PICKUP(2, "待取货"),
	DELIVERING(3, "配送中"),
	DELIVERED(4, "已送达"),
	CANCELLED(5, "已取消"),
	COMPLETED(6, "已完成"),
	AFTER_SALE(7, "售后中");

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
