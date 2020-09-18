package com.IpManage.common.api.cmdb;

import lombok.Data;

import java.util.List;

/**
 * @author yl
 * @Date 2020/4/3 0003 13:10
 */
@Data
public class ModelForm {

    private String id;

    private String name;

    private String code;

    private List<ModelAttrs> attrList;
}
