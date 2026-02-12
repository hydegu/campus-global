package com.example.service.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.exception.BusinessException;
import com.example.service.api.dto.CommissionConfigAddDTO;
import com.example.service.api.dto.CommissionConfigUpdateDTO;
import com.example.service.api.entity.CommissionConfig;
import com.example.service.api.entity.ErrandCategory;
import com.example.service.api.vo.CommissionConfigVO;
import com.example.service.biz.mapper.CommissionConfigMapper;
import com.example.service.biz.mapper.ErrandCategoryMapper;
import com.example.service.biz.service.CommissionConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分佣配置服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommissionConfigServiceImpl extends ServiceImpl<CommissionConfigMapper, CommissionConfig>
        implements CommissionConfigService {

    private final ErrandCategoryMapper errandCategoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCommissionConfig(CommissionConfigAddDTO dto) {
        // 参数校验
        if (dto == null) {
            throw new BusinessException("INVALID_PARAM", "请求参数不能为空");
        }

        // configType=1（全局默认）时，categoryId可为null；其他类型必须提供categoryId
        if (dto.getConfigType() == null) {
            throw new BusinessException("INVALID_PARAM", "配置类型不能为空");
        }

        if (dto.getConfigType() != 1 && dto.getCategoryId() == null) {
            throw new BusinessException("INVALID_PARAM", "服务分类ID不能为空");
        }

        // 如果提供了categoryId，验证分类是否存在
        if (dto.getCategoryId() != null) {
            ErrandCategory category = errandCategoryMapper.selectById(dto.getCategoryId());
            if (category == null) {
                throw new BusinessException("CATEGORY_NOT_FOUND", "服务分类不存在");
            }
            if (category.getDeleteAt() != null) {
                throw new BusinessException("CATEGORY_DELETED", "服务分类已删除");
            }
        }

        // 检查是否已存在相同的配置（避免重复）
        LambdaQueryWrapper<CommissionConfig> checkWrapper = Wrappers.lambdaQuery();
        checkWrapper.eq(CommissionConfig::getConfigType, dto.getConfigType());
        if (dto.getCategoryId() != null) {
            checkWrapper.eq(CommissionConfig::getCategoryId, dto.getCategoryId());
        } else {
            checkWrapper.isNull(CommissionConfig::getCategoryId);
        }
        checkWrapper.isNull(CommissionConfig::getDeleteAt);
        checkWrapper.eq(CommissionConfig::getStatus, 1);

        List<CommissionConfig> existingConfigs = baseMapper.selectList(checkWrapper);
        if (!existingConfigs.isEmpty()) {
            throw new BusinessException("CONFIG_EXISTS", "已存在相同的分佣配置，请先删除或禁用");
        }

        // 构建实体
        CommissionConfig config = new CommissionConfig();
        config.setCategoryId(dto.getCategoryId());
        config.setConfigType(dto.getConfigType());
        config.setCommissionRate(dto.getCommissionRate());
        config.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);
        config.setCreateAt(LocalDateTime.now());
        config.setUpdateAt(LocalDateTime.now());

        // 保存配置
        baseMapper.insert(config);

        log.info("成功新增分佣配置: configType={}, categoryId={}, commissionRate={}",
                dto.getConfigType(), dto.getCategoryId(), dto.getCommissionRate());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCommissionConfig(Long id, CommissionConfigUpdateDTO dto) {
        // 参数校验
        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "配置ID不能为空");
        }
        if (dto == null) {
            throw new BusinessException("INVALID_PARAM", "请求参数不能为空");
        }

        // 查询配置信息
        CommissionConfig config = baseMapper.selectById(id);
        if (config == null) {
            throw new BusinessException("CONFIG_NOT_FOUND", "分佣配置不存在");
        }
        if (config.getDeleteAt() != null) {
            throw new BusinessException("CONFIG_DELETED", "分佣配置已删除");
        }

        // 如果更新了categoryId，验证分类是否存在
        if (dto.getCategoryId() != null && !dto.getCategoryId().equals(config.getCategoryId())) {
            if (dto.getCategoryId() != 0) {
                ErrandCategory category = errandCategoryMapper.selectById(dto.getCategoryId());
                if (category == null) {
                    throw new BusinessException("CATEGORY_NOT_FOUND", "服务分类不存在");
                }
                if (category.getDeleteAt() != null) {
                    throw new BusinessException("CATEGORY_DELETED", "服务分类已删除");
                }
            }
        }

        // 更新字段（只更新非空字段）
        if (dto.getCategoryId() != null) {
            config.setCategoryId(dto.getCategoryId());
        }
        if (dto.getConfigType() != null) {
            config.setConfigType(dto.getConfigType());
        }
        if (dto.getCommissionRate() != null) {
            config.setCommissionRate(dto.getCommissionRate());
        }
        if (dto.getStatus() != null) {
            config.setStatus(dto.getStatus());
        }
        config.setUpdateAt(LocalDateTime.now());

        // 软删除旧配置
        config.setDeleteAt(LocalDateTime.now());
        baseMapper.updateById(config);

        // 创建新配置（保持历史记录）
        CommissionConfig newConfig = new CommissionConfig();
        newConfig.setCategoryId(config.getCategoryId());
        newConfig.setConfigType(config.getConfigType());
        newConfig.setCommissionRate(config.getCommissionRate());
        newConfig.setStatus(config.getStatus());
        newConfig.setCreateAt(config.getCreateAt());
        newConfig.setUpdateAt(LocalDateTime.now());

        baseMapper.insert(newConfig);

        log.info("成功更新分佣配置: id={}, newId={}", id, newConfig.getId());
    }

    /**
     * 获取配置类型名称
     */
    private String getConfigTypeName(Integer configType) {
        if (configType == null) {
            return "";
        }
        switch (configType) {
            case 1:
                return "全局默认";
            case 2:
                return "服务分佣";
            case 3:
                return "商家分佣";
            case 4:
                return "合伙人分佣";
            default:
                return "未知类型";
        }
    }

    @Override
    public CommissionConfig getCommissionConfig(Long categoryId, Integer configType) {
        if (configType == null) {
            return null;
        }

        // configType=1（全局默认）时，优先查询全局配置
        if (configType == 1) {
            LambdaQueryWrapper<CommissionConfig> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(CommissionConfig::getConfigType, 1);
            wrapper.isNull(CommissionConfig::getCategoryId);
            wrapper.isNull(CommissionConfig::getDeleteAt);
            wrapper.eq(CommissionConfig::getStatus, 1);
            wrapper.orderByDesc(CommissionConfig::getCreateAt);
            wrapper.last("LIMIT 1");
            return baseMapper.selectOne(wrapper);
        }

        // 其他类型，优先查询指定分类的配置
        if (categoryId != null) {
            LambdaQueryWrapper<CommissionConfig> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(CommissionConfig::getConfigType, configType);
            wrapper.eq(CommissionConfig::getCategoryId, categoryId);
            wrapper.isNull(CommissionConfig::getDeleteAt);
            wrapper.eq(CommissionConfig::getStatus, 1);
            wrapper.orderByDesc(CommissionConfig::getCreateAt);
            wrapper.last("LIMIT 1");
            CommissionConfig categoryConfig = baseMapper.selectOne(wrapper);
            if (categoryConfig != null) {
                return categoryConfig;
            }
        }

        // 降级查询全局默认配置（configType=1）
        LambdaQueryWrapper<CommissionConfig> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CommissionConfig::getConfigType, 1);
        wrapper.isNull(CommissionConfig::getCategoryId);
        wrapper.isNull(CommissionConfig::getDeleteAt);
        wrapper.eq(CommissionConfig::getStatus, 1);
        wrapper.orderByDesc(CommissionConfig::getCreateAt);
        wrapper.last("LIMIT 1");
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Map<Integer, CommissionConfig> getCommissionConfigs(Long categoryId) {
        Map<Integer, CommissionConfig> result = new HashMap<>();

        // 查询指定分类的所有配置
        LambdaQueryWrapper<CommissionConfig> wrapper = Wrappers.lambdaQuery();
        wrapper.isNull(CommissionConfig::getDeleteAt);
        wrapper.eq(CommissionConfig::getStatus, 1);
        wrapper.orderByDesc(CommissionConfig::getCreateAt);
        if (categoryId != null) {
            wrapper.and(w -> w.eq(CommissionConfig::getCategoryId, categoryId).or().isNull(CommissionConfig::getCategoryId));
        } else {
            wrapper.isNull(CommissionConfig::getCategoryId);
        }
        List<CommissionConfig> allConfigs = baseMapper.selectList(wrapper);

        // 按配置类型分组，保留最新的配置
        Map<Integer, CommissionConfig> configMap = allConfigs.stream()
                .collect(Collectors.toMap(
                        CommissionConfig::getConfigType,
                        config -> config,
                        (existing, replacement) -> existing.getCreateAt().isAfter(replacement.getCreateAt()) ? existing : replacement
                ));

        // 获取全局默认配置（configType=1）
        CommissionConfig globalConfig = configMap.get(1);
        if (globalConfig == null) {
            // 如果没有全局默认配置，创建一个默认配置（0%）
            globalConfig = new CommissionConfig();
            globalConfig.setConfigType(1);
            globalConfig.setCategoryId(null);
            globalConfig.setCommissionRate(BigDecimal.ZERO);
        }

        // 确保每种类型都有配置（使用全局默认作为兜底）
        for (int configType = 1; configType <= 4; configType++) {
            if (!configMap.containsKey(configType)) {
                CommissionConfig defaultConfig = new CommissionConfig();
                defaultConfig.setConfigType(configType);
                defaultConfig.setCategoryId(categoryId);
                defaultConfig.setCommissionRate(globalConfig.getCommissionRate());
                configMap.put(configType, defaultConfig);
            }
        }

        return configMap;
    }
}