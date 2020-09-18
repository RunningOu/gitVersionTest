package com.IpManage.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.IpManage.dao.appVersion.AppVersionDAO;
import com.IpManage.dataobject.AppVersionDO;

/**
 * Created by yangfei on 2017/6/5.
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {

    @Autowired
    private AppVersionDAO appVersionDAO;

    @Override
    public int insertAppVersion(AppVersionDO appVersionDO) {
        return appVersionDAO.insertAppVersion(appVersionDO);
    }

    @Override
    public AppVersionDO selectAppVersionByAppName(AppVersionDO appVersionDO) {
        return appVersionDAO.selectAppVersionByAppName(appVersionDO);
    }
}
