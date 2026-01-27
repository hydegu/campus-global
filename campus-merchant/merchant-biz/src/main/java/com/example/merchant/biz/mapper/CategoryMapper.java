package com.example.merchant.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.merchant.api.entity.MchCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类Mapper
 *
 * @author campus-merchant
 */
@Mapper
public interface CategoryMapper extends BaseMapper<MchCategory> {
}