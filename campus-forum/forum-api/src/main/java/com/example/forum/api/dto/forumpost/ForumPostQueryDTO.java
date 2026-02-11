package com.example.forum.api.dto.forumpost;

import com.example.forum.api.dto.PageQuery;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ForumPostQueryDTO extends PageQuery {

    @Size(max = 100, message = "帖子标题最长100个字符")
    private String postTitle;

    @Size(max = 500, message = "帖子内容最长500个字符")
    private String postContent;

    @Size(max = 200, message = "标签最长200个字符")
    private String tags;

    private Long userId;

    @Size(max = 50, message = "用户名最长50个字符")
    private String userName;

    private LocalDateTime createdAt;

}
