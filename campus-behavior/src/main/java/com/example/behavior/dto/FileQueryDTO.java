package com.example.behavior.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件查询请求DTO
 *
 * @author campus
 * @date 2026-01-28
 */
@Data
@Schema(name = "FileQueryDTO", description = "文件查询请求DTO")
public class FileQueryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	@Schema(description = "页码", example = "1")
	private Integer pageNum = 1;

	/**
	 * 每页条数
	 */
	@Schema(description = "每页条数", example = "10")
	private Integer pageSize = 10;

	/**
	 * 业务类型
	 */
	@Schema(description = "业务类型")
	private String businessType;

	/**
	 * 上传用户ID（如果不指定，默认查询当前用户的文件）
	 */
	@Schema(description = "上传用户ID")
	private Long uploadUserId;

	/**
	 * 文件类型（MIME类型前缀，如image、video等）
	 */
	@Schema(description = "文件类型")
	private String fileType;

}