package com.IpManage.common.api.cmdb.count;

import lombok.Data;

/**
 * @author yl
 * @Date 2020/4/17 0017 9:36
 */
@Data
public class CiForm {

    /**
     * 属性名称
     */
    private String name;
    /**
     * 属性编码
     */
    private String classCode;

    /**
     * 总数
     */
    private Integer count;
}
