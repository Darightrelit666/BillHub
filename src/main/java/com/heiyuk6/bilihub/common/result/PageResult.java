package com.heiyuk6.bilihub.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 通用分页结果封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /** 数据列表 */
    private List<T> records;
    /** 总条数 */
    private long total;
    /** 当前页（1-based） */
    private int page;
    /** 每页条数 */
    private int size;
}
