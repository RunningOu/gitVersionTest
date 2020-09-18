package com.IpManage.bo;

import com.IpManage.dataobject.IpmIpInfos;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.IpManage.common.util.IpManageUtils;
import com.IpManage.common.util.ListUtil;
import com.IpManage.dataobject.IpmIpInfo;
import com.IpManage.dataobject.IpmPlanInfo;
import com.IpManage.service.IpmIpInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IpmIpInfoBOImpl implements IpmIpInfoBO{

    @Reference
    IpmIpInfoService ipmIpInfoService;

    @Override
    public PageInfo<IpmIpInfo> queryList(IpmIpInfo ipmIpInfo, int pageNum, int pageSize) {
        return ipmIpInfoService.queryList(ipmIpInfo,pageNum,pageSize);
    }

    @Override
    public int planBatch(IpmPlanInfo ipmPlanInfo) {
        List<IpmIpInfo> ipmIpInfos = new ArrayList<>();
        List<String> ips = IpManageUtils.getIpArea(ipmPlanInfo.getStartIp(),ipmPlanInfo.getEndIp());
        for (String ip:ips
             ) {
            IpmIpInfo ipmIpInfo = new IpmIpInfo();
            ipmIpInfo.setIp(ip);
            ipmIpInfo.setAffiliatedUnit(ipmPlanInfo.getAffiliatedUnit());
            ipmIpInfo.setDomain(ipmPlanInfo.getDomain());
            ipmIpInfo.setPlanner(ipmPlanInfo.getPlanner());
            ipmIpInfo.setNetworkDesc(ipmPlanInfo.getNetworkDesc());
            ipmIpInfo.setCreateTime(ipmPlanInfo.getCreateTime());
            ipmIpInfo.setUseStatus("1");
            ipmIpInfo.setOnlineStatus("1");
            ipmIpInfos.add(ipmIpInfo);

        }
        try {
            List<List<IpmIpInfo> > lists = ListUtil.splitList(ipmIpInfos,50);
            for (List<IpmIpInfo> infos:lists
                 ) {
                List<IpmIpInfo> insertList = new ArrayList<>();
                insertList.addAll(infos);
                ipmIpInfoService.insertList(insertList);
                Thread.sleep(100);
            }
            //持久化规划信息
            ipmIpInfoService.insertIpPlanInfo(ipmPlanInfo);
            return ipmIpInfos.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateIpInfo(IpmIpInfo ipmIpInfo) {
        return ipmIpInfoService.updateIpInfo(ipmIpInfo);
    }

    @Override
    public IpmIpInfo selIpInfoById(String id) {
        return ipmIpInfoService.selIpInfoById(id);
    }

    @Override
    public List<IpmIpInfo> ipinfoExportXls(IpmIpInfo ipmIpInfo) {
        return ipmIpInfoService.ipinfoExportXls(ipmIpInfo);
    }

    @Override
    public PageInfo<IpmPlanInfo> queryIpPlanInfo(IpmPlanInfo ipmPlanInfo, int pageNum, int pageSize) {
        return ipmIpInfoService.queryIpPlanInfo(ipmPlanInfo,pageNum,pageSize);
    }

    @Override
    public List<IpmPlanInfo> ipPlanInfoExportXls(IpmPlanInfo ipmPlanInfo) {
        return ipmIpInfoService.ipPlanInfoExportXls(ipmPlanInfo);
    }

    @Override
    public List<IpmIpInfo> selIpInfoByIds(List<String> ids) {
        return ipmIpInfoService.selIpInfoByIds(ids);
    }

    @Override
    public int insertList(List<IpmIpInfo> ipmIpInfo) {
        return ipmIpInfoService.insertList(ipmIpInfo);
    }

    @Override
    public int insertLists(List<IpmIpInfos> ipmIpInfo) {
        return ipmIpInfoService.insertLists(ipmIpInfo);
    }
}
