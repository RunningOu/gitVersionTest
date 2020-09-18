package com.IpManage.common.api.cmdb.count;

import lombok.Data;

/**
 * @author yl
 * @Date 2020/4/17 0017 14:30
 */
@Data
public class TerminalForm {

    private String code;

    private String name;
    private Integer unused;
    private Integer recycled;
    private Integer borrow;
    private Integer scrapped;
    private Integer nbf;
    private Integer inactive;
    private Integer used;
}
