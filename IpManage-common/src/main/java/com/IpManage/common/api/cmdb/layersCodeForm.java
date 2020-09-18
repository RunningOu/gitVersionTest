package com.IpManage.common.api.cmdb;

import lombok.Data;

/**
 * @author yl
 * @Date 2020/4/17 0017 9:33
 */
@Data
public class layersCodeForm {
    /**
     * code : Application
     * inline : false
     * builtin : true
     * name : 应用
     * tenantId : e10adc3949ba59abbe56e057f20f88dd
     * id : 5cb3fb6c6e3e26eb9829bec5
     * resOwners : ["MONITOR","CMDB","AUTOMATION","NETWORK"]
     * layerCode : Business Application
     */
    private String code;
    private boolean inline;
    private boolean builtin;
    private String name;
    private String id;
    private String layerCode;
}
