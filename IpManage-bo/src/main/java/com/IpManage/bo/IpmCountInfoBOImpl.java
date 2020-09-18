package com.IpManage.bo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.IpManage.service.IpmCountInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IpmCountInfoBOImpl implements IpmCountInfoBO{

    @Reference
    IpmCountInfoService ipmCountInfoService;

    @Override
    public List<Map> countByProject() {
        return ipmCountInfoService.countByProject();
    }

    @Override
    public List<Map> countByAffiliatedUnit() {
        return ipmCountInfoService.countByAffiliatedUnit();
    }
}
