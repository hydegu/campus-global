package com.example.common.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传配置属性
 * 从application.yml读取app.file配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "app.file")
public class FileUploadProperties {

    /**
     * 文件服务器访问地址
     * 开发环境：http://localhost:8080
     * 生产环境：http://172.16.8.74:8093
     */
    private String serverUrl;

    /**
     * 上传目录（相对于项目根目录）
     * 默认：uploads
     */
    private String uploadDir = "uploads";
}
