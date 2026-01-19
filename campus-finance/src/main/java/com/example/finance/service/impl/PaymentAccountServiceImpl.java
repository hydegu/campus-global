package com.example.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.finance.Mapper.PaymentAccountMapper;
import com.example.finance.dto.PaymentAccountDTO;
import com.example.finance.dto.PaymentAccountQueryDTO;
import com.example.finance.entity.PaymentAccount;
import com.example.finance.service.PaymentAccountService;
import com.example.finance.vo.PaymentAccountPageVO;
import com.example.finance.vo.PaymentAccountVO;
import com.example.finance.common.util.PageUtil;
import org.springframework.util.StringUtils;
import org.apache.seata.spring.annotation.GlobalTransactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentAccountServiceImpl extends ServiceImpl<PaymentAccountMapper, PaymentAccount> implements PaymentAccountService {


    /**
     * 添加账户
     *
     * @param paymentAccountdto 账户信息
     * @return
     */
    @GlobalTransactional(name = "add-payment-account", rollbackFor = Exception.class)
    @Override
    public PaymentAccountVO addPaymentAccount(PaymentAccountDTO paymentAccountdto) {
        // 校验账户名称是否重复
        if (baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>().eq(PaymentAccount::getAccountName, paymentAccountdto.getAccountName())) != null) {
            throw new IllegalArgumentException("账户名称已存在");
        }
        // 校验账户编码是否重复
        if (baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>().eq(PaymentAccount::getAccountCode, paymentAccountdto.getAccountCode())) != null) {
            throw new IllegalArgumentException("账户编码已存在");
        }
        //验明电话
        if (baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>().eq(PaymentAccount::getContactPhone, paymentAccountdto.getContactPhone())) != null) {
            throw new IllegalArgumentException("电话已存在");
        }
        //验证邮箱是否重复
        if (baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>().eq(PaymentAccount::getContactEmail, paymentAccountdto.getContactEmail())) != null) {
            throw new IllegalArgumentException("邮箱已存在");
        }
        //验证账号是否重复
        if (baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>().eq(PaymentAccount::getBankAccountNumber, paymentAccountdto.getBankAccountNumber())) != null) {
            throw new IllegalArgumentException("账号已存在");
        }
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setAccountName(paymentAccountdto.getAccountName());
        paymentAccount.setAccountCode(paymentAccountdto.getAccountCode());
        paymentAccount.setContactPhone(paymentAccountdto.getContactPhone());
        paymentAccount.setContactEmail(paymentAccountdto.getContactEmail());
        paymentAccount.setBankAccountNumber(paymentAccountdto.getBankAccountNumber());
        paymentAccount.setBankAccountName(paymentAccountdto.getBankAccountName());
        paymentAccount.setBankName(paymentAccountdto.getBankName());
        paymentAccount.setBankBranch(paymentAccountdto.getBankBranch());
        paymentAccount.setStatus(1);
        paymentAccount.setRemark(paymentAccountdto.getRemark());
        this.save(paymentAccount);
        PaymentAccountVO paymentAccountVO = PaymentAccountVO.builder()
                        .accountName(paymentAccount.getAccountName())
                        .accountCode(paymentAccount.getAccountCode())
                        .contactPhone(paymentAccount.getContactPhone())
                        .bankAccountNumber(paymentAccount.getBankAccountNumber())
                        .bankAccountName(paymentAccount.getBankAccountName())
                        .bankName(paymentAccount.getBankName())
                        .build();
        return paymentAccountVO;
    }

    /**
     * 查看账户详情
     * @param id 账户ID
     * @return 账户详情
     */
     @Override
    public PaymentAccountVO getPaymentAccountById(Long id) {
         PaymentAccount paymentAccount = baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>()
                 .eq(PaymentAccount::getId, id));
        if (paymentAccount == null) {
            throw new IllegalArgumentException("账户不存在");
        }
        if(paymentAccount.getStatus()!=1){
            throw new IllegalArgumentException("账户已被禁用");
        }
        if(paymentAccount.getDeleteAt()!=null){
            throw new IllegalArgumentException("账户已被删除");
        }
        PaymentAccountVO paymentAccountVO = PaymentAccountVO.builder()
                        .id(paymentAccount.getId())
                        .accountName(paymentAccount.getAccountName())
                        .accountCode(paymentAccount.getAccountCode())
                        .contactPhone(paymentAccount.getContactPhone())
                        .contactEmail(paymentAccount.getContactEmail())
                        .bankAccountNumber(paymentAccount.getBankAccountNumber())
                        .bankAccountName(paymentAccount.getBankAccountName())
                        .bankName(paymentAccount.getBankName())
                        .bankBranch(paymentAccount.getBankBranch())
                        .lastPaymentTime(paymentAccount.getLastPaymentTime())
                        .status(paymentAccount.getStatus())
                        .remark(paymentAccount.getRemark())
                        .createAt(paymentAccount.getCreateAt())
                        .updateAt(paymentAccount.getUpdateAt())
                        .build();
        return paymentAccountVO;
    }

    /**
     * 分页查询账户列表
     * @param query 查询条件
     * @return 账户列表
     */
    @Override
    public PaymentAccountPageVO getPaymentAccountPage(PaymentAccountQueryDTO query) {
        // 空值检查
        if (query == null) {
            query = new PaymentAccountQueryDTO();
        }
        
        // 构建查询条件
        LambdaQueryWrapper<PaymentAccount> wrapper = Wrappers.<PaymentAccount>lambdaQuery()
                .like(StringUtils.hasText(query.getAccountName()), PaymentAccount::getAccountName, query.getAccountName())
                .like(StringUtils.hasText(query.getAccountCode()), PaymentAccount::getAccountCode, query.getAccountCode())
                .like(StringUtils.hasText(query.getBankName()), PaymentAccount::getBankName, query.getBankName())
                .eq(PaymentAccount::getStatus, 1)
                .eq(PaymentAccount::getDeleteAt, null)
                .orderByDesc(PaymentAccount::getCreateAt);

        // 处理分页参数
        Integer pageNum = query.getPage() != null ? query.getPage() : 1;
        Integer pageSize = query.getSize() != null ? query.getSize() : 10;
        
        // 分页查询
        Page<PaymentAccount> page = PageUtil.createPage(pageNum, pageSize);
        Page<PaymentAccount> result = this.page(page, wrapper);

        // 转换为VO
        List<PaymentAccountVO> voList = result.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        return new PaymentAccountPageVO(result.getCurrent(), result.getSize(), result.getTotal(), voList);
    }

    /**
     * 转换为VO
     * @param paymentAccount 账户实体
     * @return 账户VO
     */
    private PaymentAccountVO convertToVO(PaymentAccount paymentAccount) {
        return PaymentAccountVO.builder()
                .id(paymentAccount.getId())
                .accountName(paymentAccount.getAccountName())
                .accountCode(paymentAccount.getAccountCode())
                .contactPhone(paymentAccount.getContactPhone())
                .contactEmail(paymentAccount.getContactEmail())
                .bankAccountNumber(paymentAccount.getBankAccountNumber())
                .bankAccountName(paymentAccount.getBankAccountName())
                .bankName(paymentAccount.getBankName())
                .bankBranch(paymentAccount.getBankBranch())
                .lastPaymentTime(paymentAccount.getLastPaymentTime())
                .status(paymentAccount.getStatus())
                .remark(paymentAccount.getRemark())
                .createAt(paymentAccount.getCreateAt())
                .updateAt(paymentAccount.getUpdateAt())
                .build();
    }

    /**
     * 更新账户信息
     * 根据id
     *
     * @param id                账户ID
     * @param paymentAccountdto 账户信息
     */
    @Override
    public PaymentAccountVO updatePaymentAccount(Long id, PaymentAccountDTO paymentAccountdto) {
        PaymentAccount paymentAccount = baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>()
                .eq(PaymentAccount::getId, id));
        if (paymentAccount == null) {
            throw new IllegalArgumentException("账户不存在");
        }
        if(paymentAccount.getStatus()!=1){
            throw new IllegalArgumentException("账户已被禁用");
        }
        if(paymentAccount.getDeleteAt()!=null){
            throw new IllegalArgumentException("账户已被删除");
        }
        
        // 校验账户名称是否重复（排除当前账户）
        if (!paymentAccount.getAccountName().equals(paymentAccountdto.getAccountName())) {
            if (baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>()
                    .eq(PaymentAccount::getAccountName, paymentAccountdto.getAccountName())
                    .ne(PaymentAccount::getId, id)) != null) {
                throw new IllegalArgumentException("账户名称已存在");
            }
        }
        // 校验电话是否重复（排除当前账户）
        if (!paymentAccount.getContactPhone().equals(paymentAccountdto.getContactPhone())) {
            if (baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>()
                    .eq(PaymentAccount::getContactPhone, paymentAccountdto.getContactPhone())
                    .ne(PaymentAccount::getId, id)) != null) {
                throw new IllegalArgumentException("电话已存在");
            }
        }
        
        // 校验邮箱是否重复（排除当前账户）
        if (!paymentAccount.getContactEmail().equals(paymentAccountdto.getContactEmail())) {
            if (baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>()
                    .eq(PaymentAccount::getContactEmail, paymentAccountdto.getContactEmail())
                    .ne(PaymentAccount::getId, id)) != null) {
                throw new IllegalArgumentException("邮箱已存在");
            }
        }
        
        // 校验账号是否重复（排除当前账户）
        if (!paymentAccount.getBankAccountNumber().equals(paymentAccountdto.getBankAccountNumber())) {
            if (baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>()
                    .eq(PaymentAccount::getBankAccountNumber, paymentAccountdto.getBankAccountNumber())
                    .ne(PaymentAccount::getId, id)) != null) {
                throw new IllegalArgumentException("账号已存在");
            }
        }
        paymentAccount.setBankAccountNumber(paymentAccountdto.getBankAccountNumber());
        paymentAccount.setBankAccountName(paymentAccountdto.getBankAccountName());
        if (paymentAccountdto.getStatus() != null) {
            paymentAccount.setStatus(paymentAccountdto.getStatus());
        }
        paymentAccount.setRemark(paymentAccountdto.getRemark());
        this.updateById(paymentAccount);
        return convertToVO(paymentAccount);
    }

    /**
     * 根据账户ID删除账户
     * @param id 账户ID
     * @return 删除结果
     */
    @Override
    public Boolean deletePaymentAccount(Long id) {
        PaymentAccount paymentAccount = baseMapper.selectOne(new LambdaQueryWrapper<PaymentAccount>()
                .eq(PaymentAccount::getId, id));
        if (paymentAccount == null) {
            throw new IllegalArgumentException("账户不存在");
        }
        if(paymentAccount.getStatus()!=1){
            throw new IllegalArgumentException("账户已被禁用");
        }
        if(paymentAccount.getDeleteAt()!=null){
            throw new IllegalArgumentException("账户已被删除");
        }
        paymentAccount.setDeleteAt(LocalDateTime.now());
        return this.removeById(paymentAccount);
    }
}
