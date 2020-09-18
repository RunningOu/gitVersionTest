package com.IpManage.bo;

import com.IpManage.dataobject.IpmIpInfos;
import com.github.pagehelper.PageInfo;
import com.IpManage.dataobject.IpmIpInfo;
import com.IpManage.dataobject.IpmPlanInfo;

import java.util.List;

/**
 * ouln
 * 2020年6月12日10:10:16
 */
public interface IpmIpInfoBO {
    /**
     * @param ipmIpInfo
     * @return
     * @desc 批量添加
     */
    int insertList(List<IpmIpInfo> ipmIpInfo);
    /**
     * @param ipmIpInfo
     * @return
     * @desc 批量添加
     */
    int insertLists(List<IpmIpInfos> ipmIpInfo);

    /**
     * @param ipmIpInfo
     * @return
     * @desc 查询
     */
    PageInfo<IpmIpInfo> queryList(IpmIpInfo ipmIpInfo, int pageNum, int pageSize);

    /**
     * 规划
     * @param ipmPlanInfo
     * @return
     */
    int planBatch(IpmPlanInfo ipmPlanInfo);

    /**
     * @param ipmIpInfo
     * @return
     * @desc 修改
     */
    int updateIpInfo(IpmIpInfo ipmIpInfo);
    /**
     * 根据id查询IP信息
     * @param id
     * @return
     */
    IpmIpInfo selIpInfoById(String id);
    /**
     * @param ipmIpInfo
     * @return
     * @desc 报表查询
     */
    List<IpmIpInfo> ipinfoExportXls(IpmIpInfo ipmIpInfo);
    /**
     * @param ipmPlanInfo
     * @return
     * @desc ip地址规划列表查询
     */
    PageInfo<IpmPlanInfo> queryIpPlanInfo(IpmPlanInfo ipmPlanInfo, int pageNum, int pageSize);

    /**
     * @param ipmPlanInfo
     * @return
     * @desc 报表查询(规划列表)
     */
    List<IpmPlanInfo> ipPlanInfoExportXls(IpmPlanInfo ipmPlanInfo);

    /**
     * 根据ids查询IP信息
     * @param ids
     * @return
     */
    List<IpmIpInfo> selIpInfoByIds(List<String> ids);
}
