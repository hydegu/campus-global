package com.example.behavior.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.behavior.dto.BrowsingHistoryAddDTO;
import com.example.behavior.dto.BrowsingHistoryQueryDTO;
import com.example.behavior.entity.AppBrowsingHistory;
import com.example.behavior.enums.ContentTypeEnum;
import com.example.behavior.mapper.AppBrowsingHistoryMapper;
import com.example.behavior.service.AppBrowsingHistoryService;
import com.example.behavior.vo.BrowsingHistoryVO;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
* @author 22417
* @description 针对表【app_browsing_history(用户浏览记录表)】的数据库操作Service实现
* @createDate 2026-01-28 16:20:22
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AppBrowsingHistoryServiceImpl extends ServiceImpl<AppBrowsingHistoryMapper, AppBrowsingHistory>
    implements AppBrowsingHistoryService{

    /**
     * 添加浏览记录
     * 用户浏览论坛帖子或外卖商家时，记录浏览信息
     * 
     * @param addDTO 添加浏览记录请求DTO
     * @return 操作结果，成功返回Result.ok()，失败返回Result.failed()
     */
    @Override
    public Result<Void> addBrowsingHistory(BrowsingHistoryAddDTO addDTO) {
        log.info("添加浏览记录，contentType: {}, contentId: {}", addDTO.getContentType(), addDTO.getContentId());
        
        // 1. 从SecurityContext获取当前用户ID
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == 0) {
            log.error("未获取到当前用户ID");
            return Result.failed("用户未登录");
        }
        
        // 2. 验证contentType是否合法
        ContentTypeEnum contentTypeEnum = ContentTypeEnum.getByCode(addDTO.getContentType());
        if (contentTypeEnum == null) {
            log.error("无效的内容类型: {}", addDTO.getContentType());
            return Result.failed("无效的内容类型");
        }
        
        // 3. 根据contentType调用不同的服务获取内容信息（标题、图片、描述）
        String contentTitle = null;
        String contentImage = null;
        String contentDescription = null;
        
        if (contentTypeEnum == ContentTypeEnum.FORUM_POST) {
            // 如果contentType为1（论坛帖子），调用forum服务获取帖子信息
            // TODO: 调用forum服务获取帖子信息
            // contentTitle = forumService.getPostTitle(addDTO.getContentId());
            // contentImage = forumService.getPostImage(addDTO.getContentId());
            // contentDescription = forumService.getPostDescription(addDTO.getContentId());
            log.warn("forum服务暂未实现，无法获取帖子信息，contentId: {}", addDTO.getContentId());
            contentTitle = "TODO: 帖子标题";
            contentImage = null;
            contentDescription = null;
        } else if (contentTypeEnum == ContentTypeEnum.MERCHANT) {
            // 如果contentType为2（外卖商家），调用merchant服务获取商家信息
            // TODO: 调用merchant服务获取商家信息
            // contentTitle = merchantService.getMerchantName(addDTO.getContentId());
            // contentImage = merchantService.getMerchantLogo(addDTO.getContentId());
            // contentDescription = merchantService.getMerchantDescription(addDTO.getContentId());
            log.warn("merchant服务暂未实现，无法获取商家信息，contentId: {}", addDTO.getContentId());
            contentTitle = "TODO: 商家名称";
            contentImage = null;
            contentDescription = null;
        }
        
        // 4. 构建AppBrowsingHistory对象并保存到数据库
        AppBrowsingHistory browsingHistory = new AppBrowsingHistory();
        browsingHistory.setUserId(userId);
        browsingHistory.setContentType(addDTO.getContentType());
        browsingHistory.setContentId(addDTO.getContentId());
        browsingHistory.setContentTitle(contentTitle);
        browsingHistory.setContentImage(contentImage);
        browsingHistory.setContentDescription(contentDescription);
        browsingHistory.setCreatedDate(LocalDate.now());
        
        boolean saved = this.save(browsingHistory);
        if (!saved) {
            log.error("保存浏览记录失败");
            return Result.failed("保存浏览记录失败");
        }
        
        log.info("添加浏览记录成功，id: {}", browsingHistory.getId());
        return Result.ok();
    }
    
    /**
     * 分页查询浏览记录列表
     * 查询当前登录用户的浏览记录，支持按内容类型筛选
     * 
     * @param queryDTO 查询条件DTO
     * @return 分页结果，包含浏览记录列表和分页信息
     */
    @Override
    public Result<PageResult<BrowsingHistoryVO>> getBrowsingHistoryList(BrowsingHistoryQueryDTO queryDTO) {
        log.info("查询浏览记录列表，contentType: {}, pageNum: {}, pageSize: {}", 
                queryDTO.getContentType(), queryDTO.getPageNum(), queryDTO.getPageSize());
        
        // 1. 从SecurityContext获取当前用户ID
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == 0) {
            log.error("未获取到当前用户ID");
            return Result.failed("用户未登录");
        }
        
        // 2. 设置分页参数
        int pageNum = queryDTO.getPageNum() != null && queryDTO.getPageNum() > 0 ? queryDTO.getPageNum() : 1;
        int pageSize = queryDTO.getPageSize() != null && queryDTO.getPageSize() > 0 ? queryDTO.getPageSize() : 10;
        
        // 3. 构建查询条件（用户ID、内容类型）
        LambdaQueryWrapper<AppBrowsingHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppBrowsingHistory::getUserId, userId);
        
        if (queryDTO.getContentType() != null) {
            queryWrapper.eq(AppBrowsingHistory::getContentType, queryDTO.getContentType());
        }
        
        queryWrapper.orderByDesc(AppBrowsingHistory::getCreatedDate, AppBrowsingHistory::getId);
        
        // 4. 使用MyBatis-Plus的分页功能查询数据
        IPage<AppBrowsingHistory> page = this.page(new Page<>(pageNum, pageSize), queryWrapper);
        
        // 5. 将Entity转换为VO，并填充contentTypeDesc
        java.util.List<BrowsingHistoryVO> voList = page.getRecords().stream().map(entity -> {
            BrowsingHistoryVO vo = BrowsingHistoryVO.builder()
                    .id(entity.getId())
                    .createdDate(entity.getCreatedDate())
                    .userId(entity.getUserId())
                    .contentType(entity.getContentType())
                    .contentTypeDesc(ContentTypeEnum.getText(entity.getContentType()))
                    .contentId(entity.getContentId())
                    .contentTitle(entity.getContentTitle())
                    .contentImage(entity.getContentImage())
                    .contentDescription(entity.getContentDescription())
                    .build();
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        
        // 6. 构建分页结果
        PageResult<BrowsingHistoryVO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setCurrent(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setPages(page.getPages());
        pageResult.setList(voList);
        
        log.info("查询浏览记录列表成功，total: {}", page.getTotal());
        return Result.ok(pageResult);
    }
}




