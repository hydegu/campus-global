package com.example.forum.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.exception.DuplicateException;
import com.example.common.core.exception.ForbiddenException;
import com.example.common.core.exception.ResourceNotFoundException;
import com.example.forum.api.entity.ForumActivity;
import com.example.forum.api.entity.ForumActivityRegistration;
import com.example.forum.api.vo.UserRegisteredActivityVO;
import com.example.forum.biz.mapper.ForumActivityRegistrationMapper;
import com.example.forum.biz.service.ForumActivityRegistrationService;
import com.example.forum.biz.service.ForumActivityService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 22417
* @description 针对表【forum_activity_registration(活动报名表)】的数据库操作Service实现
* @createDate 2025-12-09 17:36:39
*/
@Service
public class ForumActivityRegistrationServiceImpl extends ServiceImpl<ForumActivityRegistrationMapper, ForumActivityRegistration>
    implements ForumActivityRegistrationService {

    private final ForumActivityRegistrationMapper forumActivityRegistrationMapper;
    private final ForumActivityService forumActivityService;


    public ForumActivityRegistrationServiceImpl(ForumActivityRegistrationMapper forumActivityRegistrationMapper, ForumActivityService forumActivityService) {
        this.forumActivityRegistrationMapper = forumActivityRegistrationMapper;
        this.forumActivityService = forumActivityService;
    }

    @Override
    public List<UserRegisteredActivityVO> getRegisteredActivitiesByUserId(Long userId) {
        return forumActivityRegistrationMapper.selectRegisteredActivitiesByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerForActivity(Long userId, Long activityId) {
        if(userId == null){
            throw new ForbiddenException("用户未登录");
        }
        ForumActivity activity = forumActivityService.findById(activityId);
        if(activity == null){
            throw new ResourceNotFoundException("活动不存在");
        }

        // 检查活动可见性（1-显示 0-隐藏）
        if(activity.getIsVisible() != null && activity.getIsVisible() == 0) {
            throw new ForbiddenException("活动已隐藏，无法报名");
        }

        // 检查活动状态（0-草稿 1-待审核 2-已发布 3-已取消）
        if(activity.getStatus() == null || activity.getStatus() != 2) {
            throw new BusinessException("活动未发布，无法报名", "FORBIDDEN");
        }
        if(activity.getStatus() == 3) {
            throw new BusinessException("活动已取消，无法报名", "FORBIDDEN");
        }

        // 检查报名时间
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        if(activity.getRegistrationStartTime() != null && now.isBefore(activity.getRegistrationStartTime())) {
            throw new BusinessException("报名尚未开始", "FORBIDDEN");
        }
        if(activity.getRegistrationEndTime() != null && now.isAfter(activity.getRegistrationEndTime())) {
            throw new BusinessException("活动已结束，无法报名", "FORBIDDEN");
        }

        // 检查活动是否已结束
        if(activity.getActivityTime() != null && now.isAfter(activity.getActivityTime())) {
            throw new BusinessException("报名已截止", "FORBIDDEN");
        }

        // 检查是否已报名
        LambdaQueryWrapper<ForumActivityRegistration> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumActivityRegistration::getUserId,userId);
        queryWrapper.eq(ForumActivityRegistration::getActivityId,activityId);
        ForumActivityRegistration registration = getOne(queryWrapper);
        if(registration != null){
            throw new DuplicateException("您已报名该活动");
        }

        // 先创建报名记录，再增加人数（原子操作，避免竞态条件）
        ForumActivityRegistration forumActivityRegistration = new ForumActivityRegistration();
        forumActivityRegistration.setUserId(userId);
        forumActivityRegistration.setActivityId(activityId);
        forumActivityRegistration.setStatus(1);
        save(forumActivityRegistration);

        // 使用SQL WHERE条件确保不超过最大人数
        boolean success = forumActivityService.addParticipants(activityId);
        if (!success) {
            // 如果增加失败（人数已满），抛出异常触发事务回滚
            throw new BusinessException("报名失败，活动人数已满", "FORBIDDEN");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelRegistration(Long userId, Long activityId) {
        if(userId == null || activityId == null){
            throw new ForbiddenException("用户未登录");
        }
        ForumActivity activity = forumActivityService.findById(activityId);
        if(activity == null){
            throw new ResourceNotFoundException("活动不存在");
        }
        LambdaQueryWrapper<ForumActivityRegistration> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumActivityRegistration::getUserId,userId);
        queryWrapper.eq(ForumActivityRegistration::getActivityId,activityId);
        ForumActivityRegistration registration = getOne(queryWrapper);
        if(registration == null){
            throw new ResourceNotFoundException("您未报名该活动");
        }
        forumActivityService.reduceParticipants(activityId);
        baseMapper.delete(queryWrapper);
    }



}


