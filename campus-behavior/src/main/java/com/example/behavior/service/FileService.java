package com.example.behavior.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.behavior.dto.FileQueryDTO;
import com.example.behavior.dto.FileUploadDTO;
import com.example.behavior.entity.AppFile;
import com.example.behavior.vo.FileInfoVO;
import com.example.behavior.vo.FileListVO;
import com.example.behavior.vo.FileUploadVO;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件服务接口
 *
 * @author campus
 * @date 2026-01-28
 */
public interface FileService extends IService<AppFile> {

	/**
	 * 上传文件
	 *
	 * @param uploadDTO 上传请求DTO
	 * @return 上传结果
	 */
	Result<FileUploadVO> uploadFile(FileUploadDTO uploadDTO);

	/**
	 * 批量上传文件
	 *
	 * @param files        文件列表
	 * @param businessType 业务类型
	 * @param businessId   业务ID
	 * @return 上传结果列表
	 */
	Result<List<FileUploadVO>> uploadFiles(List<MultipartFile> files, String businessType, Long businessId);

	/**
	 * 下载文件
	 *
	 * @param fileId 文件ID
	 * @return 文件字节数组
	 */
	byte[] downloadFile(Long fileId);

	/**
	 * 删除文件
	 *
	 * @param fileId 文件ID
	 * @return 操作结果
	 */
	Result<Void> deleteFile(Long fileId);

	/**
	 * 批量删除文件
	 *
	 * @param fileIds 文件ID列表
	 * @return 操作结果
	 */
	Result<Void> deleteFiles(List<Long> fileIds);

	/**
	 * 查询文件信息
	 *
	 * @param fileId 文件ID
	 * @return 文件信息
	 */
	Result<FileInfoVO> getFileInfo(Long fileId);

	/**
	 * 分页查询文件列表
	 *
	 * @param queryDTO 查询条件
	 * @return 文件列表
	 */
	Result<PageResult<FileListVO>> getFileList(FileQueryDTO queryDTO);

}