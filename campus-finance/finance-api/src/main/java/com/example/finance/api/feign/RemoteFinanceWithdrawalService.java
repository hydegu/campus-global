package com.example.finance.api.feign;

import com.example.common.core.util.Result;
import com.example.common.feign.annotation.Inner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程提现服务接口：审核模块调用财务服务
 */
@FeignClient(contextId = "remoteFinanceWithdrawalService", value = "campus-finance")
public interface RemoteFinanceWithdrawalService {

    /**
     * 更新提现状态
     *
     * @param id 提现记录ID
     * @param status 状态：1-审核通过，2-审核拒绝
     * @return Result<Void>
     */
    @Inner
    @PutMapping("/api/finance/withdrawal/{id}/status")
    Result<Void> updateStatus(@PathVariable Long id, @RequestParam Integer status);
}