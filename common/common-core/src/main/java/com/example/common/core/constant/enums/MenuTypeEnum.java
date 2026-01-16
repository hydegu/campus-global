
package com.example.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 菜单常量
 */
@Getter
@RequiredArgsConstructor
public enum MenuTypeEnum {

	/**
	 * 目录
	 */
	LEFT_MENU("0", "left"),

	/**
	 * 菜单
	 */
	NORMAL_MENU("1", "top"),

	/**
	 * 按钮
	 */
	BUTTON("2", "button");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
