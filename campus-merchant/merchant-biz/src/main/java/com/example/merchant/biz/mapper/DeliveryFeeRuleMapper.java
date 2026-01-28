package com.example.merchant.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.merchant.api.entity.DeliveryFeeRule;
import org.apache.ibatis.annotations.Mapper;

/**
 * 配送费规则Mapper
 */
@Mapper
public interface DeliveryFeeRuleMapper extends BaseMapper<DeliveryFeeRule> {
}