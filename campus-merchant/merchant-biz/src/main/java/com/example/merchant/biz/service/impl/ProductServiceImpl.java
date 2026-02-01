package com.example.merchant.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.api.dto.MchInfoDTO;
import com.example.admin.api.feign.RemoteUserService;
import com.example.common.core.constant.CommonConstants;
import com.example.common.core.exception.BusinessException;
import com.example.common.core.util.Result;
import com.example.common.mybatis.utils.PageResult;
import com.example.merchant.api.dto.product.*;
import com.example.merchant.api.dto.spec.SpecUpdateDTO;
import com.example.merchant.api.entity.*;
import com.example.merchant.api.vo.ProductVO;
import com.example.merchant.api.vo.SpecVO;
import com.example.merchant.biz.mapper.*;
import com.example.merchant.biz.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 商品服务实现
 *
 * @author campus-merchant
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, MchProduct>
        implements ProductService {

    private final SpecMapper specMapper;
    private final ProductSpecMapper productSpecMapper;
    private final ProductCategoryMapper productCategoryMapper;
    private final CategoryMapper categoryMapper;
    private final RemoteUserService remoteUserService;

    @Override
    public PageResult<ProductVO> listProducts(ProductQueryDTO queryDTO) {
        log.info("分页查询商品列表，参数：{}", queryDTO);

        // 参数校验
        validatePageParams(queryDTO.getPageNum(), queryDTO.getPageSize());

        // 构建查询条件
        LambdaQueryWrapper<MchProduct> wrapper = buildProductWrapper(queryDTO);

        // 执行分页查询
        IPage<MchProduct> page = baseMapper.selectPage(
                new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()),
                wrapper
        );

        // 如果没有数据，返回空分页
        if (page.getRecords().isEmpty()) {
            return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), Collections.emptyList());
        }

        // 批量查询关联数据
        List<Long> productIds = page.getRecords().stream()
                .map(MchProduct::getId)
                .collect(Collectors.toList());

        Map<Long, List<Long>> productCategoryMap = batchQueryProductCategories(productIds);
        Set<Long> categoryIds = productCategoryMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toSet());
        Map<Long, MchCategory> categoryMap = batchQueryCategories(categoryIds);

        Map<Long, List<MchSpec>> specMap = batchQuerySpecs(productIds);

        List<Long> merchantIds = page.getRecords().stream()
                .map(MchProduct::getMerchantId)
                .distinct()
                .collect(Collectors.toList());
        Map<Long, MchInfoDTO> mchInfoMap = batchQueryMchInfo(merchantIds);

        // 组装VO对象
        List<ProductVO> voList = page.getRecords().stream()
                .map(product -> buildProductVO(product, productCategoryMap, categoryMap, specMap, mchInfoMap))
                .collect(Collectors.toList());

        // 返回分页结果
        return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), voList);
    }

    @Override
    public ProductVO getProductDetail(Long id) {
        log.info("查询商品详情，参数：id={}", id);

        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "商品ID不能为空");
        }

        // 查询商品信息
        MchProduct product = baseMapper.selectById(id);
        if (product == null) {
            throw new BusinessException("PRODUCT_NOT_FOUND", "商品不存在");
        }

        // 检查是否已删除
        if (product.getDeleteAt() != null) {
            throw new BusinessException("PRODUCT_DELETED", "商品已被删除");
        }

        // 批量查询关联数据
        Map<Long, List<Long>> productCategoryMap = batchQueryProductCategories(Collections.singletonList(id));
        Set<Long> categoryIds = new HashSet<>(productCategoryMap.getOrDefault(id, Collections.emptyList()));
        Map<Long, MchCategory> categoryMap = batchQueryCategories(categoryIds);

        Map<Long, List<MchSpec>> specMap = batchQuerySpecs(Collections.singletonList(id));

        Map<Long, MchInfoDTO> mchInfoMap = batchQueryMchInfo(Collections.singletonList(product.getMerchantId()));

        // 构建VO对象
        return buildProductVO(product, productCategoryMap, categoryMap, specMap, mchInfoMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProduct(ProductAddDTO dto) {
        log.info("新增商品，参数：{}", dto);

        // 参数校验
        if (dto == null) {
            throw new BusinessException("INVALID_PARAM", "新增商品参数不能为空");
        }

        // 构建商品实体
        MchProduct product = new MchProduct();
        product.setProductName(dto.getProductName());
        product.setTitle(dto.getTitle());
        product.setSellingPoints(dto.getSellingPoints());
        product.setDescription(dto.getDescription());
        product.setMainImage(dto.getMainImage());
        product.setImages(dto.getImages() != null ? dto.getImages() : Collections.emptyList());
        product.setMerchantId(dto.getMerchantId());
        product.setPrice(dto.getPrice());
        product.setProfitType(dto.getProfitType());
        product.setPartnerProfit(dto.getPartnerProfit());
        product.setMerchantProfit(dto.getMerchantProfit());
        product.setSpecType(dto.getSpecType());
        product.setShelfStatus(dto.getShelfStatus() != null ? dto.getShelfStatus() : 0);
        product.setSortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 0);
        product.setCreateAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());

        // 保存商品
        baseMapper.insert(product);

        // 如果是多规格，保存规格信息
        if (dto.getSpecType() != null && dto.getSpecType() == 2) {
            if (dto.getSpecs() == null || dto.getSpecs().isEmpty()) {
                throw new BusinessException("INVALID_PARAM", "多规格商品必须提供规格信息");
            }

            // 保存规格
            List<MchSpec> specs = dto.getSpecs().stream().map(specDto -> {
                MchSpec spec = new MchSpec();
                spec.setProductId(product.getId());
                spec.setGroupName(specDto.getGroupName());
                spec.setSpecName(specDto.getSpecName());
                spec.setPrice(specDto.getPrice());
                spec.setSpecImage(specDto.getSpecImage());
                spec.setStatus(specDto.getStatus() != null ? specDto.getStatus() : 1);
                spec.setIsDefault(specDto.getIsDefault() != null ? specDto.getIsDefault() : 0);
                spec.setSortOrder(specDto.getSortOrder() != null ? specDto.getSortOrder() : 0);
                spec.setCreateAt(LocalDateTime.now());
                spec.setUpdateAt(LocalDateTime.now());
                return spec;
            }).collect(Collectors.toList());

            specs.forEach(specMapper::insert);

            // 保存商品-规格关联关系
            specs.forEach(spec -> {
                MchProductSpec productSpec = new MchProductSpec();
                productSpec.setProductId(product.getId());
                productSpec.setSpecId(spec.getId());
                productSpecMapper.insert(productSpec);
            });
        }

        // 保存商品-分类关联关系
        if (dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
            dto.getCategoryIds().forEach(categoryId -> {
                MchProductCategory productCategory = new MchProductCategory();
                productCategory.setProductId(product.getId());
                productCategory.setCategoryId(categoryId);
                productCategoryMapper.insert(productCategory);
            });
        }

        log.info("新增商品成功，商品ID：{}", product.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(Long id, ProductUpdateDTO dto) {
        log.info("更新商品，参数：id={}, dto={}", id, dto);

        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "商品ID不能为空");
        }
        if (dto == null) {
            throw new BusinessException("INVALID_PARAM", "更新参数不能为空");
        }

        // 查询商品
        MchProduct product = baseMapper.selectById(id);
        if (product == null) {
            throw new BusinessException("PRODUCT_NOT_FOUND", "商品不存在");
        }

        // 检查是否已删除
        if (product.getDeleteAt() != null) {
            throw new BusinessException("PRODUCT_DELETED", "商品已被删除");
        }

        // 更新商品基本信息
        if (StrUtil.isNotBlank(dto.getProductName())) {
            product.setProductName(dto.getProductName());
        }
        if (StrUtil.isNotBlank(dto.getTitle())) {
            product.setTitle(dto.getTitle());
        }
        if (StrUtil.isNotBlank(dto.getSellingPoints())) {
            product.setSellingPoints(dto.getSellingPoints());
        }
        if (StrUtil.isNotBlank(dto.getDescription())) {
            product.setDescription(dto.getDescription());
        }
        if (StrUtil.isNotBlank(dto.getMainImage())) {
            product.setMainImage(dto.getMainImage());
        }
        if (dto.getImages() != null) {
            product.setImages(dto.getImages());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getProfitType() != null) {
            product.setProfitType(dto.getProfitType());
        }
        if (dto.getPartnerProfit() != null) {
            product.setPartnerProfit(dto.getPartnerProfit());
        }
        if (dto.getMerchantProfit() != null) {
            product.setMerchantProfit(dto.getMerchantProfit());
        }
        if (dto.getSpecType() != null) {
            product.setSpecType(dto.getSpecType());
        }
        if (dto.getShelfStatus() != null) {
            product.setShelfStatus(dto.getShelfStatus());
        }
        if (dto.getSortOrder() != null) {
            product.setSortOrder(dto.getSortOrder());
        }
        product.setUpdateAt(LocalDateTime.now());
        baseMapper.updateById(product);

        // 更新商品-分类关联关系
        if (dto.getCategoryIds() != null) {
            // 删除旧的关联关系
            LambdaQueryWrapper<MchProductCategory> categoryWrapper = Wrappers.lambdaQuery();
            categoryWrapper.eq(MchProductCategory::getProductId, id);
            productCategoryMapper.delete(categoryWrapper);

            // 插入新的关联关系
            if (!dto.getCategoryIds().isEmpty()) {
                dto.getCategoryIds().forEach(categoryId -> {
                    MchProductCategory productCategory = new MchProductCategory();
                    productCategory.setProductId(id);
                    productCategory.setCategoryId(categoryId);
                    productCategoryMapper.insert(productCategory);
                });
            }
        }

        // 更新规格信息
        if (dto.getSpecs() != null) {
            // 获取当前商品的所有规格
            LambdaQueryWrapper<MchSpec> currentSpecWrapper = Wrappers.lambdaQuery();
            currentSpecWrapper.eq(MchSpec::getProductId, id);
            currentSpecWrapper.isNull(MchSpec::getDeleteAt);
            List<MchSpec> currentSpecs = specMapper.selectList(currentSpecWrapper);

            // 将当前规格转换为 Map，key 为规格ID
            Map<Long, MchSpec> currentSpecMap = currentSpecs.stream()
                    .collect(Collectors.toMap(MchSpec::getId, Function.identity()));

            // 获取更新列表中的规格ID
            Set<Long> updatedSpecIds = dto.getSpecs().stream()
                    .map(SpecUpdateDTO::getId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            // 软删除不在更新列表中的规格
            List<Long> specsToDelete = currentSpecs.stream()
                    .map(MchSpec::getId)
                    .filter(specId -> !updatedSpecIds.contains(specId))
                    .collect(Collectors.toList());

            if (!specsToDelete.isEmpty()) {
                // 软删除规格
                specsToDelete.forEach(specId -> {
                    MchSpec specToDelete = currentSpecMap.get(specId);
                    if (specToDelete != null) {
                        specToDelete.setDeleteAt(LocalDateTime.now());
                        specMapper.updateById(specToDelete);
                    }
                });

                // 删除规格关联关系
                LambdaQueryWrapper<MchProductSpec> deleteSpecWrapper = Wrappers.lambdaQuery();
                deleteSpecWrapper.eq(MchProductSpec::getProductId, id);
                deleteSpecWrapper.in(MchProductSpec::getSpecId, specsToDelete);
                productSpecMapper.delete(deleteSpecWrapper);
            }

            // 更新或创建规格
            for (SpecUpdateDTO specDto : dto.getSpecs()) {
                if (specDto.getId() != null) {
                    // 更新现有规格
                    MchSpec specToUpdate = currentSpecMap.get(specDto.getId());
                    if (specToUpdate != null) {
                        if (specDto.getGroupName() != null) {
                            specToUpdate.setGroupName(specDto.getGroupName());
                        }
                        if (specDto.getSpecName() != null) {
                            specToUpdate.setSpecName(specDto.getSpecName());
                        }
                        if (specDto.getPrice() != null) {
                            specToUpdate.setPrice(specDto.getPrice());
                        }
                        if (specDto.getSpecImage() != null) {
                            specToUpdate.setSpecImage(specDto.getSpecImage());
                        }
                        if (specDto.getStatus() != null) {
                            specToUpdate.setStatus(specDto.getStatus());
                        }
                        if (specDto.getIsDefault() != null) {
                            specToUpdate.setIsDefault(specDto.getIsDefault());
                        }
                        if (specDto.getSortOrder() != null) {
                            specToUpdate.setSortOrder(specDto.getSortOrder());
                        }
                        specToUpdate.setUpdateAt(LocalDateTime.now());
                        specMapper.updateById(specToUpdate);
                    }
                }
            }
        }

        log.info("更新商品成功，商品ID：{}", id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Long id) {
        log.info("删除商品，参数：id={}", id);

        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "商品ID不能为空");
        }

        // 查询商品
        MchProduct product = baseMapper.selectById(id);
        if (product == null) {
            throw new BusinessException("PRODUCT_NOT_FOUND", "商品不存在");
        }

        // 检查是否已删除
        if (product.getDeleteAt() != null) {
            throw new BusinessException("PRODUCT_DELETED", "商品已被删除");
        }

        // 软删除商品
        product.setDeleteAt(LocalDateTime.now());
        baseMapper.updateById(product);

        // 软删除关联规格
        LambdaQueryWrapper<MchSpec> specWrapper = Wrappers.lambdaQuery();
        specWrapper.eq(MchSpec::getProductId, id);
        List<MchSpec> specs = specMapper.selectList(specWrapper);
        specs.forEach(spec -> {
            spec.setDeleteAt(LocalDateTime.now());
            specMapper.updateById(spec);
        });

        log.info("删除商品成功，商品ID：{}", id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAddProducts(ProductBatchAddDTO dto) {
        log.info("批量新增商品，参数：{}", dto);

        if (dto == null || dto.getProducts() == null || dto.getProducts().isEmpty()) {
            throw new BusinessException("INVALID_PARAM", "批量新增商品参数不能为空");
        }

        for (ProductAddDTO productDTO : dto.getProducts()) {
            addProduct(productDTO);
        }

        log.info("批量新增商品成功，共{}个商品", dto.getProducts().size());
    }

    @Override
    public void updateProductShelfStatus(Long id, ProductShelfStatusDTO statusDTO) {
        log.info("修改商品上架状态，参数：id={}, statusDTO={}", id, statusDTO);

        if (id == null) {
            throw new BusinessException("INVALID_PARAM", "商品ID不能为空");
        }
        if (statusDTO == null || statusDTO.getShelfStatus() == null) {
            throw new BusinessException("INVALID_PARAM", "上架状态不能为空");
        }

        // 查询商品
        MchProduct product = baseMapper.selectById(id);
        if (product == null) {
            throw new BusinessException("PRODUCT_NOT_FOUND", "商品不存在");
        }

        // 检查是否已删除
        if (product.getDeleteAt() != null) {
            throw new BusinessException("PRODUCT_DELETED", "商品已被删除");
        }

        // 更新上架状态
        product.setShelfStatus(statusDTO.getShelfStatus());
        product.setUpdateAt(LocalDateTime.now());
        baseMapper.updateById(product);

        log.info("修改商品上架状态成功，商品ID：{}，新状态：{}", id, statusDTO.getShelfStatus());
    }

    // ========== 公共辅助方法 ==========

    /**
     * 校验分页参数
     */
    private void validatePageParams(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 1) {
            throw new BusinessException("INVALID_PARAM", "页码必须大于0");
        }
        if (pageSize == null || pageSize < 1 || pageSize > 100) {
            throw new BusinessException("INVALID_PARAM", "每页大小必须在1-100之间");
        }
    }

    /**
     * 构建商品查询条件
     */
    private LambdaQueryWrapper<MchProduct> buildProductWrapper(ProductQueryDTO queryDTO) {
        LambdaQueryWrapper<MchProduct> wrapper = Wrappers.lambdaQuery();

        // 软删除过滤
        wrapper.isNull(MchProduct::getDeleteAt);

        // 商品ID
        if (queryDTO.getProductId() != null) {
            wrapper.eq(MchProduct::getId, queryDTO.getProductId());
        }

        // 商品名称模糊查询
        if (StrUtil.isNotBlank(queryDTO.getProductName())) {
            wrapper.like(MchProduct::getProductName, queryDTO.getProductName());
        }

        // 商品标题模糊查询
        if (StrUtil.isNotBlank(queryDTO.getTitle())) {
            wrapper.like(MchProduct::getTitle, queryDTO.getTitle());
        }

        // 商家ID
        if (queryDTO.getMerchantId() != null) {
            wrapper.eq(MchProduct::getMerchantId, queryDTO.getMerchantId());
        }

        // 价格范围
        if (queryDTO.getMinPrice() != null) {
            wrapper.ge(MchProduct::getPrice, queryDTO.getMinPrice());
        }
        if (queryDTO.getMaxPrice() != null) {
            wrapper.le(MchProduct::getPrice, queryDTO.getMaxPrice());
        }

        // 规格类型
        if (queryDTO.getSpecType() != null) {
            wrapper.eq(MchProduct::getSpecType, queryDTO.getSpecType());
        }

        // 上架状态
        if (queryDTO.getShelfStatus() != null) {
            wrapper.eq(MchProduct::getShelfStatus, queryDTO.getShelfStatus());
        }

        // 分类ID（需要通过中间表查询）
        if (queryDTO.getCategoryId() != null) {
            LambdaQueryWrapper<MchProductCategory> categoryWrapper = Wrappers.lambdaQuery();
            categoryWrapper.eq(MchProductCategory::getCategoryId, queryDTO.getCategoryId());
            List<MchProductCategory> relations = productCategoryMapper.selectList(categoryWrapper);
            if (!relations.isEmpty()) {
                List<Long> productIds = relations.stream()
                        .map(MchProductCategory::getProductId)
                        .collect(Collectors.toList());
                wrapper.in(MchProduct::getId, productIds);
            } else {
                // 如果没有关联的商品，返回空结果
                wrapper.eq(MchProduct::getId, -1L);
            }
        }

        // 排序
        if (StrUtil.isNotBlank(queryDTO.getSortField())) {
            boolean isAsc = !"desc".equalsIgnoreCase(queryDTO.getSortOrder());
            String sortField = queryDTO.getSortField().toLowerCase();

            // 根据字段名使用对应的 Lambda 表达式
            switch (sortField) {
                case "id":
                    wrapper.orderBy(true, isAsc, MchProduct::getId);
                    break;
                case "productname":
                case "product_name":
                    wrapper.orderBy(true, isAsc, MchProduct::getProductName);
                    break;
                case "title":
                    wrapper.orderBy(true, isAsc, MchProduct::getTitle);
                    break;
                case "price":
                    wrapper.orderBy(true, isAsc, MchProduct::getPrice);
                    break;
                case "merchantid":
                case "merchant_id":
                    wrapper.orderBy(true, isAsc, MchProduct::getMerchantId);
                    break;
                case "spectype":
                case "spec_type":
                    wrapper.orderBy(true, isAsc, MchProduct::getSpecType);
                    break;
                case "shelfstatus":
                case "shelf_status":
                    wrapper.orderBy(true, isAsc, MchProduct::getShelfStatus);
                    break;
                case "sortorder":
                case "sort_order":
                    wrapper.orderBy(true, isAsc, MchProduct::getSortOrder);
                    break;
                case "createat":
                case "create_at":
                    if (isAsc) {
                        wrapper.orderByAsc(MchProduct::getCreateAt);
                    } else {
                        wrapper.orderByDesc(MchProduct::getCreateAt);
                    }
                    break;
                case "updateat":
                case "update_at":
                    if (isAsc) {
                        wrapper.orderByAsc(MchProduct::getUpdateAt);
                    } else {
                        wrapper.orderByDesc(MchProduct::getUpdateAt);
                    }
                    break;
                default:
                    // 默认按创建时间降序
                    wrapper.orderByDesc(MchProduct::getCreateAt);
            }
        } else {
            wrapper.orderByDesc(MchProduct::getCreateAt);
        }

        return wrapper;
    }

    /**
     * 批量查询商品-分类关联关系
     */
    private Map<Long, List<Long>> batchQueryProductCategories(List<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return Collections.emptyMap();
        }
        LambdaQueryWrapper<MchProductCategory> wrapper = Wrappers.lambdaQuery();
        wrapper.in(MchProductCategory::getProductId, productIds);
        List<MchProductCategory> relations = productCategoryMapper.selectList(wrapper);
        return relations.stream()
                .collect(Collectors.groupingBy(MchProductCategory::getProductId,
                        Collectors.mapping(MchProductCategory::getCategoryId, Collectors.toList())));
    }

    /**
     * 批量查询分类信息
     */
    private Map<Long, MchCategory> batchQueryCategories(Set<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return Collections.emptyMap();
        }
        return categoryMapper.selectBatchIds(categoryIds).stream()
                .collect(Collectors.toMap(MchCategory::getId, Function.identity()));
    }

    /**
     * 批量查询商品规格
     */
    private Map<Long, List<MchSpec>> batchQuerySpecs(List<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return Collections.emptyMap();
        }
        LambdaQueryWrapper<MchSpec> wrapper = Wrappers.lambdaQuery();
        wrapper.in(MchSpec::getProductId, productIds);
        wrapper.isNull(MchSpec::getDeleteAt);
        List<MchSpec> specs = specMapper.selectList(wrapper);
        return specs.stream()
                .collect(Collectors.groupingBy(MchSpec::getProductId));
    }

    /**
     * 批量查询商家信息
     */
    private Map<Long, MchInfoDTO> batchQueryMchInfo(List<Long> merchantIds) {
        if (merchantIds == null || merchantIds.isEmpty()) {
            return Collections.emptyMap();
        }
        Result<List<MchInfoDTO>> result = remoteUserService.batchGetMchInfo(merchantIds);
        if (result.getCode() != CommonConstants.SUCCESS || result.getData() == null) {
            return Collections.emptyMap();
        }
        return result.getData().stream()
                .collect(Collectors.toMap(MchInfoDTO::getMchId, Function.identity()));
    }

    /**
     * 构建 ProductVO
     */
    private ProductVO buildProductVO(MchProduct product,
                                     Map<Long, List<Long>> productCategoryMap,
                                     Map<Long, MchCategory> categoryMap,
                                     Map<Long, List<MchSpec>> specMap,
                                     Map<Long, MchInfoDTO> mchInfoMap) {
        ProductVO vo = new ProductVO();
        vo.setId(product.getId());
        vo.setProductName(product.getProductName());
        vo.setTitle(product.getTitle());
        vo.setSellingPoints(product.getSellingPoints());
        vo.setDescription(product.getDescription());
        vo.setMainImage(product.getMainImage());
        vo.setImages(product.getImages() != null ? (List<String>) product.getImages() : Collections.emptyList());
        vo.setMerchantId(product.getMerchantId());
        vo.setPrice(product.getPrice());
        vo.setProfitType(product.getProfitType());
        vo.setPartnerProfit(product.getPartnerProfit());
        vo.setMerchantProfit(product.getMerchantProfit());
        vo.setSpecType(product.getSpecType());
        vo.setShelfStatus(product.getShelfStatus());
        vo.setAuditId(product.getAuditId());
        vo.setSortOrder(product.getSortOrder());

        // 填充分类信息
        List<Long> categoryIds = productCategoryMap.getOrDefault(product.getId(), Collections.emptyList());
        vo.setCategoryIds(categoryIds);
        if (!categoryIds.isEmpty()) {
            List<String> categoryNames = categoryIds.stream()
                    .map(categoryId -> {
                        MchCategory category = categoryMap.get(categoryId);
                        return category != null ? category.getCategoryName() : "";
                    })
                    .filter(name -> !name.isEmpty())
                    .collect(Collectors.toList());
            vo.setCategoryNames(categoryNames);
            if (!categoryIds.isEmpty()) {
                vo.setCategoryId(categoryIds.get(0));
                vo.setCategoryName(categoryNames.get(0));
            }
        }

        // 填充规格信息
        List<MchSpec> specs = specMap.getOrDefault(product.getId(), Collections.emptyList());
        vo.setSpecs(specs.stream()
                .map(this::buildSpecVO)
                .collect(Collectors.toList()));

        // 填充商家信息
        MchInfoDTO mchInfo = mchInfoMap.get(product.getMerchantId());
        if (mchInfo != null) {
            vo.setMerchantName(mchInfo.getMchName());
        }

        return vo;
    }

    /**
     * 构建 SpecVO
     */
    private SpecVO buildSpecVO(MchSpec spec) {
        SpecVO vo = new SpecVO();
        vo.setId(spec.getId());
        vo.setGroupName(spec.getGroupName());
        vo.setSpecName(spec.getSpecName());
        vo.setPrice(spec.getPrice());
        vo.setSpecImage(spec.getSpecImage());
        vo.setStatus(spec.getStatus());
        vo.setIsDefault(spec.getIsDefault());
        vo.setSortOrder(spec.getSortOrder());
        return vo;
    }

    @Override
    public ProductVO getByAuditId(Long auditId) {
        // 查询商品信息
        LambdaQueryWrapper<MchProduct> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MchProduct::getAuditId, auditId);
        MchProduct product = this.baseMapper.selectOne(wrapper);

        if (product == null) {
            throw new BusinessException("PRODUCT_NOT_FOUND", "商品不存在");
        }

        // 批量查询关联数据
        Map<Long, List<Long>> productCategoryMap = batchQueryProductCategories(Collections.singletonList(product.getId()));
        Set<Long> categoryIds = new HashSet<>(productCategoryMap.getOrDefault(product.getId(), Collections.emptyList()));
        Map<Long, MchCategory> categoryMap = batchQueryCategories(categoryIds);

        Map<Long, List<MchSpec>> specMap = batchQuerySpecs(Collections.singletonList(product.getId()));

        Map<Long, MchInfoDTO> mchInfoMap = batchQueryMchInfo(Collections.singletonList(product.getMerchantId()));

        // 构建VO对象
        return buildProductVO(product, productCategoryMap, categoryMap, specMap, mchInfoMap);
    }
}