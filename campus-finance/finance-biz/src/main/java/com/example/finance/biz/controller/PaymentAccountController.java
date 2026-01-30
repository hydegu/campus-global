package com.example.finance.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.util.Result;
import com.example.finance.api.dto.PaymentAccountAddDTO;
import com.example.finance.api.dto.PaymentAccountQueryDTO;
import com.example.finance.api.vo.PaymentAccountVO;
import com.example.finance.biz.service.PaymentAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * 平台账户管理Controller
 */
@RestController
@RequestMapping("/api/finance/account")
@RequiredArgsConstructor
@Tag(name = "平台账户管理", description = "管理员管理平台企业账户等功能")
public class PaymentAccountController {

    private final PaymentAccountService paymentAccountService;

    @PostMapping("/add")
    @Operation(summary = "添加平台账户", description = "管理员添加平台企业账户（管理端）")
    public Result<Long> addAccount(@Valid @RequestBody PaymentAccountAddDTO addDTO) {
        Long accountId = paymentAccountService.addAccount(addDTO);
        return Result.ok(accountId);
    }

    @GetMapping("/list")
    @Operation(summary = "查询平台账户列表", description = "根据查询条件分页查询平台账户列表")
    public Result<Page<PaymentAccountVO>> listAccounts(@ParameterObject PaymentAccountQueryDTO queryDTO) {
        Page<PaymentAccountVO> page = paymentAccountService.listByQuery(queryDTO);
        return Result.ok(page);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "更新账户状态", description = "启用或禁用平台账户（管理端）")
    @Parameters({
        @Parameter(name = "id", description = "账户ID", required = true),
        @Parameter(name = "status", description = "状态：0-禁用 1-启用", required = true)
    })
    public Result<Void> updateAccountStatus(@PathVariable Long id, @RequestParam Integer status) {
        paymentAccountService.updateStatus(id, status);
        return Result.ok();
    }
}