package com.IpManage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.IpManage.dao.Ipm.IpmCountInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class IpmCountInfoServiceImpl implements IpmCountInfoService {

    @Autowired
    private IpmCountInfoDAO ipmCountInfoDAO;

    @Override
    public List<Map> countByProject() {
        return ipmCountInfoDAO.countByProject();
    }

    @Override
    public List<Map> countByAffiliatedUnit() {
        return ipmCountInfoDAO.countByAffiliatedUnit();
    }
}
