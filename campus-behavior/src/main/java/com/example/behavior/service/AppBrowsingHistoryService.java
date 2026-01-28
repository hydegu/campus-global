package com.example.behavior.service;

import com.example.behavior.dto.BrowsingHistoryAddDTO;
import com.example.behavior.dto.BrowsingHistoryQueryDTO;
import com.example.behavior.entity.AppBrowsingHistory;
import com.example.behavior.vo.BrowsingHistoryVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;

/**
* @author 22417
* @description 针对表【app_browsing_history(用户浏览记录表)】的数据库操作Service
* @createDate 2026-01-28 16:20:22
*/
public interface AppBrowsingHistoryService extends IService<AppBrowsingHistory> {

    /**
     * 添加浏览记录
     * 用户浏览论坛帖子或外卖商家时，记录浏览信息
     * 
     * @param addDTO 添加浏览记录请求DTO
     * @return 操作结果，成功返回Result.ok()，失败返回Result.failed()
     */
    Result<Void> addBrowsingHistory(BrowsingHistoryAddDTO addDTO);
    
    /**
     * 分页查询浏览记录列表
     * 查询当前登录用户的浏览记录，支持按内容类型筛选
     * 
     * @param queryDTO 查询条件DTO
     * @return 分页结果，包含浏览记录列表和分页信息
     */
    Result<PageResult<BrowsingHistoryVO>> getBrowsingHistoryList(BrowsingHistoryQueryDTO queryDTO);
}
