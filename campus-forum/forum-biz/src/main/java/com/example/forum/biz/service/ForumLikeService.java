package com.example.forum.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.forum.api.dto.ForumLikeDTO;
import com.example.forum.api.entity.ForumLikeRecord;
import com.example.forum.api.vo.ForumLikeVO;

import java.util.List;

/**
 * 点赞记录服务接口
 */
public interface ForumLikeService extends IService<ForumLikeRecord> {

    List<ForumLikeVO> getLikeRecords(Long userId, ForumLikeDTO forumLikeDTO);
}