package com.example.behavior.config;

import cn.hutool.core.io.FileUtil;
import com.example.common.file.core.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * Web MVC 配置 - 文件静态资源映射
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {


	@Value("${file.local.base-path:./data/upload/campus-behavior}")
	private String basePath;

	private final FileProperties fileProperties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 将相对路径转换为绝对路径，确保基于项目根目录
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

		// 将 /files/** 映射到本地文件存储目录
		// 将 Windows 路径转换为 file: URL 格式
		String resourceLocation = absolutePath.replace("\\", "/");
		if (absolutePath.contains(":")) {
			// Windows 绝对路径，需要添加 file:///
			resourceLocation = "file:///" + resourceLocation + "/";
		} else {
			// Unix 路径，使用 file:/
			resourceLocation = "file:" + resourceLocation + "/";
		}
		registry.addResourceHandler("/files/**")
				.addResourceLocations(resourceLocation)
				.setCachePeriod(3600);

	}

}