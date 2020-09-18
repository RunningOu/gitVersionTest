package com.IpManage.common.api.cmdb;

import lombok.Data;

import java.util.Map;

/**
 * @author yl
 * @Date 2020/4/3 0003 13:00
 */
@Data
public class ModelAttrs {

    /**
     * params : {"minLength":0,"maxLength":100,"validation":"IP"}
     * name : IP地址
     * id : 5cb3fb6b6e3e26eb9829bd71
     * type : singleRowText
     * defaultValue : null
     * builtin : true
     * unit : null
     * required : false
     * code : ip
     * tenantId : e10adc3949ba59abbe56e057f20f88dd
     * resOwners : ["CMDB","AUTOMATION","NETWORK","MONITOR"]
     */

    private Map<String, Object> params;
    private String type;
    private String name;
    private String id;
    private String code;
}
