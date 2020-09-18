package com.IpManage.common.api.cmdb.report;

import lombok.Data;

import java.util.List;

/**
 * @author yl
 * @Date 2020/4/20 0020 11:02
 */
@Data
public class ConfigItem {

    private String name;

    private List<Resource> resource;
}
