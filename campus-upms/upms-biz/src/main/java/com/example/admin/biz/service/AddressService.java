package com.example.admin.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admin.api.entity.Address;

/**
 * 地址服务接口
 */
public interface AddressService extends IService<Address> {

	/**
	 * 根据地址ID获取地址经纬度字符串
	 * @param addressId 地址ID
	 * @return 经纬度字符串（格式：经度,纬度），如果不存在返回null
	 */
	String getAddressCoordinate(Long addressId);

	/**
	 * 根据商家ID获取商家地址经纬度字符串
	 * @param merchantId 商家ID
	 * @return 经纬度字符串（格式：经度,纬度），如果不存在返回null
	 */
	String getMerchantAddressCoordinate(Long merchantId);
}