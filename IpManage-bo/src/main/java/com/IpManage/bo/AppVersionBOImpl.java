package com.IpManage.bo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.IpManage.dataobject.AppVersionDO;
import com.IpManage.service.AppVersionService;


/**
 * Created by len on 2017/6/5.
 */
@Service
public class AppVersionBOImpl implements AppVersionBO {

    @Reference
    private AppVersionService appVersionService;

    @Override
    public int insertAppVersion(AppVersionDO appVersionDO) {
        return appVersionService.insertAppVersion(appVersionDO);
    }

    @Override
    public AppVersionDO selectAppVersionByAppName(AppVersionDO appVersionDO) {
        return appVersionService.selectAppVersionByAppName(appVersionDO);
    }
}
