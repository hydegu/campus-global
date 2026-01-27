package com.example.merchant.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.merchant.api.entity.MchSpec;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品规格Mapper
 *
 * @author campus-merchant
 */
@Mapper
public interface SpecMapper extends BaseMapper<MchSpec> {
}