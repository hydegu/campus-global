package com.example.merchant.api.feign;

import com.example.common.core.util.Result;
import com.example.common.feign.annotation.Inner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程商品服务接口：审核模块调用商家服务
 */
@FeignClient(contextId = "remoteProductService", value = "campus-merchant")
public interface RemoteProductService {

    /**
     * 更新商品上架状态
     *
     * @param id 商品ID
     * @param shelfStatus 上架状态：0-未上架，1-已上架
     * @return Result<Void>
     */
    @Inner
    @PutMapping("/api/product/{id}/shelf-status")
    Result<Void> updateShelfStatus(@PathVariable Long id, @RequestParam Integer shelfStatus);
}