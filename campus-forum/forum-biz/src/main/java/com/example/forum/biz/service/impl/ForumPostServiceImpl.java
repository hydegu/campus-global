package com.example.forum.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.dto.UserInfo;
import com.example.admin.api.feign.RemoteUserService;
import com.example.common.core.exception.ForbiddenException;
import com.example.common.core.exception.ResourceNotFoundException;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.common.security.util.SecurityUtils;
import com.example.forum.biz.utils.PageUtil;
import com.example.forum.api.dto.ForumLikeDTO;
import com.example.forum.api.dto.forumpost.ForumPostListDTO;
import com.example.forum.api.dto.forumpost.ForumPostQueryDTO;
import com.example.forum.api.entity.BrowsingHistory;
import com.example.forum.api.entity.ForumLikeRecord;
import com.example.forum.api.entity.ForumPost;
import com.example.forum.api.vo.BrowseHistoryVO;
import com.example.forum.api.vo.ForumPostCommentTreeVO;
import com.example.forum.api.vo.ForumPostQueryVO;
import com.example.forum.api.vo.MyForumPostVO;
import com.example.forum.biz.mapper.BrowsingHistoryMapper;
import com.example.forum.biz.mapper.ForumLikeRecordMapper;
import com.example.forum.biz.mapper.ForumPostMapper;
import com.example.forum.biz.service.ForumPostCommentService;
import com.example.forum.biz.service.ForumPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 22417
 * @description 针对表【forum_post(帖子表)】的数据库操作Service实现
 * @createDate 2025-12-09 17:36:38
 */
@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "forumPostCache")
public class ForumPostServiceImpl extends ServiceImpl<ForumPostMapper, ForumPost>
    implements ForumPostService {

    private final ForumPostMapper forumPostMapper;
    private final ForumLikeRecordMapper forumLikeRecordMapper;
    private final ForumPostCommentService forumPostCommentService;
    private final BrowsingHistoryMapper browsingHistoryMapper;
    private final RemoteUserService remoteUserService;


    @Override
    @Cacheable(value = "hotPostsCache", key = "'hot:' + #queryDTO.page + ':' + #queryDTO.size", unless = "#result == null")
    public IPage<ForumPostQueryVO> getForumPostHotList(ForumPostListDTO queryDTO) {
        Page<ForumPostQueryVO> forumPostPage = PageUtil.createPage(
                queryDTO.getPage(),
                queryDTO.getSize());
        
        IPage<ForumPostQueryVO> pageResult = forumPostMapper.selectForumPostHotPage(forumPostPage);
        
        fillUserInfo(pageResult.getRecords());
        
        return pageResult;
    }

    @Override
    @Cacheable(value = "hotPostsCache", key = "'time:' + #queryDTO.page + ':' + #queryDTO.size", unless = "#result == null")
    public IPage<ForumPostQueryVO> getForumPostTimeList(ForumPostListDTO queryDTO) {
        Page<ForumPostQueryVO> forumPostPage = PageUtil.createPage(
                queryDTO.getPage(),
                queryDTO.getSize());
        
        IPage<ForumPostQueryVO> pageResult = forumPostMapper.selectForumPostTimePage(forumPostPage);
        
        fillUserInfo(pageResult.getRecords());
        
        return pageResult;
    }

    private void fillUserInfo(List<ForumPostQueryVO> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        
        List<Long> userIds = records.stream()
                .map(ForumPostQueryVO::getUserId)
                .distinct()
                .collect(Collectors.toList());
        
        Map<Long, UserInfo> userInfoMap = batchGetUserInfo(userIds);
        
        records.forEach(vo -> {
            UserInfo userInfo = userInfoMap.get(vo.getUserId());
            if (userInfo != null) {
                vo.setUsername(userInfo.getUsername());
                vo.setAvatarUrl(userInfo.getAvatar());
            } else {
                vo.setUsername("匿名用户");
                vo.setAvatarUrl("");
            }
        });
    }

    private Map<Long, UserInfo> batchGetUserInfo(List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return Collections.emptyMap();
        }
        
        Map<Long, UserInfo> userInfoMap = new HashMap<>();
        
        for (Long userId : userIds) {
            try {
                Result<UserInfo> result = remoteUserService.getUserInfoById(userId);
                if (result.getCode() == 0 && result.getData() != null) {
                    UserInfo userInfo = result.getData();
                    userInfoMap.put(userId, userInfo);
                }
            } catch (Exception e) {
                log.error("获取用户[{}]信息失败: {}", userId, e.getMessage());
            }
        }
        
        return userInfoMap;
    }

    @Override
    public ForumPostCommentTreeVO getForumPostDetail(Long id) {
        if(id == null){
            throw new IllegalArgumentException("帖子ID不能为空");
        }
        
        ForumPostQueryVO forumPost = forumPostMapper.selectPostDetailById(id);
        if(forumPost == null){
            throw new IllegalArgumentException("帖子不存在");
        }

        fillUserInfo(Collections.singletonList(forumPost));

        incrementViewCount(id);

        return convertVoToVO(forumPost);
    }

    /**
     * 增加帖子浏览量
     * @param postId 帖子ID
     */
    private void incrementViewCount(Long postId) {
        try {
            ForumPost post = getById(postId);
            if (post != null && post.getDeleteAt() == null) {
                post.setViewCount((post.getViewCount() != null ? post.getViewCount() : 0) + 1);
                post.setUpdateAt(LocalDateTime.now());
                updateById(post);
                log.debug("帖子[{}]浏览量+1，当前浏览量: {}", postId, post.getViewCount());
            }
        } catch (Exception e) {
            // 浏览量更新失败不影响主流程，只记录日志
            log.warn("更新帖子[{}]浏览量失败: {}", postId, e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "hotPostsCache", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void createPost(Long userId ,ForumPost post) {
        // 1. 获取当前用户ID
        if (userId == null) {
            throw new ForbiddenException("用户未登录");
        }

        // 2. 设置帖子属性
        post.setUserId(userId);

        // 3. 保存帖子
        save(post);
        log.info("用户[{}]创建帖子成功，帖子ID: {}", userId, post.getId());
    }


    @Override
    @CacheEvict(value = "hotPostsCache", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void deletePost(Long postId, Long userId) {
        // 1. 获取当前用户ID
        if (userId == null) {
            throw new ForbiddenException("用户未登录");
        }

        // 2. 检查帖子是否存在
        ForumPost existingPost = getById(postId);
        if (existingPost == null) {
            throw new ResourceNotFoundException("帖子不存在");
        }

        // 3. 检查用户是否有权限删除该帖子
        if (!userId.equals(existingPost.getUserId())) {
            throw new ForbiddenException("无权限删除该帖子");
        }

        // 4. 软删除帖子
        existingPost.setDeleteAt(LocalDateTime.now());
        updateById(existingPost);

        // 5. 级联删除该帖子的所有评论（在事务内执行，确保原子性）
        forumPostCommentService.deleteCommentByPostId(userId, postId);

        log.info("用户[{}]删除帖子成功，帖子ID: {}，同时删除了该帖子的所有评论", userId, postId);
    }

    @Override
    @CacheEvict(value = "hotPostsCache", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void likePost(Long userId,Long postId) {
        // 1. 检查帖子是否存在
        ForumPost post = getById(postId);
        if (post == null) {
            throw new ResourceNotFoundException("帖子不存在");
        }
        ForumLikeDTO likeDTO = new ForumLikeDTO();
        likeDTO.setUserId(userId);
        likeDTO.setLikeId(postId);
        likeDTO.setLikeType(2);
        ForumLikeRecord forumLikeRecord = forumLikeRecordMapper.getLikeRecord(likeDTO);
        if(forumLikeRecord != null){
            throw new ForbiddenException("用户已点过赞");
        }
        // 2. 增加点赞数
        post.setLikeCount((post.getLikeCount() != null ? post.getLikeCount() : 0) + 1);
        updateById(post);
        forumLikeRecordMapper.addLikeRecord(likeDTO);
        log.info("帖子[{}]点赞成功，当前点赞数: {}", postId, post.getLikeCount());
    }

    @Override
    @CacheEvict(value = "hotPostsCache", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void unlikePost(Long userId,Long postId) {
        // 1. 检查帖子是否存在
        ForumPost post = getById(postId);
        if (post == null) {
            throw new ResourceNotFoundException("帖子不存在");
        }
        ForumLikeDTO likeDTO = new ForumLikeDTO();
        likeDTO.setUserId(userId);
        likeDTO.setLikeId(postId);
        likeDTO.setLikeType(2);
        ForumLikeRecord forumLikeRecord = forumLikeRecordMapper.getLikeRecord(likeDTO);
        if(forumLikeRecord == null){
            throw new ForbiddenException("用户没有点过赞");
        }
        // 2. 减少点赞数（不能小于0）
        int currentLikeCount = post.getLikeCount() != null ? post.getLikeCount() : 0;
        if (currentLikeCount > 0) {
            post.setLikeCount(currentLikeCount - 1);
            post.setUpdateAt(LocalDateTime.now());
            updateById(post);
            forumLikeRecordMapper.subLikeRecord(likeDTO);
        }

        log.info("帖子[{}]取消点赞成功，当前点赞数: {}", postId, post.getLikeCount());
    }

    @Override
    public List<String> upload(MultipartFile[] files) {
        List<String> urls = new ArrayList<>();
        if(files != null && files.length > 0){
            for (MultipartFile file : files) {
                try {
                    // 上传文件
//                    String url = fileUploadUtil.uploadImage(file);
                    String url = "https://example.com/upload/";
                    urls.add(url);
                } catch (Exception e) {
                    log.error("上传文件失败：{}", e.getMessage());
                }
            }
        }
        return urls;
    }

    // VO转化
    private ForumPostCommentTreeVO convertVoToVO(ForumPostQueryVO dto) {
        ForumPostCommentTreeVO vo = new ForumPostCommentTreeVO();

        // 复制基本字段
        vo.setId(dto.getId());
        vo.setUserId(dto.getUserId());
        vo.setUsername(dto.getUsername());
        vo.setAvatarUrl(dto.getAvatarUrl());
        vo.setPostTitle(dto.getPostTitle());
        vo.setPostContent(dto.getPostContent());
        vo.setViewCount(dto.getViewCount());
        vo.setLikeCount(dto.getLikeCount());
        vo.setShareCount(dto.getShareCount());
        vo.setFavoriteCount(dto.getFavoriteCount());
        vo.setCommentCount(dto.getCommentCount());
        vo.setCreateAt(dto.getCreateAt());


        // 处理imageUrls（List<String>转String数组）
        if (dto.getImageUrls() != null && !dto.getImageUrls().isEmpty()) {
            vo.setImageUrls(dto.getImageUrls());
        } else {
            vo.setImageUrls(List.of());
        }
        return vo;
    }

    @Override
    public PageResult<MyForumPostVO> getMyPosts(ForumPostQueryDTO queryDTO) {
        // 从请求头中获取当前用户ID
        Long currentUserId = SecurityUtils.getCurrentUserId();

        // 构建分页对象
        IPage<ForumPost> page = PageUtil.createPage(queryDTO.getPage(), queryDTO.getSize());

        // 构建查询条件
        LambdaQueryWrapper<ForumPost> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumPost::getUserId, currentUserId)
                .isNull(ForumPost::getDeleteAt)  // 只查询未删除的帖子
                .orderByDesc(ForumPost::getCreateAt); // 按创建时间倒序
        // 执行分页查询
        IPage<ForumPost> resultPage = page(page, queryWrapper);

        // 转换为VO对象
        PageResult<MyForumPostVO> pageResult = new PageResult<>();
        pageResult.setCurrent(resultPage.getCurrent());
        pageResult.setPageSize(resultPage.getSize());
        pageResult.setTotal(resultPage.getTotal());
        pageResult.setPages(resultPage.getPages());

        // 转换数据列表
        pageResult.setList(resultPage.getRecords().stream().map(this::convertToUserPostVO).collect(Collectors.toList()));

        return pageResult;
    }

    @Override
    public PageResult<BrowseHistoryVO> getMyBrowseHistory(ForumPostQueryDTO queryDTO) {
        // 从请求头中获取当前用户ID
        Long currentUserId = SecurityUtils.getCurrentUserId();

        // 构建分页对象
        IPage<BrowsingHistory> page = PageUtil.createPage(queryDTO.getPage(), queryDTO.getSize());

        // 构建查询条件 - 查询用户浏览历史，只查询论坛帖子(contentType=1)
        LambdaQueryWrapper<BrowsingHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BrowsingHistory::getUserId, currentUserId)
                .eq(BrowsingHistory::getContentType, 1) // 只查询论坛帖子
                .orderByDesc(BrowsingHistory::getId); // 按浏览记录ID倒序

        // 执行分页查询
        IPage<BrowsingHistory> resultPage = browsingHistoryMapper.selectPage(page, queryWrapper);

        // 转换为VO对象
        PageResult<BrowseHistoryVO> pageResult = new PageResult<>();
        pageResult.setCurrent(resultPage.getCurrent());
        pageResult.setPageSize(resultPage.getSize());
        pageResult.setTotal(resultPage.getTotal());
        pageResult.setPages(resultPage.getPages());

        // 转换数据列表
        pageResult.setList(resultPage.getRecords().stream().map(this::convertToBrowseHistoryVO).collect(Collectors.toList()));

        return pageResult;
    }

    /**
     * 将ForumPost实体转换为UserPostVO
     * @param forumPost 帖子实体
     * @return UserPostVO
     */
    private MyForumPostVO convertToUserPostVO(ForumPost forumPost) {
        MyForumPostVO vo = new MyForumPostVO();
        vo.setId(forumPost.getId());
        vo.setPostTitle(forumPost.getPostTitle());
        vo.setPostContent(forumPost.getPostContent());
        vo.setViewCount(forumPost.getViewCount());
        vo.setLikeCount(forumPost.getLikeCount());
        vo.setShareCount(forumPost.getShareCount());
        vo.setFavoriteCount(forumPost.getFavoriteCount());
        vo.setCommentCount(forumPost.getCommentCount());
        vo.setCreateAt(forumPost.getCreateAt());
        vo.setUpdateAt(forumPost.getUpdateAt());

        // 处理图片URL数组
        if (forumPost.getImageUrls() != null && !forumPost.getImageUrls().isEmpty()) {
            vo.setImageUrls(forumPost.getImageUrls().toArray(new String[0]));
        } else {
            vo.setImageUrls(new String[0]);
        }

        // 处理标签数组
        if (forumPost.getTags() != null && !forumPost.getTags().isEmpty()) {
            String[] tags = Arrays.stream(forumPost.getTags().split(","))
                    .map(String::trim)
                    .filter(tag -> !tag.isEmpty())
                    .toArray(String[]::new);
            vo.setTags(tags);
        } else {
            vo.setTags(new String[0]);
        }

        return vo;
    }

    /**
     * 将浏览历史记录转换为BrowseHistoryVO
     * @param browsingHistory 浏览历史实体
     * @return BrowseHistoryVO
     */
    private BrowseHistoryVO convertToBrowseHistoryVO(BrowsingHistory browsingHistory) {
        BrowseHistoryVO vo = new BrowseHistoryVO();
        vo.setId(browsingHistory.getId());
        vo.setUserId(browsingHistory.getUserId());
        vo.setContentType(browsingHistory.getContentType());
        vo.setContentId(browsingHistory.getContentId());
        // 使用createdDate作为创建时间
        if (browsingHistory.getCreatedDate() != null) {
            vo.setCreatedAt(browsingHistory.getCreatedDate().atStartOfDay());
        }

        // 查询对应的帖子信息
        ForumPost post = forumPostMapper.selectById(browsingHistory.getContentId());
        if (post != null) {
            vo.setPostTitle(post.getPostTitle());
            vo.setPostContent(post.getPostContent());
            vo.setViewCount(post.getViewCount());
            vo.setLikeCount(post.getLikeCount());
            vo.setCommentCount(post.getCommentCount());

            // 处理图片URL数组
            if (post.getImageUrls() != null && !post.getImageUrls().isEmpty()) {
                vo.setImageUrls(post.getImageUrls().toArray(new String[0]));
            } else {
                vo.setImageUrls(new String[0]);
            }

            // 处理标签数组
            if (post.getTags() != null && !post.getTags().isEmpty()) {
                String[] tags = Arrays.stream(post.getTags().split(","))
                        .map(String::trim)
                        .filter(tag -> !tag.isEmpty())
                        .toArray(String[]::new);
                vo.setTags(tags);
            } else {
                vo.setTags(new String[0]);
            }
        }

        return vo;
    }
}




