package com.example.finance.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.util.Result;
import com.example.finance.api.dto.FinanceTransactionAddDTO;
import com.example.finance.api.dto.FinanceTransactionQueryDTO;
import com.example.finance.api.vo.FinanceTransactionVO;
import com.example.finance.biz.service.FinanceTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * 财务流水管理Controller
 */
@RestController
@RequestMapping("/api/finance/transaction")
@RequiredArgsConstructor
@Tag(name = "财务流水管理", description = "财务流水创建、查询等功能")
public class FinanceTransactionController {

    private final FinanceTransactionService financeTransactionService;

    @PostMapping("/create")
    @Operation(summary = "创建流水", description = "创建财务流水记录（内部接口）")
    public Result<Long> createTransaction(@Valid @RequestBody FinanceTransactionAddDTO addDTO) {
        Long transactionId = financeTransactionService.createTransaction(addDTO);
        return Result.ok(transactionId);
    }

    @GetMapping("/list")
    @Operation(summary = "查询流水列表", description = "根据查询条件分页查询财务流水列表")
    public Result<Page<FinanceTransactionVO>> listTransactions(@ParameterObject FinanceTransactionQueryDTO queryDTO) {
        Page<FinanceTransactionVO> page = financeTransactionService.listByQuery(queryDTO);
        return Result.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "流水详情", description = "根据ID查询财务流水详情")
    @Parameters({
        @Parameter(name = "id", description = "流水ID", required = true)
    })
    public Result<FinanceTransactionVO> getTransactionDetail(@PathVariable Long id) {
        FinanceTransactionVO vo = financeTransactionService.getDetail(id);
        return Result.ok(vo);
    }
}