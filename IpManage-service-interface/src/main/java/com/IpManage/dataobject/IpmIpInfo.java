package com.IpManage.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.util.Date;

/**
 * IP信息表
 * ouln
 * 2020年6月11日18:52:06
 */
@Data
@ExcelTarget(value = "IpmIpInfo")
public class IpmIpInfo {
    /**
     * 编号
     */
    private String id;
    /**
     * IP地址
     */
    @Excel(name = "IP地址",width = 15)
    private String ip;
    /**
     * 网络域
     */
    @Excel(name = "网络域",width = 15)
    private String domain;
    /**
     * 项目信息
     */
    @Excel(name = "项目信息",width = 15)
    private String project;
    /**
     * 网段描述
     */
    @Excel(name = "网段描述",width = 15)
    private String networkDesc;
    /**
     * 应用单位
     */
    @Excel(name = "应用单位",width = 15)
    private String affiliatedUnit;
    /**
     * 厂商
     */
    @Excel(name = "厂商",width = 15)
    private String manufactor;
    /**
     * 规划人
     */
    @Excel(name = "规划人",width = 15)
    private String planner;
    /**
     * 负责人
     */
    @Excel(name = "负责人",width = 15)
    private String projectManager;
    /**
     * 申请人
     */
    @Excel(name = "申请人",width = 15)
    private String applicant;
    /**
     * 分配状态
     */
    @Excel(name = "分配状态",width = 15)
    private String useStatus;
    /**
     * 在线状态
     */
    @Excel(name = "在线状态",width = 15)
    private String onlineStatus;
    /**
     * 入网时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "入网时间",width = 15)
    private String createTime;
    /**
     * 上一次在线时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上一次在线时间",width = 15)
    private String lastOnlineTime;
    /**
     * 检测时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "检测时间",width = 15)
    private String testTime;

    //以下作为查询使用

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTimeStart;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTimeEnd;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private String lastOnlineTimeStart;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private String lastOnlineTimeEnd;
}
