package com.example.common.mybatis.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页查询VO，与PageUtil.PageResult保持兼容
 */
@Schema(description = "分页查询结果")
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResult<T> {
    @Schema(description = "总条数", example = "100")
    private Long total;       // 总条数

    @Schema(description = "当前页", example = "1")
    private Long current;     // 当前页，从1开始

    @Schema(description = "每页条数", example = "10")
    private Long pageSize;    // 每页条数

    @Schema(description = "总页数", example = "10")
    private Long pages;       // 总页数

    @Schema(description = "数据列表")
    private List<T> list;     // 数据列表（兼容原records字段）

    public PageResult() {}

    public PageResult(long current, long pageSize, long total, List<T> list) {
        this.current = current;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        this.pages = total > 0 ? (total + pageSize - 1) / pageSize : 0;
    }

    /**
     * 从MyBatis-Plus IPage快速转换，推荐Service层统一调用PageUtil.toPageResult(ipage)
     */
    public static <T> PageResult<T> of(IPage<T> iPage) {
        return new PageResult<>(
                iPage.getCurrent(),
                iPage.getSize(),
                iPage.getTotal(),
                iPage.getRecords()
        );
    }

    // 计算辅助方法
    public boolean hasNext() {
        return current < pages;
    }

    public boolean hasPrevious() {
        return current > 1;
    }
}