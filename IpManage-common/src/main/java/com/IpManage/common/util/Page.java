package com.IpManage.common.util;

import lombok.Data;

/**
 * 分页父类
 * ouln
 * 2019-6-17 20:17:25
 */
@Data
public class Page<T> {
    private T model;
    private int pageNum;
    private int pageSize;
}