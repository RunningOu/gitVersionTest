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
@ExcelTarget(value = "IpmProjectCountInfo")
public class IpmProjectCountInfo {

    @Excel(name = "项目信息",width = 15)
    private String project;

    @Excel(name = "在线数量",width = 15)
    private int online;

    @Excel(name = "离线数量",width = 15)
    private int unOnline;

    @Excel(name = "IP总数",width = 15)
    private int ipSum;

    @Excel(name = "在线率", width = 15)
    private String rate="0";

    public void setOnline(int online) {
        this.online = online;
        setSum();
    }

    public void setUnOnline(int unOnline) {
        this.unOnline = unOnline;
        setSum();
    }

    public void setSum(){
        this.ipSum=this.online+this.unOnline;
        if(this.ipSum!=0){
            this.rate = String.format("%.2f", (float)this.online/this.ipSum*100)+"%";
        }
    }
}
