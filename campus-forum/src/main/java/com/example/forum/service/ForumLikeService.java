package com.example.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.forum.dto.ForumLikeDTO;
import com.example.forum.vo.ForumLikeVO;
import com.example.forum.entity.ForumLikeRecord;

import java.util.List;

/**
 * 点赞记录服务接口
 */
public interface ForumLikeService extends IService<ForumLikeRecord> {

    List<ForumLikeVO> getLikeRecords(Long userId, ForumLikeDTO forumLikeDTO);
}