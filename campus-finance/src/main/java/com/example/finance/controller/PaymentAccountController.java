package com.example.finance.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.common.core.util.Result;
import com.example.finance.dto.PaymentAccountDTO;
import com.example.finance.dto.PaymentAccountQueryDTO;
import com.example.finance.service.PaymentAccountService;
import com.example.finance.vo.PaymentAccountVO;
import com.example.finance.vo.PaymentAccountPageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;

@Tag(name = "支付账户管理", description = "支付账户的增删改查功能")
@RestController
@RequestMapping("/paymentAccount")
@RequiredArgsConstructor
public class PaymentAccountController {

    private final PaymentAccountService paymentAccountService;

    /**
     * 添加账户
     * @param paymentAccountDTO 账户信息
     * @return 账户信息
     */
    @PostMapping("/addaccount")
    @Operation(summary = "新增支付账户", description = "创建新的支付账户。需要提供账户名称、账户编码、联系信息、银行信息等基本字段。")
    @SentinelResource(value = "addaccount", blockHandler = "handleBlockAdd")
    public Result<PaymentAccountVO> addPaymentAccount(@RequestBody PaymentAccountDTO paymentAccountDTO) {
        PaymentAccountVO result = paymentAccountService.addPaymentAccount(paymentAccountDTO);
        return Result.ok(result);
    }

    /**
     * 根据ID查询账户
     */
    @GetMapping("/getaccount/{id}")
    @Operation(summary = "查询支付账户", description = "根据账户ID查询账户详情。")
    @SentinelResource(value = "getaccount", blockHandler = "handleBlockGet")
    public Result<PaymentAccountVO> getPaymentAccountById(@PathVariable Long id) {
        PaymentAccountVO result = paymentAccountService.getPaymentAccountById(id);
        return Result.ok(result);
    }

    /**
     * 分页查询账户列表
     * @param query 查询条件
     * @return 账户列表
     */
    @GetMapping("/accountlist")
    @Operation(summary = "分页查询账户列表", description = "分页查询账户列表，支持按账户名称、编码、状态筛选，按创建时间倒序排序。")
    @SentinelResource(value = "list", blockHandler = "handleBlockPage")
    public Result<PaymentAccountPageVO> getPaymentAccountPage(@ParameterObject PaymentAccountQueryDTO query) {
        PaymentAccountPageVO result = paymentAccountService.getPaymentAccountPage(query);
        return Result.ok(result);
    }

    /**
     * 更新账户信息根据id
     */
    @PutMapping("/updateaccount/{id}")
    @Operation(summary = "更新支付账户", description = "根据账户ID更新账户信息。需要提供账户名称、账户编码、联系信息、银行信息等基本字段。")
    @SentinelResource(value = "updateaccount", blockHandler = "handleBlockUpdate")
    public Result<PaymentAccountVO> updatePaymentAccount(@PathVariable Long id, @RequestBody PaymentAccountDTO paymentAccountDTO) {
        PaymentAccountVO result = paymentAccountService.updatePaymentAccount(id, paymentAccountDTO);
        return Result.ok(result);
    }

    /**
     * Sentinel 限流处理方法（用于POST请求）
     */
    public Result<PaymentAccountVO> handleBlockAdd(PaymentAccountDTO paymentAccountDTO, com.alibaba.csp.sentinel.slots.block.BlockException ex) {
        // 限流处理逻辑
        System.out.println("请求被限流");
        return Result.failed("请求被限流，请稍后重试");
    }

    /**
     * Sentinel 限流处理方法（用于PUT请求）
     */
    public Result<PaymentAccountVO> handleBlockUpdate(Long id, PaymentAccountDTO paymentAccountDTO, com.alibaba.csp.sentinel.slots.block.BlockException ex) {
        // 限流处理逻辑
        System.out.println("请求被限流");
        return Result.failed("请求被限流，请稍后重试");
    }

    /**
     * Sentinel 限流处理方法（用于GET请求）
     */
    public Result<PaymentAccountVO> handleBlockGet(Long id, com.alibaba.csp.sentinel.slots.block.BlockException ex) {
        // 限流处理逻辑
        System.out.println("请求被限流");
        return Result.failed("请求被限流，请稍后重试");
    }

    /**
     * Sentinel 限流处理方法（用于分页查询）
     */
    public Result<PaymentAccountPageVO> handleBlockPage(PaymentAccountQueryDTO query, com.alibaba.csp.sentinel.slots.block.BlockException ex) {
        // 限流处理逻辑
        System.out.println("请求被限流");
        return Result.failed("请求被限流，请稍后重试");
    }
}
