package com.example.behavior.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件信息实体
 *
 * @author campus
 * @date 2026-01-28
 */
@Data
@TableName(value = "app_file")
@Schema(name = "AppFile", description = "文件信息实体")
public class AppFile implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件ID
	 */
	@TableId(type = IdType.AUTO)
	@Schema(description = "文件ID")
	private Long id;

	/**
	 * 存储文件名
	 */
	@Schema(description = "存储文件名")
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
	 * 文件大小（字节）
	 */
	@Schema(description = "文件大小（字节）")
	private Long fileSize;

	/**
	 * 文件类型（MIME类型）
	 */
	@Schema(description = "文件类型（MIME类型）")
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
	 * 业务类型（avatar-头像，content_image-内容图片，attachment-附件等）
	 */
	@Schema(description = "业务类型")
	private String businessType;

	/**
	 * 业务ID（关联的业务记录ID）
	 */
	@Schema(description = "业务ID")
	private Long businessId;

	/**
	 * 状态：0-已删除，1-正常
	 */
	@Schema(description = "状态：0-已删除，1-正常")
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间")
	private LocalDateTime createAt;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(description = "更新时间")
	private LocalDateTime updateAt;

	/**
	 * 删除时间
	 */
	@TableLogic
	@Schema(description = "删除时间")
	private LocalDateTime deleteAt;

}