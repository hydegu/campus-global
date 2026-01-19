package com.example.forum.api.dto.forumpost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
public class ForumPostAddDTO {

    /**
     * 帖子标题
     */
    @Schema(description = "帖子标题", example = "校园生活的美好时光")
    @NotBlank(message = "帖子标题不能为空")
    @Size(min = 1, max = 100, message = "帖子标题长度必须在1-100个字符之间")
    private String postTitle;

    /**
     * 帖子内容
     */
    @Schema(description = "帖子内容", example = "今天在校园里遇到了很多有趣的事情...")
    @NotBlank(message = "帖子内容不能为空")
    @Size(min = 1, max = 5000, message = "帖子内容长度必须在1-5000个字符之间")
    private String postContent;

    /**
     * 帖子图片
     */
    @Schema(description = "帖子图片(JSON数组)", example = "[\"image1.jpg\", \"image2.jpg\"]")
    @Size(max = 9, message = "最多只能上传9张图片")
    private List<String> imageUrls;

    public void setImageUrls(Object imageUrlsObj) {
        if (imageUrlsObj instanceof List) {
            // 如果是List<String>类型，直接处理，避免双重序列化
            @SuppressWarnings("unchecked")
            List<String> stringList = (List<String>) imageUrlsObj;
            this.imageUrls = stringList.stream()
                    .filter(str -> str != null && !str.trim().isEmpty())
                    .collect(Collectors.toList());
        } else if (imageUrlsObj instanceof String) {
            // 如果是字符串，尝试解析为JSON数组
            String jsonString = (String) imageUrlsObj;
            if (jsonString.trim().startsWith("[") && jsonString.trim().endsWith("]")) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    @SuppressWarnings("unchecked")
                    List<String> parsedList = mapper.readValue(jsonString, List.class);
                    this.imageUrls = parsedList.stream()
                            .filter(str -> str != null && !str.trim().isEmpty())
                            .collect(Collectors.toList());
                } catch (JsonProcessingException e) {
                    log.warn("解析图片URL JSON数组失败: {}", e.getMessage());
                    // 如果解析失败，尝试将整个字符串作为单个URL处理
                    if (!jsonString.trim().isEmpty()) {
                        this.imageUrls = List.of(jsonString);
                    } else {
                        this.imageUrls = null;
                    }
                }
            } else {
                // 如果不是数组格式，可能是单个URL
                if (!jsonString.trim().isEmpty()) {
                    this.imageUrls = List.of(jsonString);
                } else {
                    this.imageUrls = null;
                }
            }
        } else if (imageUrlsObj == null) {
            this.imageUrls = null;
        } else {
            // 其他类型，转换为字符串
            String str = imageUrlsObj.toString();
            if (!str.trim().isEmpty()) {
                this.imageUrls = List.of(str);
            } else {
                this.imageUrls = null;
            }
        }
    }
}