package com.IpManage.dataobject;

import lombok.Data;

/**
 * 字典表
 * ouln
 * 2020年6月11日19:18:09
 */
@Data
public class IpmEnumInfo {
    /**
     * 编号
     */
    private String id;
    /**
     * 所属字段的父级
     */
    private String parentCode;
    /**
     * 字典编码
     */
    private String enumCode;
    /**
     * 字典名称
     */
    private String enumName;
}
