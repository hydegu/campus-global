package com.example.forum.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.forum.dto.forumpost.ForumPostListDTO;
import com.example.forum.dto.forumpost.ForumPostQueryDTO;
import com.example.forum.common.utils.PageResult;
import com.example.forum.vo.BrowseHistoryVO;
import com.example.forum.vo.ForumPostCommentTreeVO;
import com.example.forum.vo.ForumPostQueryVO;
import com.example.forum.vo.MyForumPostVO;
import com.example.forum.entity.ForumPost;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author 22417
* @description 针对表【forum_post(帖子表)】的数据库操作Service
* @createDate 2025-12-09 17:36:38
*/
public interface ForumPostService extends IService<ForumPost> {

    /*
    * 帖子列表热度
    * */
    IPage<ForumPostQueryVO> getForumPostHotList(ForumPostListDTO queryDTO);

    /*
    * 帖子列表时间
    * */
    IPage<ForumPostQueryVO> getForumPostTimeList(ForumPostListDTO queryDTO);

    /*
    * 帖子详情
    * */
    ForumPostCommentTreeVO getForumPostDetail(Long id);

    /**
     * 创建帖子
     *
     * @param post 用户ID 帖子对象
     */
    void createPost(Long userId,ForumPost post);

    /**
     * 删除帖子
     *
     * @param postId 帖子ID
    */
    void deletePost(Long postId,Long userId);

    /**
     * 点赞帖子
     *
     * @param postId 帖子ID
     */
    void likePost(Long userId,Long postId);

    /**
     * 取消点赞帖子
     *
     * @param postId 帖子ID
     */
    void unlikePost(Long userId,Long postId);

    /**
     * 上传文件
     */
    List<String> upload(MultipartFile[] files);
    /**
     * 查询当前用户发布的帖子
     *
     * @param queryDTO 查询参数
     * @return 帖子分页结果
     */
    PageResult<MyForumPostVO> getMyPosts(ForumPostQueryDTO queryDTO);

    /**
     * 查询当前用户的浏览历史
     *
     * @param queryDTO 查询参数
     * @return 浏览历史分页结果
     */
    PageResult<BrowseHistoryVO> getMyBrowseHistory(ForumPostQueryDTO queryDTO);

}
