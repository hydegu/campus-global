
package com.example.common.core.constant;

/**
 * 通用常量
 */
public interface CommonConstants {

	/**
	 * 停用
	 */
	String STATUS_DEL = "0";

	/**
	 * 正常
	 */
	String STATUS_NORMAL = "1";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单树根节点
	 */
	Long MENU_TREE_ROOT_ID = 0L;

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * JSON 资源
	 */
	String CONTENT_TYPE = "application/json; charset=utf-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "campus-frontend";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "campus-backend";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 1;

	/**
	 * 失败标记
	 */
	Integer FAIL = 0;

	/**
	 * 当前页
	 */
	String CURRENT = "current";

	/**
	 * size
	 */
	String SIZE = "size";

	/**
	 * 请求开始时间
	 */
	String REQUEST_START_TIME = "REQUEST-START-TIME";

}
