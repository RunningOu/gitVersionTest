package com.IpManage.bo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.IpManage.dataobject.IpmEnumInfo;
import com.IpManage.service.IpmEnumInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpmEnumInfoBOImpl implements IpmEnumInfoBO{

    @Reference
    IpmEnumInfoService ipmEnumInfoService;

    @Override
    public List<IpmEnumInfo> queryEnumList(IpmEnumInfo ipmEnumInfo, int pageNum, int pageSize) {
        return ipmEnumInfoService.queryEnumList(ipmEnumInfo,pageNum,pageSize);
    }

    @Override
    public int updateEnumInfo(IpmEnumInfo ipmEnumInfo) {
        return ipmEnumInfoService.updateEnumInfo(ipmEnumInfo);
    }

    @Override
    public int insertEnumInfo(IpmEnumInfo ipmEnumInfo) {
        return ipmEnumInfoService.insertEnumInfo(ipmEnumInfo);
    }

    @Override
    public int deleteEnumInfo(IpmEnumInfo ipmEnumInfo) {
        return ipmEnumInfoService.deleteEnumInfo(ipmEnumInfo);
    }
}
