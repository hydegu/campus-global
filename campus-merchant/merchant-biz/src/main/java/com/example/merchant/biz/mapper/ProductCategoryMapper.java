package com.example.merchant.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.merchant.api.entity.MchProductCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品-分类关联Mapper
 *
 * @author campus-merchant
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<MchProductCategory> {
}