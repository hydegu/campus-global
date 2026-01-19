package com.example.forum.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.forum.api.entity.ForumActivityRegistration;
import com.example.forum.api.vo.UserRegisteredActivityVO;

import java.util.List;

/**
* @author 22417
* @description 针对表【forum_activity_registration(活动报名表)】的数据库操作Service
* @createDate 2025-12-09 17:36:39
*/
public interface ForumActivityRegistrationService extends IService<ForumActivityRegistration> {

    /**
     * 活动报名
     *
     * @param activityId 活动ID
     */
    void registerForActivity(Long userId,Long activityId);

    /**
     * 取消活动报名
     *
     * @param activityId 活动ID
   */
    void cancelRegistration(Long userId,Long activityId);
    /**
     * 查询用户报名的活动列表
     * @param userId 用户ID
     * @return 活动列表
     */
    List<UserRegisteredActivityVO> getRegisteredActivitiesByUserId(Long userId);
}