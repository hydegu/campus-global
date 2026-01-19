package com.example.forum.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公告VO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForumAnnouncementVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String image;

    private Integer isDisplay;

    private Long schoolId;

    /**
     * 学校名称（关联查询）
     */
    private String schoolName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
