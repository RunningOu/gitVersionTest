package com.IpManage.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * IP统计记录表
 * ouln
 * 2020年6月11日19:15:47
 */
@Data
public class IpmCountInfo {

    /**
     * 编号
     */
    private String id;
    /**
     * 记录数据类型
     */
    private String dataType;
    /**
     * 统计时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 数据集
     */
    private String data;
}
