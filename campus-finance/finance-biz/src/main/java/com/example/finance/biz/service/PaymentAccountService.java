package com.example.finance.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.finance.api.dto.PaymentAccountAddDTO;
import com.example.finance.api.dto.PaymentAccountQueryDTO;
import com.example.finance.api.dto.PaymentAccountUpdateDTO;
import com.example.finance.api.entity.PaymentAccount;
import com.example.finance.api.vo.PaymentAccountVO;

/**
 * 平台账户服务
 */
public interface PaymentAccountService extends IService<PaymentAccount> {

    /**
     * 添加平台账户
     *
     * @param addDTO 添加账户请求
     * @return 账户ID
     */
    Long addAccount(PaymentAccountAddDTO addDTO);

    /**
     * 按条件查询账户
     *
     * @param queryDTO 查询条件
     * @return 账户分页列表
     */
    Page<PaymentAccountVO> listByQuery(PaymentAccountQueryDTO queryDTO);

    /**
     * 更新账户状态
     *
     * @param accountId 账户ID
     * @param status 状态：0-禁用 1-启用
     */
    void updateStatus(Long accountId, Integer status);

    /**
     * 获取账户详情
     *
     * @param accountId 账户ID
     * @return 账户详情VO
     */
    PaymentAccountVO getDetail(Long accountId);

    /**
     * 更新账户最后支付时间
     *
     * @param accountId 账户ID
     */
    void updateLastPaymentTime(Long accountId);

    /**
     * 更新账户信息
     *
     * @param accountId 账户ID
     * @param updateDTO 更新请求
     */
    void updateAccount(Long accountId, PaymentAccountUpdateDTO updateDTO);
}
