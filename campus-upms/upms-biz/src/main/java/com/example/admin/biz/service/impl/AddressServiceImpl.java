package com.example.admin.biz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.entity.Address;
import com.example.admin.api.entity.UserMch;
import com.example.admin.biz.mapper.AddressMapper;
import com.example.admin.biz.mapper.UserMchMapper;
import com.example.admin.biz.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 地址服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
		implements AddressService {

	private final UserMchMapper userMchMapper;

	@Override
	public String getAddressCoordinate(Long addressId) {
		if (addressId == null) {
			return null;
		}
		Address address = baseMapper.selectById(addressId);
		if (address == null || address.getDeleteAt() != null) {
			return null;
		}
		if (address.getReceiverLng() == null || address.getReceiverLat() == null) {
			return null;
		}
		return address.getReceiverLng() + "," + address.getReceiverLat();
	}

	@Override
	public String getMerchantAddressCoordinate(Long merchantId) {
		if (merchantId == null) {
			return null;
		}
		UserMch merchant = userMchMapper.selectById(merchantId);
		if (merchant == null || merchant.getDeleteTime() != null) {
			return null;
		}
		if (merchant.getAddressId() == null) {
			return null;
		}
		return getAddressCoordinate(merchant.getAddressId());
	}
}