package com.example.merchant.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.merchant.api.entity.MchProductSpec;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品-规格关联Mapper
 *
 * @author campus-merchant
 */
@Mapper
public interface ProductSpecMapper extends BaseMapper<MchProductSpec> {
}