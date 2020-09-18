package com.IpManage.common.api.cmdb;

import lombok.Data;

/**
 * @author yl
 * @Date 2020/4/1 0001 13:53
 */
@Data
public class ConditionsBean {
    /**
     * field : classCode
     * operator : EQ
     * value : sgSjzx
     */
    private String field;
    private String operator;
    private String value;

    public ConditionsBean(String field, String operator, String value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public ConditionsBean() {
    }
}
