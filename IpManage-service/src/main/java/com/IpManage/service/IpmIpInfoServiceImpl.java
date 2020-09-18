package com.IpManage.service;

import com.IpManage.dataobject.IpmIpInfos;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.IpManage.dao.Ipm.IpmIpInfoDAO;
import com.IpManage.dataobject.IpmIpInfo;
import com.IpManage.dataobject.IpmPlanInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class IpmIpInfoServiceImpl implements IpmIpInfoService {

    @Autowired
    private IpmIpInfoDAO ipmIpInfoDAO;

    @Override
    public int insertList(List<IpmIpInfo> ipmIpInfo) {
        return ipmIpInfoDAO.insertList(ipmIpInfo);
    }

    @Override
    public int insertLists(List<IpmIpInfos> ipmIpInfo) {
        return ipmIpInfoDAO.insertLists(ipmIpInfo);
    }

    @Override
    public PageInfo<IpmIpInfo> queryList(IpmIpInfo ipmIpInfo, int pageNum, int pageSize) {
        return ipmIpInfoDAO.queryList(ipmIpInfo,pageNum,pageSize);
    }

    @Override
    public int insertIpPlanInfo(IpmPlanInfo ipmPlanInfo) {
        return ipmIpInfoDAO.insertIpPlanInfo(ipmPlanInfo);
    }

    @Override
    public PageInfo<IpmPlanInfo> queryIpPlanInfo(IpmPlanInfo ipmPlanInfo, int pageNum, int pageSize) {
        return ipmIpInfoDAO.queryIpPlanInfo(ipmPlanInfo,pageNum,pageSize);
    }

    @Override
    public int updateIpInfo(IpmIpInfo ipmIpInfo) {
        return ipmIpInfoDAO.updateIpInfo(ipmIpInfo);
    }

    @Override
    public IpmIpInfo selIpInfoById(String id) {
        return ipmIpInfoDAO.selIpInfoById(id);
    }

    @Override
    public List<IpmIpInfo> ipinfoExportXls(IpmIpInfo ipmIpInfo) {
        return ipmIpInfoDAO.ipinfoExportXls(ipmIpInfo);
    }

    @Override
    public List<IpmPlanInfo> ipPlanInfoExportXls(IpmPlanInfo ipmPlanInfo) {
        return ipmIpInfoDAO.ipPlanInfoExportXls(ipmPlanInfo);
    }

    @Override
    public List<IpmIpInfo> selIpInfoByIds(List<String> ids) {
        return ipmIpInfoDAO.selIpInfoByIds(ids);
    }
}
