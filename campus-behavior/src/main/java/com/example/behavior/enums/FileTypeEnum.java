package com.example.behavior.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 文件类型枚举
 *
 * @author campus
 * @date 2026-01-28
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

	/**
	 * 图片
	 */
	IMAGE("image", "图片", Arrays.asList("jpg", "jpeg", "png", "gif", "webp", "bmp")),

	/**
	 * 视频
	 */
	VIDEO("video", "视频", Arrays.asList("mp4", "avi", "mov", "wmv")),

	/**
	 * 文档
	 */
	DOCUMENT("document", "文档", Arrays.asList("pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx")),

	/**
	 * 音频
	 */
	AUDIO("audio", "音频", Arrays.asList("mp3", "wav", "aac")),

	/**
	 * 压缩包
	 */
	ARCHIVE("archive", "压缩包", Arrays.asList("zip", "rar", "7z"));

	/**
	 * 文件类型编码
	 */
	private final String code;

	/**
	 * 文件类型描述
	 */
	private final String desc;

	/**
	 * 文件扩展名列表
	 */
	private final List<String> extensions;

	/**
	 * 根据扩展名获取文件类型
	 *
	 * @param extension 扩展名
	 * @return 文件类型枚举
	 */
	public static FileTypeEnum getByExtension(String extension) {
		if (extension == null || extension.isEmpty()) {
			return null;
		}
		for (FileTypeEnum typeEnum : values()) {
			if (typeEnum.getExtensions().contains(extension.toLowerCase())) {
				return typeEnum;
			}
		}
		return null;
	}

	/**
	 * 检查扩展名是否允许
	 *
	 * @param extension 扩展名
	 * @return 是否允许
	 */
	public static boolean isAllowed(String extension) {
		return getByExtension(extension) != null;
	}

	/**
	 * 获取MIME类型前缀
	 *
	 * @param contentType MIME类型
	 * @return MIME类型前缀
	 */
	public static String getMimePrefix(String contentType) {
		if (contentType == null || contentType.isEmpty()) {
			return null;
		}
		int index = contentType.indexOf('/');
		if (index > 0) {
			return contentType.substring(0, index);
		}
		return null;
	}

}