package com.example.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.forum.vo.UserRegisteredActivityVO;
import com.example.forum.entity.ForumActivityRegistration;

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
     * @throws com.example.campusapp.commons.exception.ConflictException 当活动状态不允许报名时抛出
     * @throws com.example.campusapp.commons.exception.ResourceNotFoundException 当活动不存在时抛出
     */
    void registerForActivity(Long userId,Long activityId);

    /**
     * 取消活动报名
     *
     * @param activityId 活动ID
     * @throws com.example.campusapp.commons.exception.ConflictException 当活动状态不允许取消报名时抛出
     * @throws com.example.campusapp.commons.exception.ResourceNotFoundException 当活动或报名记录不存在时抛出
     */
    void cancelRegistration(Long userId,Long activityId);
    /**
     * 查询用户报名的活动列表
     * @param userId 用户ID
     * @return 活动列表
     */
    List<UserRegisteredActivityVO> getRegisteredActivitiesByUserId(Long userId);
}