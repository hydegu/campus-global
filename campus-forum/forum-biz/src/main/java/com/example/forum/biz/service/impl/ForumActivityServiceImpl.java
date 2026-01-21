package com.example.forum.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.forum.api.feign.RemoteSchoolService;
import com.example.admin.api.vo.SysSchoolVO;
import com.example.common.core.exception.DuplicateException;
import com.example.common.core.exception.ForbiddenException;
import com.example.common.core.exception.ResourceNotFoundException;
import com.example.common.core.util.Result;
import com.example.forum.biz.utils.PageUtil;
import com.example.forum.api.dto.ForumLikeDTO;
import com.example.forum.api.dto.forumActivity.ForumActivityAddDTO;
import com.example.forum.api.dto.forumActivity.ForumActivityListDTO;
import com.example.forum.api.entity.ForumActivity;
import com.example.forum.api.entity.ForumLikeRecord;
import com.example.forum.api.vo.ForumActivityDetailVO;
import com.example.forum.api.vo.ForumActivityQueryVO;
import com.example.forum.biz.mapper.ForumActivityMapper;
import com.example.forum.biz.mapper.ForumLikeRecordMapper;
import com.example.forum.biz.service.ForumActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author 22417
* @description 针对表【forum_activity(活动表)】的数据库操作Service实现
* @createDate 2025-12-09 17:36:39
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ForumActivityServiceImpl extends ServiceImpl<ForumActivityMapper, ForumActivity>
    implements ForumActivityService {

    private  final ForumLikeRecordMapper forumLikeRecordMapper;
    private  final RemoteSchoolService remoteSchoolService;

    @Override
    public IPage<ForumActivityQueryVO> getForumActivityList(ForumActivityListDTO queryDTO) {
        Page<ForumActivityQueryVO> iPage = PageUtil.createPage(
                queryDTO.getPage(),
                queryDTO.getSize()
        );
        return baseMapper.selectForumActivityPage(iPage);
    }

    @Override
    public ForumActivityDetailVO getForumActivityDetail(Long id) {
        ForumActivityDetailVO vo = baseMapper.selectDetailById(id);

        if(vo == null){
            throw new ResourceNotFoundException("活动不存在");
        }

        log.info("活动详情查询结果 - ID: {}, schoolId: {}, schoolName: {}", 
                 vo.getId(), vo.getSchoolId(), vo.getSchoolName());

        if(vo.getSchoolId() != null) {
            try {
                log.info("开始调用学校服务 - schoolId: {}", vo.getSchoolId());
                Result<SysSchoolVO> result = remoteSchoolService.getSchoolById(vo.getSchoolId());
                log.info("学校服务返回结果 - code: {}, data: {}", result.getCode(), result.getData());
                
                if(result.getCode() == 1 && result.getData() != null) {
                    String orgName = result.getData().getOrgName();
                    log.info("获取到学校名称 - orgName: {}", orgName);
                    vo.setSchoolName(orgName);
                } else {
                    log.warn("学校服务返回失败 - code: {}, data: {}", result.getCode(), result.getData());
                }
            } catch (Exception e) {
                log.error("调用学校服务异常 - schoolId: {}", vo.getSchoolId(), e);
                vo.setSchoolName("");
            }
        } else {
            log.warn("活动没有关联学校 - ID: {}", vo.getId());
        }

        return vo;
    }

    @Override
    public void createForumActivity(Long userId, ForumActivityAddDTO dto) {
        ForumActivity activity = BeanUtil.copyProperties(dto, ForumActivity.class);
        LambdaQueryWrapper<ForumActivity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumActivity::getActivityTitle, activity.getActivityTitle());
        queryWrapper.eq(ForumActivity::getActivityContent, activity.getActivityContent());
        ForumActivity existActivity = baseMapper.selectOne(queryWrapper);
        if(existActivity != null){
            throw new DuplicateException("活动已存在");
        }
        Integer maxParticipants = activity.getMaxParticipants();
        if (maxParticipants != null && maxParticipants <= 0) {
            throw new IllegalArgumentException("最大报名人数必须大于0");
        }

        if(activity.getRegistrationStartTime().isAfter(activity.getRegistrationEndTime())){
            throw new IllegalArgumentException("报名开始时间必须在报名结束时间之前");
        }
        if(activity.getActivityTime().isBefore(activity.getRegistrationEndTime())){
            throw new IllegalArgumentException("活动时间必须在报名结束时间之后");
        }

        List<String> imagesList = dto.getImages();
        if (imagesList != null && !imagesList.isEmpty()) {
            // 将List转换为JSON数组字符串（如["image1.jpg","image2.jpg"]）
            String imagesJson = JSONUtil.toJsonStr(imagesList);
            activity.setImages(imagesJson);
        } else {
            // 可选：设置空JSON数组或null
            activity.setImages("[]");
        }
        save(activity);
    }

    @Override
    public ForumActivity findById(Long id) {
        ForumActivity forumActivity = getById(id);
        return forumActivity;
    }


    @Override
    public boolean addParticipants(Long id) {
        LambdaUpdateWrapper<ForumActivity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ForumActivity::getId, id);
        // 添加WHERE条件确保不会超过最大人数（原子操作）
        updateWrapper.setSql("current_participants = current_participants + 1");
        updateWrapper.apply("current_participants < max_participants");
        return update(updateWrapper);
    }

    @Override
    public void reduceParticipants(Long id) {
        LambdaUpdateWrapper<ForumActivity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ForumActivity::getId,id);
        updateWrapper.setSql("current_participants = current_participants - 1");
        update(updateWrapper);
    }

    @Override
    public void likeActivity(Long userId, Long id) {
        if(userId == null){
            throw new ForbiddenException("用户未登录");
        }
        ForumActivity activity = getById(id);
        if(activity == null){
            throw new ResourceNotFoundException("活动不存在");
        }
        if(activity.getIsVisible() == 0){
            throw new ForbiddenException("活动已隐藏");
        }
        ForumLikeDTO likeDTO = new ForumLikeDTO();
        likeDTO.setUserId(userId);
        likeDTO.setLikeId(id);
        likeDTO.setLikeType(1);
        ForumLikeRecord forumLikeRecord = forumLikeRecordMapper.getLikeRecord(likeDTO);
        if(forumLikeRecord != null){
            throw new DuplicateException("用户已点赞");
        }
        activity.setLikeCount((activity.getLikeCount() != null ? activity.getLikeCount() : 0) + 1);
        activity.setUpdatedAt(LocalDateTime.now());
        updateById(activity);
        forumLikeRecordMapper.addLikeRecord(likeDTO);
    }

    @Override
    public void unLikeActivity(Long userId, Long id) {
        if(userId == null){
            throw new ForbiddenException("用户未登录");
        }
        ForumActivity activity = getById(id);
        if(activity == null){
            throw new ResourceNotFoundException("活动不存在");
        }
        if(activity.getIsVisible() == 0){
            throw new ForbiddenException("活动已隐藏");
        }
        ForumLikeDTO likeDTO = new ForumLikeDTO();
        likeDTO.setUserId(userId);
        likeDTO.setLikeId(id);
        likeDTO.setLikeType(1);
        ForumLikeRecord forumLikeRecord = forumLikeRecordMapper.getLikeRecord(likeDTO);
        if(forumLikeRecord == null){
            throw new ResourceNotFoundException("用户未点赞");
        }
        activity.setLikeCount((activity.getLikeCount() != null ? activity.getLikeCount() : 0) - 1);
        activity.setUpdatedAt(LocalDateTime.now());
        updateById(activity);
        forumLikeRecordMapper.subLikeRecord(likeDTO);
    }
}




