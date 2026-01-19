package com.example.forum.common.utils;

import com.example.forum.common.config.FileUploadProperties;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传工具类
 */
@Slf4j
@Component
@RequiredArgsConstructor
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class FileUploadUtil {

    private final FileUploadProperties fileUploadProperties;
    private final RestTemplate restTemplate;

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("${app.file.use-dynamic-ip:false}")
    private boolean useDynamicIp;

    /**
     * 允许上传的图片格式
     */
    private static final List<String> ALLOWED_IMAGE_EXTENSIONS = Arrays.asList(
            "jpg", "jpeg", "png", "gif", "bmp", "webp"
    );

    /**
     * 允许的MIME类型
     */
    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/bmp",
            "image/webp"
    );

    /**
     * 图片最大大小：5MB
     */
    private static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024;

    /**
     * 获取项目根目录的绝对路径
     */
    private String getProjectRootPath() {
        return System.getProperty("user.dir");
    }

    /**
     * 上传图片文件
     *
     * @param file      上传的文件
     * @return 文件相对路径（存储到数据库）
     */
    public String uploadImage(MultipartFile file) {
        // 1. 检查文件是否为空
        if (file == null || file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }

        // 2. 检查文件大小
        if (file.getSize() > MAX_IMAGE_SIZE) {
            throw new BusinessException("图片大小不能超过5MB");
        }

        // 3. 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.trim().isEmpty()) {
            throw new BusinessException("文件名不能为空");
        }

        // 4. 安全检查：防止路径遍历攻击和危险字符
        validateFilename(originalFilename);

        // 5. 检查文件扩展名
        String extension = getFileExtension(originalFilename);
        if (!ALLOWED_IMAGE_EXTENSIONS.contains(extension.toLowerCase())) {
            throw new BusinessException("只支持上传图片格式：" + String.join(", ", ALLOWED_IMAGE_EXTENSIONS));
        }

        // 6. 安全检查：验证MIME类型
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_MIME_TYPES.contains(contentType.toLowerCase())) {
            log.warn("文件MIME类型不合法: {}, 文件名: {}", contentType, originalFilename);
            throw new BusinessException("文件类型不合法，仅支持图片文件");
        }

        // 7. 安全检查：验证文件头（Magic Bytes）
        try {
            if (!isValidImageFile(file)) {
                log.warn("文件内容验证失败，可能不是真实的图片文件: {}", originalFilename);
                throw new BusinessException("文件内容不合法，请上传真实的图片文件");
            }
        } catch (IOException e) {
            log.error("读取文件内容失败: {}", e.getMessage());
            throw new BusinessException("文件读取失败");
        }

        try {
            // 获取项目根目录
            String projectRoot = getProjectRootPath();

            // 从配置获取上传目录
            String uploadDir = fileUploadProperties.getUploadDir();

            // 按日期创建子目录：uploads/2025-12-05/
            String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // 构建绝对路径：项目根目录 + uploads + 日期
            String fullUploadDir = projectRoot + File.separator + uploadDir + File.separator + datePath;

            // 创建上传目录
            Path uploadPath = Paths.get(fullUploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("创建上传目录: {}", fullUploadDir);
            }

            // 生成唯一文件名：UUID + 原扩展名
            String newFilename = UUID.randomUUID().toString().replace("-", "") + "." + extension;

            // 完整文件路径（绝对路径）
            Path filePath = uploadPath.resolve(newFilename);

            // 保存文件
            file.transferTo(filePath.toFile());

            // 返回相对路径（存储到数据库，便于服务器地址变更）
            String relativePath = "/" + uploadDir + "/" + datePath + "/" + newFilename;

            log.info("文件上传成功，相对路径: {}", relativePath);

            return relativePath;

        } catch (IOException e) {
            log.error("文件上传失败: {}", e.getMessage(), e);
            throw new BusinessException("文件上传失败: " + e.getMessage());
        }
    }
    

    /**
     * 删除文件
     *
     * @param fileUrl 文件URL（可以是完整URL或相对路径）
     * @return 是否删除成功
     */
    public boolean deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.trim().isEmpty()) {
            return false;
        }

        try {
            // 如果是完整URL，提取相对路径部分
            String relativePath = fileUrl;
            if (fileUrl.startsWith("http://") || fileUrl.startsWith("https://")) {
                // 去掉域名部分，只保留路径
                relativePath = fileUrl.substring(fileUrl.indexOf("/", 8)); // 8 = "https://".length()
            }

            // 去掉开头的斜杠
            if (relativePath.startsWith("/")) {
                relativePath = relativePath.substring(1);
            }

            // 构建绝对路径：项目根目录 + 相对路径
            String projectRoot = getProjectRootPath();
            String absolutePath = projectRoot + File.separator + relativePath.replace("/", File.separator);

            Path filePath = Paths.get(absolutePath);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                log.info("文件删除成功: {}", fileUrl);
                return true;
            } else {
                log.warn("文件不存在: {}", absolutePath);
                return false;
            }
        } catch (IOException e) {
            log.error("文件删除失败: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获取文件扩展名
     *
     * @param filename 文件名
     * @return 扩展名（不含点）
     */
    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return filename.substring(lastDotIndex + 1);
    }

    /**
     * 检查是否是图片文件
     *
     * @param filename 文件名
     * @return 是否是图片
     */
    public boolean isImage(String filename) {
        if (filename == null) {
            return false;
        }
        String extension = getFileExtension(filename);
        return ALLOWED_IMAGE_EXTENSIONS.contains(extension.toLowerCase());
    }

    /**
     * 将相对路径转换为完整URL
     * 如果已经是完整URL则直接返回
     *
     * @param relativePath 相对路径（如：/uploads/2025-12-05/xxx.jpg）
     * @return 完整URL（如：http://172.16.8.74:8093/uploads/2025-12-05/xxx.jpg）
     */
    public String buildFullUrl(String relativePath) {
        if (relativePath == null || relativePath.trim().isEmpty()) {
            return null;
        }

        if (relativePath.startsWith("http://") || relativePath.startsWith("https://")) {
            return relativePath;
        }

        String serverUrl;
        
        if (useDynamicIp) {
            serverUrl = NetworkUtil.getServerUrl(serverPort);
            log.debug("使用动态IP构建URL: {}", serverUrl);
        } else {
            serverUrl = fileUploadProperties.getServerUrl();
        }

        if (!relativePath.startsWith("/")) {
            relativePath = "/" + relativePath;
        }

        return serverUrl + relativePath;
    }

    /**
     * 下载微信头像URL并保存到服务器
     *
     * @param avatarUrl 微信头像URL
     * @return 文件相对路径（存储到数据库）
     */
    public String downloadAndSaveWeChatAvatar(String avatarUrl) {
        if (avatarUrl == null || avatarUrl.trim().isEmpty()) {
            throw new BusinessException("头像URL不能为空");
        }

        try {
            // 获取项目根目录
            String projectRoot = getProjectRootPath();

            // 从配置获取上传目录
            String uploadDir = fileUploadProperties.getUploadDir();

            // 按日期创建子目录：uploads/2025-12-05/
            String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // 构建绝对路径：项目根目录 + uploads + 日期
            String fullUploadDir = projectRoot + File.separator + uploadDir + File.separator + datePath;

            // 创建上传目录
            Path uploadPath = Paths.get(fullUploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("创建上传目录: {}", fullUploadDir);
            }

            // 生成唯一文件名：wx_avatar + UUID + .jpg (微信头像通常是jpg格式)
            String newFilename = "wx_avatar_" + UUID.randomUUID().toString().replace("-", "") + ".jpg";

            // 完整文件路径（绝对路径）
            Path filePath = uploadPath.resolve(newFilename);

            // 下载微信头像
            log.info("开始下载微信头像: {}", avatarUrl);
            byte[] imageBytes = restTemplate.getForObject(avatarUrl, byte[].class);

            if (imageBytes == null || imageBytes.length == 0) {
                throw new BusinessException("头像下载失败：内容为空");
            }

            // 检查文件大小（微信头像通常不大，但最好还是检查一下）
            if (imageBytes.length > MAX_IMAGE_SIZE) {
                throw new BusinessException("头像文件大小超过5MB");
            }

            // 保存文件
            Files.write(filePath, imageBytes);
            log.info("微信头像保存成功: {}", filePath);

            // 返回相对路径（存储到数据库）
            String relativePath = "/" + uploadDir + "/" + datePath + "/" + newFilename;

            log.info("微信头像下载并保存成功，相对路径: {}", relativePath);
            return relativePath;

        } catch (IOException e) {
            log.error("保存微信头像失败: {}", e.getMessage(), e);
            throw new BusinessException("保存微信头像失败: " + e.getMessage());
        }
    }

    /**
     * 验证文件名安全性
     * 防止路径遍历攻击和危险字符
     *
     * @param filename 文件名
     */
    private void validateFilename(String filename) {
        // 检查路径遍历字符
        if (filename.contains("..") || filename.contains("/") || filename.contains("\\")) {
            log.warn("文件名包含非法字符（路径遍历）: {}", filename);
            throw new BusinessException("文件名包含非法字符");
        }

        // 检查特殊字符和控制字符
        if (filename.matches(".*[<>:\"|?*\\x00-\\x1F].*")) {
            log.warn("文件名包含非法特殊字符: {}", filename);
            throw new BusinessException("文件名包含非法字符");
        }

        // 检查文件名长度
        if (filename.length() > 255) {
            log.warn("文件名过长: {}", filename.length());
            throw new BusinessException("文件名长度不能超过255个字符");
        }
    }

    /**
     * 验证图片文件的真实性（通过文件头Magic Bytes）
     *
     * @param file 上传的文件
     * @return 是否是合法的图片文件
     * @throws IOException 读取文件失败
     */
    private boolean isValidImageFile(MultipartFile file) throws IOException {
        byte[] fileHeader = new byte[12];
        int bytesRead = file.getInputStream().read(fileHeader);

        if (bytesRead < 2) {
            return false;
        }

        // JPEG: FF D8 FF
        if (fileHeader[0] == (byte) 0xFF && fileHeader[1] == (byte) 0xD8 && fileHeader[2] == (byte) 0xFF) {
            return true;
        }

        // PNG: 89 50 4E 47 0D 0A 1A 0A
        if (fileHeader[0] == (byte) 0x89 && fileHeader[1] == (byte) 0x50 &&
            fileHeader[2] == (byte) 0x4E && fileHeader[3] == (byte) 0x47) {
            return true;
        }

        // GIF: 47 49 46 38 (GIF8)
        if (fileHeader[0] == (byte) 0x47 && fileHeader[1] == (byte) 0x49 &&
            fileHeader[2] == (byte) 0x46 && fileHeader[3] == (byte) 0x38) {
            return true;
        }

        // BMP: 42 4D (BM)
        if (fileHeader[0] == (byte) 0x42 && fileHeader[1] == (byte) 0x4D) {
            return true;
        }

        // WebP: 52 49 46 46 ... 57 45 42 50 (RIFF...WEBP)
        if (bytesRead >= 12 &&
            fileHeader[0] == (byte) 0x52 && fileHeader[1] == (byte) 0x49 &&
            fileHeader[2] == (byte) 0x46 && fileHeader[3] == (byte) 0x46 &&
            fileHeader[8] == (byte) 0x57 && fileHeader[9] == (byte) 0x45 &&
            fileHeader[10] == (byte) 0x42 && fileHeader[11] == (byte) 0x50) {
            return true;
        }

        log.warn("未识别的文件头: {}", Arrays.toString(Arrays.copyOf(fileHeader, Math.min(bytesRead, 12))));
        return false;
    }
}