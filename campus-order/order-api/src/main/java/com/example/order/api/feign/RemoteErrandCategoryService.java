package com.example.order.api.feign;

import com.example.common.core.util.Result;
import com.example.service.api.vo.ErrandCategoryVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 服务分类远程服务接口
 */
@FeignClient(contextId = "remoteErrandCategoryService", value = "campus-service")
public interface RemoteErrandCategoryService {

	/**
	 * 查询服务分类详情
	 * @param id 服务分类ID
	 * @return 服务分类详情
	 */
	@GetMapping("/api/errand-category/{id}")
	Result<ErrandCategoryVO> getCategoryDetail(@PathVariable Long id);
}