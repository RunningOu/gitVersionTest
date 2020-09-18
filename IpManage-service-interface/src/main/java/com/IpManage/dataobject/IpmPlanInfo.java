package com.IpManage.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * IP规划信息记录表
 * ouln
 * 2020年6月11日18:52:06
 */
@Data
public class IpmPlanInfo {

    /**
     * 主键，编号
     */
    private String id;
    /**
     * 开始IP
     */
    @Excel(name = "开始IP",width = 15)
    private String startIp;
    /**
     * 结束IP
     */
    @Excel(name = "结束IP",width = 15)
    private String endIp;
    /**
     * 应用单位 ：如派出所
     */
    @Excel(name = "应用单位",width = 15)
    private String affiliatedUnit;
    /**
     * 网络域
     */
    @Excel(name = "网络域",width = 15)
    private String domain;
    /**
     * 规划人
     */
    @Excel(name = "规划人",width = 15)
    private String planner;
    /**
     * 网段描述
     */
    @Excel(name = "网段描述",width = 15)
    private String networkDesc;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间",width = 15)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private String createTime;

    //用于查询

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

}
