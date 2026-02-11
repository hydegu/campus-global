package com.example.forum.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.forum.api.dto.PageQuery;
import com.example.forum.api.dto.forumActivity.ForumActivityRegistrationApprovalDTO;
import com.example.forum.api.dto.forumActivity.ForumActivityRegistrationListDTO;
import com.example.forum.api.entity.ForumActivityRegistration;
import com.example.forum.api.vo.ForumActivityRegistrationQueryVO;
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
    /**
     * 查询用户报名的活动列表（分页）
     * @param activityId 活动ID
     * @param queryDTO 查询条件（分页参数等）
     * @return 活动列表
     */
    IPage<ForumActivityRegistrationQueryVO> getRegisteredActivitiesByActivityId(Long activityId, ForumActivityRegistrationListDTO queryDTO);

     /**
     * 审批报名活动
     * @param approvalDTO 审批报名活动DTO
     */
    void approveRegistration(ForumActivityRegistrationApprovalDTO approvalDTO);
}