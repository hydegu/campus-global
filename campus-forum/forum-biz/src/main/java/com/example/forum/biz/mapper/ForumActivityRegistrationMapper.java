package com.example.forum.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.api.entity.ForumActivityRegistration;
import com.example.forum.api.vo.ForumActivityRegistrationQueryVO;
import com.example.forum.api.vo.UserRegisteredActivityVO;
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
     /**
     * 查询活动报名列表
     * @param page 分页信息
     * @param activityId 活动ID
     * @return 报名列表
     */
    IPage<ForumActivityRegistrationQueryVO> selectRegisteredActivitiesByActivityId(Page<ForumActivityRegistrationQueryVO> page, @Param("activityId") Long activityId);
}