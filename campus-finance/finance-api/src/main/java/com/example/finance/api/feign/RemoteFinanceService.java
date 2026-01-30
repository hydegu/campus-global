package com.example.finance.api.feign;

import com.example.common.core.constant.ServiceNameConstants;
import com.example.common.core.util.Result;
import com.example.common.feign.annotation.NoToken;
import com.example.finance.api.dto.FinanceTransactionAddDTO;
import com.example.finance.api.dto.FinanceTransactionQueryDTO;
import com.example.finance.api.dto.StatisticsQueryDTO;
import com.example.finance.api.vo.FinanceTransactionVO;
import com.example.finance.api.vo.TransactionLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 远程财务服务接口：提供财务流水、统计等功能
 */
@FeignClient(contextId = "remoteFinanceService", value = ServiceNameConstants.FINANCE_SERVICE)
public interface RemoteFinanceService {

    /**
     * 创建财务流水
     *
     * @param addDTO 创建流水请求
     * @return Result<Long> 流水ID
     */
    @PostMapping("/api/finance/transaction/create")
    Result<Long> createTransaction(@RequestBody FinanceTransactionAddDTO addDTO);

    /**
     * 按用户查询财务流水
     *
     * @param queryDTO 查询条件
     * @return Result<Page<FinanceTransactionVO>> 流水分页列表
     */
    @NoToken
    @GetMapping("/api/finance/transaction/list")
    Result<?> listByUser(@SpringQueryMap FinanceTransactionQueryDTO queryDTO);

    /**
     * 获取统计数据
     *
     * @param queryDTO 查询条件
     * @return Result<TransactionLogVO> 统计页数据
     */
    @NoToken
    @GetMapping("/api/finance/statistics/overview")
    Result<TransactionLogVO> getStatistics(@SpringQueryMap StatisticsQueryDTO queryDTO);
}