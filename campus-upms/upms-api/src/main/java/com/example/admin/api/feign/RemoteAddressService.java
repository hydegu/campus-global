package com.example.admin.api.feign;

import com.example.common.core.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 地址远程服务接口
 */
@FeignClient(contextId = "remoteAddressService", value = "campus-upms")
public interface RemoteAddressService {

	/**
	 * 获取地址经纬度
	 * @param addressId 地址ID
	 * @return 经纬度字符串（格式：经度,纬度）
	 */
	@GetMapping("/api/address/coordinate/{addressId}")
	Result<String> getAddressCoordinate(@PathVariable Long addressId);

	/**
	 * 获取商家地址经纬度
	 * @param merchantId 商家ID
	 * @return 经纬度字符串（格式：经度,纬度）
	 */
	@GetMapping("/api/address/merchant/{merchantId}")
	Result<String> getMerchantAddressCoordinate(@PathVariable Long merchantId);
}