package com.example.forum.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 分页参数与结果封装工具类
 */
public class PageUtil {

    /**
     * 创建分页请求Page对象
     * @param pageNum 当前页码，从1开始
     * @param pageSize 每页大小
     * @return Page对象
     */
    public static <T> Page<T> createPage(int pageNum, int pageSize) {
        // pageNum和pageSize做简单的边界处理也可以在这里统一实现
        int pNum = Math.max(pageNum, 1);
        int pSize = pageSize > 0 ? pageSize : 10;
        return new Page<>(pNum, pSize);
    }

    /**
     * 将IPage接口分页结果封装成自定义的分页结果对象
     * @param iPage MybatisPlus分页查询返回的IPage对象
     * @return 自定义分页结果
     */
    public static <T> PageResult<T> toPageResult(IPage<T> iPage) {
        return new PageResult<>(
                iPage.getCurrent(),
                iPage.getSize(),
                iPage.getTotal(),
                iPage.getRecords()
        );
    }

    /**
     * 分页结果封装类
     */
    public static class PageResult<T> {
        private long current; // 当前页
        private long pageSize; // 每页大小
        private long total; // 总记录数
        private List<T> records; // 当前页结果列表

        public PageResult(long current, long pageSize, long total, List<T> records) {
            this.current = current;
            this.pageSize = pageSize;
            this.total = total;
            this.records = records;
        }

        public long getCurrent() {
            return current;
        }

        public long getPageSize() {
            return pageSize;
        }

        public long getTotal() {
            return total;
        }

        public List<T> getRecords() {
            return records;
        }
    }
}