package com.IpManage.common.api.cmdb.report;

import lombok.Data;

import java.util.Map;

/**
 * @author yl
 * @Date 2020/4/20 0020 11:05
 */
@Data
public class Resource {

    private String classCode;

    private String className;

    private Integer total;

    private Map<String,Integer> map;
}
