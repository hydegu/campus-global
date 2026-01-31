package com.example.finance.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.util.Result;
import com.example.finance.api.dto.FinanceTransactionAddDTO;
import com.example.finance.api.dto.FinanceTransactionQueryDTO;
import com.example.finance.api.entity.FinanceTransaction;
import com.example.finance.api.enums.RelatedTypeEnum;
import com.example.finance.api.enums.TransactionTypeEnum;
import com.example.finance.api.vo.FinanceTransactionVO;
import com.example.finance.biz.mapper.FinanceTransactionMapper;
import com.example.finance.biz.service.FinanceTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author 22417
 * @description 针对表【finance_transaction(财务流水表)】的数据库操作Service实现
 * @createDate 2026-01-30 11:38:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FinanceTransactionServiceImpl extends ServiceImpl<FinanceTransactionMapper, FinanceTransaction>
        implements FinanceTransactionService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTransaction(FinanceTransactionAddDTO addDTO) {
        // 1. 校验流水号是否重复
        LambdaQueryWrapper<FinanceTransaction> checkWrapper = Wrappers.lambdaQuery();
        checkWrapper.eq(FinanceTransaction::getTransactionNo, addDTO.getTransactionNo());
        checkWrapper.isNull(FinanceTransaction::getDeleteAt);
        if (baseMapper.selectCount(checkWrapper) > 0) {
            throw new BusinessException("TRANSACTION_NO_DUPLICATE", "流水号已存在");
        }

        // 2. 组装实体
        FinanceTransaction transaction = new FinanceTransaction();
        transaction.setTransactionNo(addDTO.getTransactionNo());
        transaction.setUserId(addDTO.getUserId());
        transaction.setTransactionType(addDTO.getTransactionType());
        transaction.setAmount(addDTO.getAmount());
        transaction.setRelatedType(addDTO.getRelatedType());
        transaction.setRelatedId(addDTO.getRelatedId());
        transaction.setRemark(addDTO.getRemark());
        transaction.setCreateAt(LocalDateTime.now());
        transaction.setUpdateAt(LocalDateTime.now());

        // 3. 插入数据库
        baseMapper.insert(transaction);

        log.info("创建财务流水成功，流水ID：{}，流水号：{}，用户ID：{}，金额：{}",
                transaction.getId(), transaction.getTransactionNo(),
                transaction.getUserId(), transaction.getAmount());

        return transaction.getId();
    }

    @Override
    public Page<FinanceTransactionVO> listByUser(Long userId, Integer pageNum, Integer pageSize) {
        // 1. 参数校验
        if (userId == null) {
            throw new BusinessException("INVALID_PARAM", "用户ID不能为空");
        }

        // 2. 构建查询条件
        LambdaQueryWrapper<FinanceTransaction> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(FinanceTransaction::getUserId, userId);
        wrapper.isNull(FinanceTransaction::getDeleteAt);
        wrapper.orderByDesc(FinanceTransaction::getCreateAt);

        // 3. 分页查询
        Page<FinanceTransaction> page = baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        // 4. 组装VO
        Page<FinanceTransactionVO> result = new Page<>();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setRecords(page.getRecords().stream()
                .map(this::buildTransactionVO)
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public Page<FinanceTransactionVO> listByQuery(FinanceTransactionQueryDTO queryDTO) {
        // 1. 构建查询条件
        LambdaQueryWrapper<FinanceTransaction> wrapper = Wrappers.lambdaQuery();
        wrapper.isNull(FinanceTransaction::getDeleteAt);

        if (queryDTO.getUserId() != null) {
            wrapper.eq(FinanceTransaction::getUserId, queryDTO.getUserId());
        }
        if (queryDTO.getTransactionType() != null) {
            wrapper.eq(FinanceTransaction::getTransactionType, queryDTO.getTransactionType());
        }
        if (queryDTO.getRelatedType() != null) {
            wrapper.eq(FinanceTransaction::getRelatedType, queryDTO.getRelatedType());
        }
        if (queryDTO.getStartTime() != null) {
            wrapper.ge(FinanceTransaction::getCreateAt, queryDTO.getStartTime());
        }
        if (queryDTO.getEndTime() != null) {
            wrapper.le(FinanceTransaction::getCreateAt, queryDTO.getEndTime());
        }

        wrapper.orderByDesc(FinanceTransaction::getCreateAt);

        // 2. 分页查询
        Page<FinanceTransaction> page = baseMapper.selectPage(
                new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()), wrapper);

        // 3. 组装VO
        Page<FinanceTransactionVO> result = new Page<>();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setRecords(page.getRecords().stream()
                .map(this::buildTransactionVO)
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public FinanceTransactionVO getDetail(Long id) {
        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "流水ID不能为空");
        }

        FinanceTransaction transaction = baseMapper.selectById(id);
        if (transaction == null || transaction.getDeleteAt() != null) {
            throw new BusinessException("TRANSACTION_NOT_FOUND", "流水不存在");
        }

        return buildTransactionVO(transaction);
    }

    /**
     * 生成流水号
     * 格式：FT + yyyyMMddHHmmss + 8位随机大写字符
     */
    private String generateTransactionNo() {
        return "FT" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * 构建流水VO
     */
    private FinanceTransactionVO buildTransactionVO(FinanceTransaction transaction) {
        FinanceTransactionVO vo = new FinanceTransactionVO();
        vo.setId(transaction.getId());
        vo.setTransactionNo(transaction.getTransactionNo());
        vo.setUserId(transaction.getUserId());
        vo.setUserName(transaction.getUserName());
        vo.setUserPhone(transaction.getUserPhone());
        vo.setTransactionType(transaction.getTransactionType());
        TransactionTypeEnum typeEnum = TransactionTypeEnum.getByCode(transaction.getTransactionType());
        vo.setTransactionTypeName(typeEnum != null ? typeEnum.getDesc() : "");
        vo.setAmount(transaction.getAmount());
        vo.setRelatedType(transaction.getRelatedType());
        vo.setRelatedId(transaction.getRelatedId());
        vo.setRemark(transaction.getRemark());
        vo.setCreateAt(transaction.getCreateAt());
        return vo;
    }
}




