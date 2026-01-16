
package com.example.common.file.core;

import com.example.common.file.local.LocalFileProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 文件 配置信息
 * bucket 目录
 */
@Data
@ConfigurationProperties(prefix = "file")
public class FileProperties {


	/**
	 * 默认的存储桶名称
	 */
	private String bucketName = "local";


	/**
	 * 本地文件配置信息
	 */
	@NestedConfigurationProperty
	private LocalFileProperties local;

}
