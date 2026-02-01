package com.example.merchant.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 商品表
 * @TableName mch_product
 */
@TableName(value ="mch_product")
@Data
public class MchProduct implements Serializable {
    /**
     * 商品ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品卖点
     */
    private String sellingPoints;

    /**
     * 商品详情
     */
    private String description;

    /**
     * 商品主图URL
     */
    private String mainImage;

    /**
     * 图片URL数组
     */
    private Object images;

    /**
     * 关联的商品分类ID
     */
    private Long categoryId;

    /**
     * 所属商家ID
     */
    private Long merchantId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 收益类型:1-按比例,2-固定金额
     */
    private Integer profitType;

    /**
     * 合伙人收益（按比例时为百分比，固定金额时为具体金额）
     */
    private BigDecimal partnerProfit;

    /**
     * 服务商家收益（按比例时为百分比，固定金额时为具体金额）
     */
    private BigDecimal merchantProfit;

    /**
     * 规格类型:1-统一规格,2-多规格
     */
    private Integer specType;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 上架状态:0-未上架,1-已上架
     */
    private Integer shelfStatus;

    /**
     * 关联的审核记录ID（审核商品）
     */
    private Long auditId;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    /**
     * 软删除时间
     */
    private LocalDateTime deleteAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}