package com.example.admin.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商家余额更新类型枚举
 */
@Getter
@AllArgsConstructor
public enum MerchantBalanceUpdateTypeEnum {

	/**
	 * 余额更新
	 */
	BALANCE(1, "余额更新"),

	/**
	 * 累计总收入更新
	 */
	TOTAL_AMOUNT(2, "累计总收入更新");

	/**
	 * 类型编码
	 */
	private final Integer code;

	/**
	 * 类型描述
	 */
	private final String desc;

	/**
	 * 根据编码获取枚举
	 * @param code 编码
	 * @return 枚举
	 */
	public static MerchantBalanceUpdateTypeEnum getByCode(Integer code) {
		if (code == null) {
			return null;
		}
		for (MerchantBalanceUpdateTypeEnum typeEnum : values()) {
			if (typeEnum.getCode().equals(code)) {
				return typeEnum;
			}
		}
		return null;
	}
}