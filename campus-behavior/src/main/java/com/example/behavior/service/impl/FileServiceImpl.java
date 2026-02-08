package com.example.behavior.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.behavior.dto.FileQueryDTO;
import com.example.behavior.dto.FileUploadDTO;
import com.example.behavior.entity.AppFile;
import com.example.behavior.enums.BusinessTypeEnum;
import com.example.behavior.enums.FileTypeEnum;
import com.example.behavior.mapper.AppFileMapper;
import com.example.behavior.service.FileService;
import com.example.behavior.vo.FileInfoVO;
import com.example.behavior.vo.FileListVO;
import com.example.behavior.vo.FileUploadVO;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.common.file.core.FileTemplate;
import com.example.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件服务实现
 *
 * @author campus
 * @date 2026-01-28
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl extends ServiceImpl<AppFileMapper, AppFile> implements FileService {

	private final FileTemplate fileTemplate;

	@Value("${file.url-prefix:http://localhost:8080/files}")
	private String fileUrlPrefix;

	/**
	 * 文件大小限制（字节）
	 */
	private static final long MAX_IMAGE_SIZE = 10 * 1024 * 1024; // 10MB
	private static final long MAX_VIDEO_SIZE = 50 * 1024 * 1024; // 50MB
	private static final long MAX_DOCUMENT_SIZE = 10 * 1024 * 1024; // 10MB
	private static final long MAX_DEFAULT_SIZE = 10 * 1024 * 1024; // 10MB

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result<FileUploadVO> uploadFile(FileUploadDTO uploadDTO) {
		MultipartFile file = uploadDTO.getFile();
		String businessType = uploadDTO.getBusinessType();
		Long businessId = uploadDTO.getBusinessId();

		// 1. 验证文件
		validateFile(file);

		// 2. 获取文件信息
		String originalFilename = file.getOriginalFilename();
		String fileExtension = FileUtil.extName(originalFilename);
		String contentType = file.getContentType();

		// 3. 生成文件名和路径
		String fileName = generateFileName(fileExtension);
		String filePath = generateFilePath(businessType, fileName);
		String bucketName = getBucketName(businessType);

		// 4. 上传文件到存储桶
		String objectName = null;
		try {
			objectName = filePath;
			InputStream inputStream = file.getInputStream();
			fileTemplate.putObject(bucketName, objectName, inputStream, contentType);
			log.info("文件上传成功，bucketName: {}, objectName: {}", bucketName, objectName);
		}
		catch (Exception e) {
			log.error("文件上传失败", e);
			throw new BusinessException("FILE_UPLOAD_FAILED", "文件上传失败：" + e.getMessage());
		}

		// 5. 保存数据库记录
		AppFile appFile = new AppFile();
		appFile.setFileName(fileName);
		appFile.setOriginalName(originalFilename);
		appFile.setFilePath(filePath);
		appFile.setFileSize(file.getSize());
		appFile.setFileType(contentType);
		appFile.setFileExtension(fileExtension);
		appFile.setBucketName(bucketName);
		appFile.setUploadUserId(SecurityUtils.getCurrentUserId());
		appFile.setBusinessType(businessType);
		appFile.setBusinessId(businessId);
		appFile.setStatus(1);
		appFile.setCreateAt(LocalDateTime.now());
		appFile.setUpdateAt(LocalDateTime.now());

		this.save(appFile);

		// 6. 构建返回结果
		String fileUrl = buildFileUrl(bucketName, filePath);
		String relativePath = buildRelativePath(bucketName, filePath);
		FileUploadVO uploadVO = FileUploadVO.builder()
			.fileId(appFile.getId())
			.fileUrl(fileUrl)
			.relativePath(relativePath)
			.fileName(fileName)
			.originalName(originalFilename)
			.fileSize(file.getSize())
			.fileType(contentType)
			.build();

		return Result.ok(uploadVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result<List<FileUploadVO>> uploadFiles(List<MultipartFile> files, String businessType, Long businessId) {
		List<FileUploadVO> resultList = new ArrayList<>();
		List<String> errorMessages = new ArrayList<>();

		for (MultipartFile file : files) {
			try {
				FileUploadDTO uploadDTO = new FileUploadDTO();
				uploadDTO.setFile(file);
				uploadDTO.setBusinessType(businessType);
				uploadDTO.setBusinessId(businessId);

				Result<FileUploadVO> result = uploadFile(uploadDTO);
				if (result.getCode() == com.example.common.core.constant.CommonConstants.SUCCESS) {
					resultList.add(result.getData());
				}
				else {
					errorMessages.add(file.getOriginalFilename() + ": " + result.getMsg());
				}
			}
			catch (Exception e) {
				log.error("上传文件失败：{}", file.getOriginalFilename(), e);
				errorMessages.add(file.getOriginalFilename() + ": " + e.getMessage());
			}
		}

		if (errorMessages.isEmpty()) {
			return Result.ok(resultList);
		}
		else {
			return Result.failed(resultList, "部分文件上传失败：" + String.join("; ", errorMessages));
		}
	}

	@Override
	public byte[] downloadFile(Long fileId) {
		// 1. 查询文件信息
		AppFile appFile = this.getById(fileId);
		if (appFile == null || appFile.getStatus() == 0) {
			throw new BusinessException("FILE_NOT_FOUND", "文件不存在或已删除");
		}

		// 2. 获取文件流
		try {
			Object fileObject = fileTemplate.getObject(appFile.getBucketName(), appFile.getFilePath());
			if (fileObject instanceof InputStream) {
				InputStream inputStream = (InputStream) fileObject;
				return inputStream.readAllBytes();
			}
			else {
				throw new BusinessException("FILE_DOWNLOAD_FAILED", "文件获取失败");
			}
		}
		catch (Exception e) {
			log.error("文件下载失败，fileId: {}", fileId, e);
			throw new BusinessException("FILE_DOWNLOAD_FAILED", "文件下载失败：" + e.getMessage());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result<Void> deleteFile(Long fileId) {
		// 1. 查询文件信息
		AppFile appFile = this.getById(fileId);
		if (appFile == null || appFile.getStatus() == 0) {
			throw new BusinessException("FILE_NOT_FOUND", "文件不存在或已删除");
		}

		// 2. 检查删除权限
		Long currentUserId = SecurityUtils.getCurrentUserId();
		if (currentUserId == 0) {
			throw new BusinessException("NOT_LOGIN", "用户未登录");
		}
		if (!appFile.getUploadUserId().equals(currentUserId)) {
			throw new BusinessException("PERMISSION_DENIED", "无权删除该文件");
		}

		// 3. 删除存储文件
		try {
			fileTemplate.removeObject(appFile.getBucketName(), appFile.getFilePath());
			log.info("文件删除成功，bucketName: {}, filePath: {}", appFile.getBucketName(), appFile.getFilePath());
		}
		catch (Exception e) {
			log.error("文件删除失败，fileId: {}", fileId, e);
			// 记录日志但不阻断流程
		}

		// 4. 更新数据库记录（软删除）
		appFile.setStatus(0);
		appFile.setDeleteAt(LocalDateTime.now());
		appFile.setUpdateAt(LocalDateTime.now());
		this.updateById(appFile);

		return Result.ok();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result<Void> deleteFiles(List<Long> fileIds) {
		for (Long fileId : fileIds) {
			try {
				deleteFile(fileId);
			}
			catch (Exception e) {
				log.error("删除文件失败，fileId: {}", fileId, e);
			}
		}
		return Result.ok();
	}

	@Override
	public Result<FileInfoVO> getFileInfo(Long fileId) {
		// 1. 查询文件信息
		AppFile appFile = this.getById(fileId);
		if (appFile == null || appFile.getStatus() == 0) {
			throw new BusinessException("FILE_NOT_FOUND", "文件不存在或已删除");
		}

		// 2. 检查访问权限
		Long currentUserId = SecurityUtils.getCurrentUserId();
		if (currentUserId == 0) {
			throw new BusinessException("NOT_LOGIN", "用户未登录");
		}
		if (!appFile.getUploadUserId().equals(currentUserId)) {
			throw new BusinessException("PERMISSION_DENIED", "无权访问该文件");
		}

		// 3. 构建返回结果
		String fileUrl = buildFileUrl(appFile.getBucketName(), appFile.getFilePath());
		String relativePath = buildRelativePath(appFile.getBucketName(), appFile.getFilePath());
		FileInfoVO fileInfoVO = FileInfoVO.builder()
			.fileId(appFile.getId())
			.fileName(appFile.getFileName())
			.originalName(appFile.getOriginalName())
			.filePath(appFile.getFilePath())
			.fileUrl(fileUrl)
			.relativePath(relativePath)
			.fileSize(appFile.getFileSize())
			.fileType(appFile.getFileType())
			.fileExtension(appFile.getFileExtension())
			.bucketName(appFile.getBucketName())
			.uploadUserId(appFile.getUploadUserId())
			.businessType(appFile.getBusinessType())
			.businessId(appFile.getBusinessId())
			.status(appFile.getStatus())
			.createAt(appFile.getCreateAt())
			.build();

		return Result.ok(fileInfoVO);
	}

	@Override
	public Result<PageResult<FileListVO>> getFileList(FileQueryDTO queryDTO) {
		// 1. 获取当前用户ID
		Long currentUserId = SecurityUtils.getCurrentUserId();
		if (currentUserId == 0) {
			throw new BusinessException("NOT_LOGIN", "用户未登录");
		}

		// 2. 设置查询条件
		Long uploadUserId = queryDTO.getUploadUserId();
		if (uploadUserId == null) {
			uploadUserId = currentUserId;
		}

		// 3. 构建查询条件
		LambdaQueryWrapper<AppFile> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(AppFile::getUploadUserId, uploadUserId);
		queryWrapper.eq(AppFile::getStatus, 1);

		if (queryDTO.getBusinessType() != null && !queryDTO.getBusinessType().isEmpty()) {
			queryWrapper.eq(AppFile::getBusinessType, queryDTO.getBusinessType());
		}

		if (queryDTO.getFileType() != null && !queryDTO.getFileType().isEmpty()) {
			queryWrapper.likeRight(AppFile::getFileType, queryDTO.getFileType());
		}

		queryWrapper.orderByDesc(AppFile::getCreateAt);

		// 4. 分页查询
		Page<AppFile> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
		IPage<AppFile> pageResult = this.page(page, queryWrapper);

		// 5. 转换为VO
		List<FileListVO> voList = new ArrayList<>();
		for (AppFile appFile : pageResult.getRecords()) {
			String fileUrl = buildFileUrl(appFile.getBucketName(), appFile.getFilePath());
			String relativePath = buildRelativePath(appFile.getBucketName(), appFile.getFilePath());
			FileListVO vo = FileListVO.builder()
				.fileId(appFile.getId())
				.fileName(appFile.getFileName())
				.originalName(appFile.getOriginalName())
				.fileUrl(fileUrl)
				.relativePath(relativePath)
				.fileSize(appFile.getFileSize())
				.fileType(appFile.getFileType())
				.businessType(appFile.getBusinessType())
				.uploadUserId(appFile.getUploadUserId())
				.createAt(appFile.getCreateAt())
				.build();
			voList.add(vo);
		}

		// 6. 构建返回结果
		PageResult<FileListVO> result = new PageResult<>();
		result.setTotal(pageResult.getTotal());
		result.setCurrent(pageResult.getCurrent());
		result.setPageSize(pageResult.getSize());
		result.setPages(pageResult.getPages());
		result.setList(voList);

		return Result.ok(result);
	}

	/**
	 * 验证文件
	 */
	private void validateFile(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new BusinessException("INVALID_PARAM", "文件不能为空");
		}

		// 验证文件大小
		validateFileSize(file);

		// 验证文件类型
		validateFileType(file);
	}

	/**
	 * 验证文件大小
	 */
	private void validateFileSize(MultipartFile file) {
		long fileSize = file.getSize();
		String contentType = file.getContentType();
		String mimePrefix = FileTypeEnum.getMimePrefix(contentType);

		long maxSize = MAX_DEFAULT_SIZE;
		if ("image".equals(mimePrefix)) {
			maxSize = MAX_IMAGE_SIZE;
		}
		else if ("video".equals(mimePrefix)) {
			maxSize = MAX_VIDEO_SIZE;
		}
		else if ("document".equals(mimePrefix)) {
			maxSize = MAX_DOCUMENT_SIZE;
		}

		if (fileSize > maxSize) {
			throw new BusinessException("FILE_SIZE_EXCEEDED", "文件大小超过限制，最大允许：" + (maxSize / 1024 / 1024) + "MB");
		}
	}

	/**
	 * 验证文件类型
	 */
	private void validateFileType(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String fileExtension = FileUtil.extName(originalFilename);
		String contentType = file.getContentType();

		// 校验文件扩展名
		if (!FileTypeEnum.isAllowed(fileExtension)) {
			throw new BusinessException("FILE_TYPE_NOT_SUPPORTED", "不支持的文件类型：" + fileExtension);
		}

		// 校验MIME类型
		FileTypeEnum fileTypeEnum = FileTypeEnum.getByExtension(fileExtension);
		if (fileTypeEnum != null) {
			String mimePrefix = FileTypeEnum.getMimePrefix(contentType);
			if (!fileTypeEnum.getCode().equals(mimePrefix)) {
				throw new BusinessException("FILE_TYPE_MISMATCH", "文件类型与扩展名不匹配");
			}
		}
	}

	/**
	 * 生成文件名
	 */
	private String generateFileName(String extension) {
		return IdUtil.fastSimpleUUID() + "." + extension;
	}

	/**
	 * 生成文件存储路径
	 */
	private String generateFilePath(String businessType, String fileName) {
		LocalDate now = LocalDate.now();
		String path = String.format("%s/%d/%02d/%02d/%s", businessType != null ? businessType : "default",
				now.getYear(), now.getMonthValue(), now.getDayOfMonth(), fileName);

		// 规范化路径，防止路径遍历
		java.nio.file.Path normalizedPath = Paths.get(path).normalize();
		if (normalizedPath.startsWith("..")) {
			throw new BusinessException("INVALID_FILE_PATH", "非法的文件路径");
		}

		return normalizedPath.toString().replace("\\", "/");
	}

	/**
	 * 获取存储桶名称
	 */
	private String getBucketName(String businessType) {
		BusinessTypeEnum businessTypeEnum = BusinessTypeEnum.getByCode(businessType);
		if (businessTypeEnum != null) {
			return "campus-" + businessTypeEnum.getCode();
		}
		return "campus-default";
	}

	/**
	 * 构建文件访问URL
	 */
	private String buildFileUrl(String bucketName, String filePath) {
		return fileUrlPrefix + "/" + bucketName + "/" + filePath;
	}

	/**
	 * 构建文件相对路径
	 */
	private String buildRelativePath(String bucketName, String filePath) {
		return "/files/" + bucketName + "/" + filePath;
	}

}
