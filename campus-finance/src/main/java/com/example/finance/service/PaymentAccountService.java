package com.example.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.finance.entity.PaymentAccount;

public interface PaymentAccountService extends IService<PaymentAccount> {

    /**
     * 添加账户
     * @param paymentAccount
     */
    void addPaymentAccount(PaymentAccount paymentAccount);
}
