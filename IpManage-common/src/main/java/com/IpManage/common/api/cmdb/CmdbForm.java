package com.IpManage.common.api.cmdb;

import lombok.Data;

import java.util.List;

/**
 * @author yl
 * @Date 2020/4/1 0001 13:49
 */
@Data
public class CmdbForm{
    private boolean needCount=true;
    private int pageNum=0;
    private int pageSize=10;
    private String className;
    private String[] orderFields;
    private List<String> requiredFields;
    private List<ConditionsBean> conditions;
}
