package com.example.merchant.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.merchant.api.entity.MchProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品Mapper
 *
 * @author campus-merchant
 */
@Mapper
public interface ProductMapper extends BaseMapper<MchProduct> {
}