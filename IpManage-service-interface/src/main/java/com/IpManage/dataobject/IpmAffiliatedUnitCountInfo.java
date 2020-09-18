package com.IpManage.dataobject;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * 项目统计IP地址在线情况信息
 * ouln
 * 2020年6月16日17:41:36
 */
@Data
@ExcelTarget(value = "IpmAffiliatedUnitCountInfo")
public class IpmAffiliatedUnitCountInfo {

    @Excel(name = "应用单位",width = 15)
    private String affiliatedUnit;

    @Excel(name = "在线数量",width = 15)
    private int online;

    @Excel(name = "离线数量",width = 15)
    private int unOnline;

    @Excel(name = "已分配",width = 15)
    private int distribution;

    @Excel(name = "未分配",width = 15)
    private int unDistribution;

    @Excel(name = "IP总数",width = 15)
    private int ipSum;

    @Excel(name = "在线率(在线/已分配)",width = 25)
    private String rate="0";
    public void setOnline(int online) {
        this.online = online;
        setSum();
    }

    public void setUnOnline(int unOnline) {
        this.unOnline = unOnline;
        setSum();
    }

    public void setDistribution(int distribution) {
        this.distribution = distribution;
        setSum();
    }

    public void setUnDistribution(int unDistribution) {
        this.unDistribution = unDistribution;
        setSum();
    }

    public void setSum(){
        this.ipSum=this.distribution+this.unDistribution;
        if(this.distribution>0){
            this.rate = String.format("%.2f", (float)this.online/this.distribution*100)+"%";
        }

    }
}
