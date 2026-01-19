package com.example.order.api.feign;

import com.example.common.core.util.Result;
import com.example.order.api.dto.DeliveryCalculateDTO;
import com.example.order.api.vo.DeliveryCalculateVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "remoteMerchantService", value = "campus-merchant")
public interface RemoteMerchantService {

	@PostMapping("/api/merchant/delivery/calculate")
	Result<DeliveryCalculateVO> calculateDeliveryFee(@RequestBody DeliveryCalculateDTO calculateDTO);
}
