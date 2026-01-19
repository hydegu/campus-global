package com.example.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.finance.Mapper.PaymentAccountMapper;
import com.example.finance.entity.PaymentAccount;
import com.example.finance.service.PaymentAccountService;
import org.apache.seata.spring.annotation.GlobalTransactional;

public class PaymentAccountServiceImpl extends ServiceImpl<PaymentAccountMapper, PaymentAccount> implements PaymentAccountService {


    /**
     * 添加账户
     * @param paymentAccount 账户信息
     * @return
     */
    @GlobalTransactional(name = "add-payment-account", rollbackFor = Exception.class)
    @Override
    public void addPaymentAccount(PaymentAccount paymentAccount) {

    }

}
