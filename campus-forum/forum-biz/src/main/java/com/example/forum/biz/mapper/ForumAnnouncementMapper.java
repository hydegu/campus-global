package com.example.forum.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.api.entity.ForumAnnouncement;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 22417
* @description 针对表【forum_announcement(公告表)】的数据库操作Mapper
* @createDate 2025-12-09 17:36:39
* @Entity com.example.campusapp.entity.ForumAnnouncement
*/
@Mapper
public interface ForumAnnouncementMapper extends BaseMapper<ForumAnnouncement> {

}




