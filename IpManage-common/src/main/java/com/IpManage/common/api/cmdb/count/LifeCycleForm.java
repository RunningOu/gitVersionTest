package com.IpManage.common.api.cmdb.count;

import lombok.Data;

import java.util.Map;

/**
 * @author yl
 * @Date 2020/4/17 0017 13:09
 */
@Data
public class LifeCycleForm {

    private String classCode;

    private String name;

    private Map<String,Integer> opt;

}
