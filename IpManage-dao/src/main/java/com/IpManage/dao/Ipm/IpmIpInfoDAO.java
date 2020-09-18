package com.IpManage.dao.Ipm;


import com.IpManage.dataobject.IpmIpInfos;
import com.github.pagehelper.PageInfo;
import com.IpManage.dataobject.IpmIpInfo;
import com.IpManage.dataobject.IpmPlanInfo;

import java.util.List;

/**
 * ip信息
 * ouln
 * 2020年6月12日09:46:39
 */
public interface IpmIpInfoDAO {

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
     * @desc 修改
     */
    int updateIpInfo(IpmIpInfo ipmIpInfo);

    /**
     * @param ipmIpInfo
     * @return
     * @desc 查询
     */
    PageInfo<IpmIpInfo> queryList(IpmIpInfo ipmIpInfo, int pageNum, int pageSize);

    /**
     * @param ipmIpInfo
     * @return
     * @desc 报表查询
     */
    List<IpmIpInfo> ipinfoExportXls(IpmIpInfo ipmIpInfo);

    /**
     * @param ipmPlanInfo
     * @return
     * @desc 报表查询
     */
    List<IpmPlanInfo> ipPlanInfoExportXls(IpmPlanInfo ipmPlanInfo);
    /**
     * 规划信息保存
     * @param ipmPlanInfo
     * @return
     */
    int insertIpPlanInfo(IpmPlanInfo ipmPlanInfo);

    /**
     * 根据id查询IP信息
     * @param id
     * @return
     */
    IpmIpInfo selIpInfoById(String id);

    /**
     * 根据ids查询IP信息
     * @param ids
     * @return
     */
    List<IpmIpInfo> selIpInfoByIds(List<String> ids);
    /**
     * @param ipmPlanInfo
     * @return
     * @desc 规划信息查询
     */
    PageInfo<IpmPlanInfo> queryIpPlanInfo(IpmPlanInfo ipmPlanInfo, int pageNum, int pageSize);
}
