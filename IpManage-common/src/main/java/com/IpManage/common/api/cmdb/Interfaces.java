package com.IpManage.common.api.cmdb;

import lombok.Data;

import java.util.List;

/**
 * @author yl
 * @Date 2020/4/3 0003 12:57
 */
@Data
public class Interfaces {


    /**
     * name : 上下架通用字段
     * id : 5e142ae56e3e26bc3507acc8
     * attributes : null
     * builtin : false
     * code : QGQuODnl6C
     * tenantId : e10adc3949ba59abbe56e057f20f88dd
     * attrCodes : ["ip","jfwz","uwei","ssjg","lxr","Model","serialNumber","cjgs","ZDZCBM","xmmc","hostVendor","SSXT","SBLY","JRWL","JSYT"]
     * classList : [{"builtin":false,"code":"servermanage"},{"builtin":false,"code":"networkDevice"},{"builtin":false,"code":"cssb"},{"builtin":false,"code":"aqsb"},{"builtin":false,"code":"Firewall"},{"builtin":false,"code":"Router"},{"builtin":false,"code":"Switch"}]
     */
    private String name;
    private String id;
    private String code;
    private List<String> attrCodes;
}
