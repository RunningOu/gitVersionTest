package com.IpManage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.IpManage.dao.Ipm.IpmEnumInfoDAO;
import com.IpManage.dataobject.IpmEnumInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class IpmEnumInfoServiceImpl implements IpmEnumInfoService {

    @Autowired
    private IpmEnumInfoDAO ipmEnumInfoDAO;

    @Override
    public List<IpmEnumInfo> queryEnumList(IpmEnumInfo ipmEnumInfo, int pageNum, int pageSize) {
        return ipmEnumInfoDAO.queryEnumList(ipmEnumInfo,pageNum,pageSize);
    }

    @Override
    public int updateEnumInfo(IpmEnumInfo ipmEnumInfo) {
        return ipmEnumInfoDAO.updateEnumInfo(ipmEnumInfo);
    }

    @Override
    public int insertEnumInfo(IpmEnumInfo ipmEnumInfo) {
        return ipmEnumInfoDAO.insertEnumInfo(ipmEnumInfo);
    }

    @Override
    public int deleteEnumInfo(IpmEnumInfo ipmEnumInfo) {
        return ipmEnumInfoDAO.deleteEnumInfo(ipmEnumInfo);
    }
}
