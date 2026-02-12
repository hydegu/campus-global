package com.example.service.api.feign;

import com.example.common.core.util.Result;
import com.example.service.api.entity.CommissionConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 远程调用分佣配置接口
 */
@FeignClient(contextId = "remoteCommissionConfigService", value = "campus-service")
public interface RemoteCommissionConfigService {

    /**
     * 批量获取分佣配置
     *
     * @param categoryId 服务分类ID（可为null，为null时查询全局默认配置）
     * @return 分佣配置映射，key为配置类型：1-全局默认，2-服务分佣，3-商家分佣，4-合伙人分佣
     */
    @GetMapping("/api/commission-config/list")
    Result<Map<Integer, CommissionConfig>> getCommissionConfigs(@RequestParam(required = false) Long categoryId);
}
