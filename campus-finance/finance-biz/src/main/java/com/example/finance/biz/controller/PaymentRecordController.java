package com.example.finance.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.core.util.Result;
import com.example.finance.api.dto.PaymentRecordCreateDTO;
import com.example.finance.api.dto.PaymentRecordQueryDTO;
import com.example.finance.api.vo.PaymentRecordVO;
import com.example.finance.biz.service.PaymentRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * 打款管理Controller
 */
@RestController
@RequestMapping("/api/finance/payment")
@RequiredArgsConstructor
@Tag(name = "打款管理", description = "管理员创建并执行打款等功能")
public class PaymentRecordController {

    private final PaymentRecordService paymentRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建打款记录", description = "管理员创建打款记录（管理端）")
    public Result<Long> createPaymentRecord(@Valid @RequestBody PaymentRecordCreateDTO createDTO) {
        Long paymentId = paymentRecordService.createPaymentRecord(createDTO);
        return Result.ok(paymentId);
    }

    @PostMapping("/execute")
    @Operation(summary = "执行打款", description = "管理员执行打款操作（管理端）")
    @Parameters({
        @Parameter(name = "paymentId", description = "打款记录ID", required = true)
    })
    public Result<Void> executePayment(@RequestParam Long paymentId) {
        paymentRecordService.executePayment(paymentId);
        return Result.ok();
    }

    @GetMapping("/list")
    @Operation(summary = "查询打款记录", description = "根据查询条件分页查询打款记录")
    public Result<Page<PaymentRecordVO>> listPayments(@ParameterObject PaymentRecordQueryDTO queryDTO) {
        Page<PaymentRecordVO> page = paymentRecordService.listByQuery(queryDTO);
        return Result.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "打款详情", description = "根据ID查询打款记录详情")
    @Parameters({
        @Parameter(name = "id", description = "打款记录ID", required = true)
    })
    public Result<PaymentRecordVO> getPaymentDetail(@PathVariable Long id) {
        PaymentRecordVO vo = paymentRecordService.getDetail(id);
        return Result.ok(vo);
    }
}