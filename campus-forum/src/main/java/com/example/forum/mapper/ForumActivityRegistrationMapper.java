package com.example.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.vo.UserRegisteredActivityVO;
import com.example.forum.entity.ForumActivityRegistration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 22417
* @description 针对表【forum_activity_registration(活动报名表)】的数据库操作Mapper
* @createDate 2025-12-09 17:36:39
* @Entity com.example.campusapp.entity.ForumActivityRegistration
*/
@Mapper
public interface ForumActivityRegistrationMapper extends BaseMapper<ForumActivityRegistration> {

    /**
     * 查询用户报名的活动列表
     * @param userId 用户ID
     * @return 活动列表
     */
    List<UserRegisteredActivityVO> selectRegisteredActivitiesByUserId(@Param("userId") Long userId);
}