package com.example.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.finance.dto.PaymentAccountDTO;
import com.example.finance.dto.PaymentAccountQueryDTO;
import com.example.finance.entity.PaymentAccount;
import com.example.finance.vo.PaymentAccountPageVO;
import com.example.finance.vo.PaymentAccountVO;

public interface PaymentAccountService extends IService<PaymentAccount> {

    /**
     * 添加账户
     *
     * @param paymentAccountdto 账户信息
     * @return 账户ID
     */
    PaymentAccountVO addPaymentAccount(PaymentAccountDTO paymentAccountdto);

    /**
     * 查看账户详情
     *
     * @param id 账户ID
     * @return 账户详情
     */
    PaymentAccountVO getPaymentAccountById(Long id);

    /**
     * 分页查询账户列表
     *
     * @param query 查询条件
     * @return 账户列表
     */
    PaymentAccountPageVO getPaymentAccountPage(PaymentAccountQueryDTO query);

    /**
     * 根据账户id更新账户信息
     *
     * @param id                账户ID
     * @param paymentAccountdto 账户信息
     * @return 更新结果
     */
     PaymentAccountVO updatePaymentAccount(Long id, PaymentAccountDTO paymentAccountdto);

     /**
      * 根据账户ID删除账户
      * @param id 账户ID
      * @return 删除结果
      */
      Boolean deletePaymentAccount(Long id);

}
