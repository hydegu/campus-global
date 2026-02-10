package com.example.behavior.config;

import cn.hutool.core.io.FileUtil;
import com.example.common.file.core.FileProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.File;

/**
 * 文件存储配置 - 路径标准化
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class FileStorageConfig {

	private final FileProperties fileProperties;

	@EventListener(ApplicationReadyEvent.class)
	public void normalizeStoragePath() {
		String basePath = fileProperties.getLocal().getBasePath();
		if (basePath == null || basePath.isEmpty()) {
			return;
		}

		// 将相对路径转换为绝对路径
		String absolutePath;
		if (basePath.startsWith("./") || basePath.startsWith(".\\")) {
			// 相对路径：基于项目根目录
			String projectRoot = System.getProperty("user.dir");
			absolutePath = projectRoot + File.separator + basePath.substring(2);
		} else if (new File(basePath).isAbsolute()) {
			// 已经是绝对路径
			absolutePath = basePath;
		} else {
			// 其他相对路径：基于项目根目录
			String projectRoot = System.getProperty("user.dir");
			absolutePath = projectRoot + File.separator + basePath;
		}

		// 规范化路径
		absolutePath = FileUtil.normalize(absolutePath);

		// 确保目录存在
		FileUtil.mkdir(absolutePath);

		// 更新配置
		fileProperties.getLocal().setBasePath(absolutePath);

		log.info("文件存储路径已标准化: {} -> {}", basePath, absolutePath);
	}

}