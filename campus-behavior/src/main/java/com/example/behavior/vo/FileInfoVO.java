package com.example.behavior.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件信息返回VO
 *
 * @author campus
 * @date 2026-01-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "FileInfoVO", description = "文件信息返回VO")
public class FileInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件ID
	 */
	@Schema(description = "文件ID")
	private Long fileId;

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
	 * 文件存储路径
	 */
	@Schema(description = "文件存储路径")
	private String filePath;

	/**
	 * 文件访问URL
	 */
	@Schema(description = "文件访问URL")
	private String fileUrl;

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

	/**
	 * 文件扩展名
	 */
	@Schema(description = "文件扩展名")
	private String fileExtension;

	/**
	 * 存储桶名称
	 */
	@Schema(description = "存储桶名称")
	private String bucketName;

	/**
	 * 上传用户ID
	 */
	@Schema(description = "上传用户ID")
	private Long uploadUserId;

	/**
	 * 业务类型
	 */
	@Schema(description = "业务类型")
	private String businessType;

	/**
	 * 业务ID
	 */
	@Schema(description = "业务ID")
	private Long businessId;

	/**
	 * 状态：0-已删除，1-正常
	 */
	@Schema(description = "状态")
	private Integer status;

	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	private LocalDateTime createAt;

}