package com.example.order.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.order.api.entity.OrderOperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作审计日志 Mapper
 */
@Mapper
public interface OrderOperationLogMapper extends BaseMapper<OrderOperationLog> {
}