package com.IpManage.common.api.cmdb.report;

import lombok.Getter;

/**
 * @author yl
 * @Date 2020/4/20 0020 11:45
 */
public enum TreeEnum {
    /**
     * cmdb
     */
    computer("计算机设备",new String[]{"fwqzc","ccsb"}),

    terminal("终端设备",new String[]{"yzmzd","jsjzd","ZNJWZD","display","ydjz","dyj","otherdevice"}),

    network("网络设备",new String[]{"lyq","jhj"}),

    link("链路信息",new String[]{"llhlxx"}),

    engineroom("机房信息",new String[]{"wajf","wajg"});

    @Getter
    private String name;

    @Getter
    private String[] codeList;

    TreeEnum(String name,String[] codeList){
        this.name = name;
        this.codeList = codeList;
    }
}
