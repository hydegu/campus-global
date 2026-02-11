package com.example.forum.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.dto.UserInfo;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.exception.DuplicateException;
import com.example.common.core.exception.ForbiddenException;
import com.example.common.core.exception.ResourceNotFoundException;
import com.example.common.core.util.Result;
import com.example.forum.api.dto.forumActivity.ForumActivityRegistrationApprovalDTO;
import com.example.forum.api.dto.forumActivity.ForumActivityRegistrationListDTO;
import com.example.forum.api.entity.ForumActivity;
import com.example.forum.api.entity.ForumActivityRegistration;
import com.example.forum.api.feign.RemoteUserService;
import com.example.forum.api.vo.ForumActivityRegistrationQueryVO;
import com.example.forum.api.vo.UserRegisteredActivityVO;
import com.example.forum.biz.mapper.ForumActivityRegistrationMapper;
import com.example.forum.biz.service.ForumActivityRegistrationService;
import com.example.forum.biz.service.ForumActivityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author 22417
* @description 针对表【forum_activity_registration(活动报名表)】的数据库操作Service实现
* @createDate 2025-12-09 17:36:39
*/
@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "userCache")
public class ForumActivityRegistrationServiceImpl extends ServiceImpl<ForumActivityRegistrationMapper, ForumActivityRegistration>
    implements ForumActivityRegistrationService {

    private final ForumActivityRegistrationMapper forumActivityRegistrationMapper;
    private final ForumActivityService forumActivityService;
    private final RemoteUserService remoteUserService;

    @Override
    public List<UserRegisteredActivityVO> getRegisteredActivitiesByUserId(Long userId) {
        return forumActivityRegistrationMapper.selectRegisteredActivitiesByUserId(userId);
    }

    @Override
    public IPage<ForumActivityRegistrationQueryVO> getRegisteredActivitiesByActivityId(Long activityId, ForumActivityRegistrationListDTO queryDTO) {
        log.info("查询活动报名列表 - activityId: {}, page: {}, size: {}", activityId, queryDTO.getPage(), queryDTO.getSize());

        Page<ForumActivityRegistrationQueryVO> page = new Page<>(queryDTO.getPage(), queryDTO.getSize());
        IPage<ForumActivityRegistrationQueryVO> pageVO = forumActivityRegistrationMapper.selectRegisteredActivitiesByActivityId(page, activityId);

        if (pageVO.getRecords() == null || pageVO.getRecords().isEmpty()) {
            log.info("活动[{}]暂无报名记录", activityId);
            return pageVO;
        }

        try {
            ForumActivity activity = forumActivityService.findById(activityId);

            List<Long> userIds = pageVO.getRecords().stream()
                    .map(ForumActivityRegistrationQueryVO::getUserId)
                    .filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toList());

            log.info("批量查询用户信息 - userIds: {}", userIds);
            Map<Long, UserInfo> userInfoMap = batchGetUserInfo(userIds);
            log.info("批量查询用户信息完成 - 成功: {}/{}", userInfoMap.size(), userIds.size());

            pageVO.setRecords(pageVO.getRecords().stream().map(registration -> {
                ForumActivityRegistrationQueryVO vo = new ForumActivityRegistrationQueryVO();
                vo.setUserId(registration.getUserId());
                vo.setActivityTitle(activity.getActivityTitle());
                vo.setAuditResult(registration.getAuditResult());
                vo.setCreateAt(registration.getCreateAt());
                vo.setCurrentParticipants(activity.getCurrentParticipants());
                vo.setMaxParticipants(activity.getMaxParticipants());

                UserInfo userInfo = userInfoMap.get(registration.getUserId());
                if (userInfo != null) {
                    vo.setRealName(userInfo.getRoleList().get(0).getRoleName());
                    vo.setAvatar(userInfo.getAvatar());
                } else {
                    log.warn("用户[{}]信息未找到", registration.getUserId());
                }

                return vo;
            }).collect(Collectors.toList()));

            log.info("查询活动报名列表成功 - activityId: {}, 总数: {}", activityId, pageVO.getTotal());
        } catch (Exception e) {
            log.error("查询活动报名列表失败 - activityId: {}, 错误: {}", activityId, e.getMessage(), e);
            throw new BusinessException("QUERY_FAILED", "查询报名列表失败");
        }

        return pageVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveRegistration(ForumActivityRegistrationApprovalDTO approvalDTO) {
        ForumActivityRegistration registration = getById(approvalDTO.getRegistrationId());

        if(registration.getAuditResult() != 0){
            log.warn("报名记录已审批 - registrationId: {}", registration.getId());
            throw new BusinessException("DUPLICATE_OPERATION", "报名记录已审批，不能重复操作");
        }
        log.info("开始审批报名  registrationId: {}, approvalStatus: {}",
                 approvalDTO.getRegistrationId(), approvalDTO.getApprovalStatus());

        Integer approvalStatus = approvalDTO.getApprovalStatus();
        if (approvalStatus < 0 || approvalStatus > 2) {
            throw new BusinessException("INVALID_PARAM", "无效的审批状态");
        }

        if (registration == null) {
            log.warn("报名记录不存在 - registrationId: {}", approvalDTO.getRegistrationId());
            throw new ResourceNotFoundException("报名记录不存在");
        }
        Long activityId = registration.getActivityId();
        if (registration.getStatus() != 1) {
            log.warn("报名记录状态不允许审批 - registrationId: {}, currentStatus: {}", 
                     registration.getId(), registration.getStatus());
            throw new BusinessException("INVALID_STATUS", "该报名记录状态不允许审批");
        }

        if (approvalStatus == 0) {
            log.warn("审批状态不能设置为待审核 - registrationId: {}", registration.getId());
            throw new BusinessException("INVALID_OPERATION", "审批状态不能设置为待审核");
        }

        try {
            if (approvalStatus == 1) {
                log.info("审批通过 - registrationId: {}, userId: {}", registration.getId(), registration.getUserId());
                boolean success = forumActivityService.addParticipants(activityId);
                if (!success) {
                    log.warn("活动人数已满 - activityId: {}", activityId);
                    throw new BusinessException("ACTIVITY_FULL", "活动人数已满，无法通过审批");
                }
                registration.setStatus(1);
                log.info("审批通过成功 - registrationId: {}, 已增加活动人数", registration.getId());
            } else if (approvalStatus == 2) {
                log.info("审批拒绝 - registrationId: {}, userId: {}", registration.getId(), registration.getUserId());
                forumActivityService.reduceParticipants(activityId);
                registration.setStatus(2);
                log.info("审批拒绝成功 - registrationId: {}, 已减少活动人数", registration.getId());
            }

            registration.setUpdateAt(java.time.LocalDateTime.now());
            updateById(registration);

            log.info("审批报名完成 - activityId: {}, registrationId: {}, approvalStatus: {}", 
                     activityId, registration.getId(), approvalStatus);
        } catch (BusinessException e) {
            log.error("审批报名失败 - registrationId: {}, 错误: {}", 
                      registration.getId(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("审批报名异常 - registrationId: {}, 异常: {}", 
                      registration.getId(), e.getMessage(), e);
            throw new BusinessException("APPROVAL_FAILED", "审批失败，请稍后重试");
        }
    }

    private Map<Long, UserInfo> batchGetUserInfo(List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Long, UserInfo> userInfoMap = new HashMap<>();
        for (Long userId : userIds) {
            try {
                UserInfo userInfo = getUserInfo(userId);
                if (userInfo != null) {
                    userInfoMap.put(userId, userInfo);
                }
            } catch (Exception e) {
                log.error("获取用户[{}]信息失败: {}", userId, e.getMessage());
            }
        }
        return userInfoMap;
    }

    @Cacheable(key = "#userId", unless = "#result == null")
    public UserInfo getUserInfo(Long userId) {
        try {
            Result<UserInfo> result = remoteUserService.getUserInfoById(userId);
            if (result.getCode() == 1 && result.getData() != null) {
                
                return result.getData();
            }
            log.warn("获取用户[{}]信息失败 - code: {}, msg: {}", userId, result.getCode(), result.getMsg());
        } catch (Exception e) {
            log.error("获取用户[{}]信息异常: {}", userId, e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerForActivity(Long userId, Long activityId) {
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
            throw new BusinessException("FORBIDDEN", "活动未发布，无法报名");
        }
        if(activity.getStatus() == 3) {
            throw new BusinessException("FORBIDDEN", "活动已取消，无法报名");
        }

        // 检查报名时间
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        if(activity.getRegistrationStartTime() != null && now.isBefore(activity.getRegistrationStartTime())) {
            throw new BusinessException("FORBIDDEN", "报名尚未开始");
        }
        if(activity.getRegistrationEndTime() != null && now.isAfter(activity.getRegistrationEndTime())) {
            throw new BusinessException("FORBIDDEN", "活动已结束，无法报名");
        }

        // 检查活动是否已结束
        if(activity.getActivityTime() != null && now.isAfter(activity.getActivityTime())) {
            throw new BusinessException("FORBIDDEN", "报名已截止");
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
            throw new BusinessException("FORBIDDEN", "报名失败，活动人数已满");
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


