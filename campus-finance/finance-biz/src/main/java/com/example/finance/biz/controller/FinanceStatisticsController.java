package com.example.finance.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.util.Result;
import com.example.finance.api.dto.CategoryTurnoverQueryDTO;
import com.example.finance.api.dto.MerchantTurnoverQueryDTO;
import com.example.finance.api.dto.StatisticsQueryDTO;
import com.example.finance.api.dto.StaffTurnoverQueryDTO;
import com.example.finance.api.vo.CategoryTurnoverVO;
import com.example.finance.api.vo.MerchantTurnoverVO;
import com.example.finance.api.vo.StaffTurnoverVO;
import com.example.finance.api.vo.TransactionLogVO;
import com.example.finance.biz.service.FinanceStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 财务统计管理Controller
 */
@RestController
@RequestMapping("/api/finance/statistics")
@RequiredArgsConstructor
@Tag(name = "财务统计管理", description = "财务数据统计和报表等功能")
public class FinanceStatisticsController {

    private final FinanceStatisticsService financeStatisticsService;

    @GetMapping("/overview")
    @Operation(summary = "统计页数据", description = "获取财务统计页的概览数据（管理端）")
    public Result<TransactionLogVO> getOverview(@ParameterObject StatisticsQueryDTO queryDTO) {
        TransactionLogVO vo = financeStatisticsService.getOverview(queryDTO);
        return Result.ok(vo);
    }

    @GetMapping("/merchant")
    @Operation(summary = "商家流水统计", description = "统计商家流水数据（管理端）")
    public Result<Page<MerchantTurnoverVO>> getMerchantTurnover(@ParameterObject MerchantTurnoverQueryDTO queryDTO) {
        Page<MerchantTurnoverVO> page = financeStatisticsService.getMerchantTurnover(queryDTO);
        return Result.ok(page);
    }

    @GetMapping("/category")
    @Operation(summary = "品类流水统计", description = "统计品类流水数据（管理端）")
    public Result<Page<CategoryTurnoverVO>> getCategoryTurnover(@ParameterObject CategoryTurnoverQueryDTO queryDTO) {
        Page<CategoryTurnoverVO> page = financeStatisticsService.getCategoryTurnover(queryDTO);
        return Result.ok(page);
    }

    @GetMapping("/staff")
    @Operation(summary = "服务人员流水统计", description = "统计服务人员流水数据（管理端）")
    public Result<Page<StaffTurnoverVO>> getStaffTurnover(@ParameterObject StaffTurnoverQueryDTO queryDTO) {
        Page<StaffTurnoverVO> page = financeStatisticsService.getStaffTurnover(queryDTO);
        return Result.ok(page);
    }
}