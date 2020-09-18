package com.IpManage.common.api.cmdb.count;

import lombok.Data;

/**
 * @author yl
 * @Date 2020/4/17 0017 10:33
 */
@Data
public class MaintenanceForm {
    private String classCode;
    private String name;
    /**
     * 在保
     */
    private Integer online;
    /**
     * 过保
     */
    private Integer offline;

    private Integer unknown;
}
