package com.example.forum.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.forum.api.dto.forumActivity.ForumActivityAddDTO;
import com.example.forum.api.dto.forumActivity.ForumActivityListDTO;
import com.example.forum.api.entity.ForumActivity;
import com.example.forum.api.vo.ForumActivityDetailVO;
import com.example.forum.api.vo.ForumActivityQueryVO;

/**
* @author 22417
* @description 针对表【forum_activity(活动表)】的数据库操作Service
* @createDate 2025-12-09 17:36:39
*/
public interface ForumActivityService extends IService<ForumActivity> {

    /*
    * 活动列表
    * */
    IPage<ForumActivityQueryVO> getForumActivityList(ForumActivityListDTO queryDTO);

    /*
    * 活动详情
    * */
    ForumActivityDetailVO getForumActivityDetail(Long id);

    /**
    * 发布活动
    * */

    void createForumActivity(Long userId, ForumActivityAddDTO dto);

    /*
    * 查找活动
    * */
    ForumActivity findById(Long id);


    /*
    * 增加人数（带并发控制）
    * @return 是否成功增加（失败表示人数已满）
    * */
    boolean addParticipants(Long id);

    /*
    * 减少人数
    * */
    void reduceParticipants(Long id);

    /*
    * 点赞活动
    * */
    void likeActivity(Long userId,Long id);

    /*
    * 取消点赞活动
    * */
    void unLikeActivity(Long userId,Long id);
}