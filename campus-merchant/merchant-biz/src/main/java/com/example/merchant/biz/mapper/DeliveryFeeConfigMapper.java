package com.example.merchant.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.merchant.api.entity.DeliveryFeeConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 配送费配置Mapper
 */
@Mapper
public interface DeliveryFeeConfigMapper extends BaseMapper<DeliveryFeeConfig> {
}