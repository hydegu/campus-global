package com.example.behavior.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件上传返回VO
 *
 * @author campus
 * @date 2026-01-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "FileUploadVO", description = "文件上传返回VO")
public class FileUploadVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件ID
	 */
	@Schema(description = "文件ID")
	private Long fileId;

	/**
	 * 文件访问URL
	 */
	@Schema(description = "文件访问URL")
	private String fileUrl;

	/**
	 * 文件名
	 */
	@Schema(description = "文件名")
	private String fileName;

	/**
	 * 原始文件名
	 */
	@Schema(description = "原始文件名")
	private String originalName;

	/**
	 * 文件大小（字节）
	 */
	@Schema(description = "文件大小（字节）")
	private Long fileSize;

	/**
	 * 文件类型
	 */
	@Schema(description = "文件类型")
	private String fileType;

}