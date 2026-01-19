package com.example.finance.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.finance.dto.PaymentAccountDTO;
import com.example.finance.service.PaymentAccountService;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Registered
@RequestMapping("/paymentAccount")
public class PaymentAccountController {

    @Autowired
    private PaymentAccountService paymentAccountService;


    /**
     * 添加账户
     * TODO 添加账户
     * @param paymentAccountDTO 账户信息
     */
    @PostMapping("/addaccount")
    @SentinelResource(value = "addaccount", blockHandler = "handleBlock")
    public void addPaymentAccount(PaymentAccountDTO paymentAccountDTO) {
    }
}
