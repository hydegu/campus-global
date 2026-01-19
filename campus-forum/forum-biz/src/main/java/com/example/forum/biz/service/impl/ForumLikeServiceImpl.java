package com.example.forum.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.ResourceNotFoundException;
import com.example.forum.api.dto.ForumLikeDTO;
import com.example.forum.api.entity.ForumLikeRecord;
import com.example.forum.api.vo.ForumLikeVO;
import com.example.forum.biz.mapper.ForumLikeRecordMapper;
import com.example.forum.biz.service.ForumLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 点赞记录服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ForumLikeServiceImpl extends ServiceImpl<ForumLikeRecordMapper, ForumLikeRecord>
        implements ForumLikeService {


    @Override
    public List<ForumLikeVO> getLikeRecords(Long userId, ForumLikeDTO forumLikeDTO) {
        LambdaQueryWrapper<ForumLikeRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumLikeRecord::getUserId, userId)
                .eq(ForumLikeRecord::getLikeType, forumLikeDTO.getLikeType());
        List<ForumLikeRecord> forumLikeRecords = baseMapper.selectList(queryWrapper);
        if (forumLikeRecords.isEmpty()) {
            throw new ResourceNotFoundException("未找到该用户的点赞记录");
        }
        
        // 使用BeanUtil将实体转换为VO
        return forumLikeRecords.stream()
                .map(record -> BeanUtil.copyProperties(record, ForumLikeVO.class))
                .collect(Collectors.toList());
    }
}