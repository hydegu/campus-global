package com.example.behavior.controller;

import com.example.behavior.dto.BrowsingHistoryAddDTO;
import com.example.behavior.dto.BrowsingHistoryQueryDTO;
import com.example.behavior.service.AppBrowsingHistoryService;
import com.example.behavior.vo.BrowsingHistoryVO;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 浏览记录管理
 * 用于记录和查询用户的浏览历史
 *
 * @author campus-behavior
 */
@RestController
@RequestMapping("/api/app/browsing-history")
@RequiredArgsConstructor
@Validated
@Tag(name = "浏览记录管理", description = "用户浏览历史记录相关接口")
public class BrowsingHistoryController {

    private final AppBrowsingHistoryService browsingHistoryService;

    @PostMapping
    @Operation(summary = "添加浏览记录",
            description = "用户浏览论坛帖子或外卖商家时，记录浏览信息。用户ID从SecurityContext获取，防止越权攻击")
    public Result<Void> addBrowsingHistory(@Valid @RequestBody BrowsingHistoryAddDTO addDTO) {
        return browsingHistoryService.addBrowsingHistory(addDTO);
    }

    @GetMapping
    @Operation(summary = "查询浏览记录列表",
            description = "查询当前登录用户的浏览记录列表，支持按内容类型筛选（论坛帖子或外卖商家），支持分页查询。用户ID从SecurityContext获取，防止越权攻击")
    public Result<PageResult<BrowsingHistoryVO>> getBrowsingHistoryList(@Valid BrowsingHistoryQueryDTO queryDTO) {
        return browsingHistoryService.getBrowsingHistoryList(queryDTO);
    }
}