package com.example.behavior.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 文件上传请求DTO
 *
 * @author campus
 * @date 2026-01-28
 */
@Data
@Schema(name = "FileUploadDTO", description = "文件上传请求DTO")
public class FileUploadDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件
	 */
	@NotNull(message = "文件不能为空")
	@Schema(description = "文件", requiredMode = Schema.RequiredMode.REQUIRED)
	private MultipartFile file;

	/**
	 * 业务类型（avatar-头像，content_image-内容图片等）
	 */
	@Schema(description = "业务类型")
	private String businessType;

	/**
	 * 业务ID（关联的业务记录ID）
	 */
	@Schema(description = "业务ID")
	private Long businessId;

}