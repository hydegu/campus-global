package com.example.behavior.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务类型枚举
 *
 * @author campus
 * @date 2026-01-28
 */
@Getter
@AllArgsConstructor
public enum BusinessTypeEnum {

	/**
	 * 用户头像
	 */
	AVATAR("avatar", "用户头像"),

	/**
	 * 内容图片
	 */
	CONTENT_IMAGE("content_image", "内容图片"),

	/**
	 * 附件
	 */
	ATTACHMENT("attachment", "附件"),

	/**
	 * 二维码
	 */
	QRCODE("qrcode", "二维码");

	/**
	 * 业务类型编码
	 */
	private final String code;

	/**
	 * 业务类型描述
	 */
	private final String desc;

	/**
	 * 根据编码获取枚举
	 *
	 * @param code 编码
	 * @return 枚举
	 */
	public static BusinessTypeEnum getByCode(String code) {
		if (code == null) {
			return null;
		}
		for (BusinessTypeEnum typeEnum : values()) {
			if (typeEnum.getCode().equals(code)) {
				return typeEnum;
			}
		}
		return null;
	}

	/**
	 * 获取描述
	 *
	 * @param code 编码
	 * @return 描述
	 */
	public static String getText(String code) {
		BusinessTypeEnum typeEnum = getByCode(code);
		return typeEnum == null ? null : typeEnum.getDesc();
	}

}