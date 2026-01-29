package com.example.behavior.controller;

import com.example.behavior.dto.FileQueryDTO;
import com.example.behavior.dto.FileUploadDTO;
import com.example.behavior.service.FileService;
import com.example.behavior.vo.FileInfoVO;
import com.example.behavior.vo.FileListVO;
import com.example.behavior.vo.FileUploadVO;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 文件管理Controller
 *
 * @author campus
 * @date 2026-01-28
 */
@Slf4j
@RestController
@RequestMapping("/api/app/file")
@RequiredArgsConstructor
@Validated
@Tag(name = "文件管理", description = "文件上传下载相关接口")
public class FileController {

	private final FileService fileService;

	@PostMapping("/upload")
	@Operation(summary = "上传文件", description = "支持单文件上传，需要登录认证")
	public Result<FileUploadVO> uploadFile(@Valid FileUploadDTO uploadDTO) {
		return fileService.uploadFile(uploadDTO);
	}

	@PostMapping("/upload/batch")
	@Operation(summary = "批量上传文件", description = "支持多文件上传，需要登录认证")
	public Result<List<FileUploadVO>> uploadFiles(
			@RequestParam("files") List<MultipartFile> files,
			@RequestParam(value = "businessType", required = false) String businessType,
			@RequestParam(value = "businessId", required = false) Long businessId) {
		return fileService.uploadFiles(files, businessType, businessId);
	}

	@GetMapping("/download/{fileId}")
	@PreAuthorize("permitAll()")
	@Operation(summary = "下载文件", description = "根据文件ID下载文件，公开访问")
	public void downloadFile(@PathVariable Long fileId, HttpServletResponse response) {
		try {
			// 获取文件字节数组
			byte[] fileBytes = fileService.downloadFile(fileId);

			// 获取文件信息用于设置响应头
			com.example.behavior.vo.FileInfoVO fileInfo = fileService.getFileInfo(fileId).getData();

			// 设置响应头
			response.setContentType(fileInfo.getFileType());
			response.setContentLengthLong(fileBytes.length);
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=" + URLEncoder.encode(fileInfo.getOriginalName(), StandardCharsets.UTF_8));

			// 写入响应流
			response.getOutputStream().write(fileBytes);
			response.getOutputStream().flush();
		}
		catch (IOException e) {
			log.error("文件下载失败，fileId: {}", fileId, e);
			throw new RuntimeException("文件下载失败", e);
		}
	}

	@GetMapping("/info/{fileId}")
	@Operation(summary = "查询文件信息", description = "根据文件ID查询文件详细信息，需要登录认证")
	public Result<FileInfoVO> getFileInfo(@PathVariable Long fileId) {
		return fileService.getFileInfo(fileId);
	}

	@GetMapping("/list")
	@Operation(summary = "查询文件列表", description = "分页查询文件列表，支持按业务类型筛选，需要登录认证")
	public Result<PageResult<FileListVO>> getFileList(@Valid FileQueryDTO queryDTO) {
		return fileService.getFileList(queryDTO);
	}

	@DeleteMapping("/{fileId}")
	@Operation(summary = "删除文件", description = "根据文件ID删除文件，需要登录认证")
	public Result<Void> deleteFile(@PathVariable Long fileId) {
		return fileService.deleteFile(fileId);
	}

	@DeleteMapping("/batch")
	@Operation(summary = "批量删除文件", description = "批量删除文件，需要登录认证")
	public Result<Void> deleteFiles(@RequestBody @NotEmpty(message = "文件ID列表不能为空") List<Long> fileIds) {
		return fileService.deleteFiles(fileIds);
	}

}