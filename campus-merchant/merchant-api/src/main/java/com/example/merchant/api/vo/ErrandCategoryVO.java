package com.example.merchant.api.vo;

import com.example.merchant.api.pojo.CommissionInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ErrandCategoryVO", description = "服务分类返回对象VO")
public class ErrandCategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "父级ID（0为顶级分类）", example = "0")
    private Long parentId;

    @Schema(description = "分类层级：1-一级分类，2-二级分类", example = "1")
    private Integer level;

    @Schema(description = "分类名称", example = "跑腿代购")
    private String categoryName;

    @Schema(description = "排序（值越小越靠前显示）", example = "10")
    private Integer sortOrder;

    @Schema(description = "是否允许线下交易：0-否，1-是", example = "1")
    private Integer allowOfflineTrade;

    @Schema(description = "状态：0-禁用，1-启用", example = "1")
    private Integer status;

    @Schema(description = "创建时间", example = "2026-01-27T10:00:00")
    private LocalDateTime createAt;

    @Schema(description = "更新时间", example = "2026-01-27T10:00:00")
    private LocalDateTime updateAt;

    @Schema(description = "分佣配置信息")
    private CommissionInfo commissionInfo;

    @Schema(description = "父分类名称", example = "跑腿")
    private String parentName;

    @Schema(description = "层级路径", example = "跑腿 > 代取快递")
    private String levelPath;

    @Schema(description = "子分类列表")
    private List<ErrandCategoryVO> children;
}