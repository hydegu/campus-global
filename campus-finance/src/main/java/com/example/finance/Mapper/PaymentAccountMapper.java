package com.example.finance.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.finance.entity.PaymentAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentAccountMapper extends BaseMapper<PaymentAccount> {
}
