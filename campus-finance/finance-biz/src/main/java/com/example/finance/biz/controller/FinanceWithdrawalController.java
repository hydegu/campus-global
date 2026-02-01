package com.example.finance.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.util.Result;
import com.example.finance.api.dto.FinanceWithdrawalApplyDTO;
import com.example.finance.api.dto.FinanceWithdrawalAuditDTO;
import com.example.finance.api.dto.FinanceWithdrawalQueryDTO;
import com.example.finance.api.vo.FinanceWithdrawalVO;
import com.example.finance.biz.service.FinanceWithdrawalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * 提现管理Controller
 */
@RestController
@RequestMapping("/api/finance/withdrawal")
@RequiredArgsConstructor
@Tag(name = "提现管理", description = "用户提现申请、管理员审核等功能")
public class FinanceWithdrawalController {

    private final FinanceWithdrawalService financeWithdrawalService;

    @PostMapping("/apply")
    @Operation(summary = "提现申请", description = "用户申请提现（用户端）")
    public Result<Long> applyWithdrawal(@Valid @RequestBody FinanceWithdrawalApplyDTO applyDTO) {
        Long withdrawalId = financeWithdrawalService.applyWithdrawal(applyDTO);
        return Result.ok(withdrawalId);
    }


    @GetMapping("/list")
    @Operation(summary = "查询提现记录", description = "根据查询条件分页查询提现记录")
    public Result<Page<FinanceWithdrawalVO>> listWithdrawals(@ParameterObject FinanceWithdrawalQueryDTO queryDTO) {
        Page<FinanceWithdrawalVO> page = financeWithdrawalService.listByQuery(queryDTO);
        return Result.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "提现详情", description = "根据ID查询提现记录详情")
    @Parameters({
        @Parameter(name = "id", description = "提现记录ID", required = true)
    })
    public Result<FinanceWithdrawalVO> getWithdrawalDetail(@PathVariable Long id) {
        FinanceWithdrawalVO vo = financeWithdrawalService.getDetail(id);
        return Result.ok(vo);
    }
}