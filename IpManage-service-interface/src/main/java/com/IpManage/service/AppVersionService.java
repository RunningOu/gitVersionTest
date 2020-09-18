package com.IpManage.service;

import com.IpManage.dataobject.AppVersionDO;


public interface AppVersionService {

    /**
     * @param appVersionDO
     * @return
     * @desc 插入升级app
     */
    public int insertAppVersion(AppVersionDO appVersionDO);


    /**
     * @param appVersionDO
     * @return
     * @desc 根据appname查下最新app信息
     */
    public AppVersionDO selectAppVersionByAppName(AppVersionDO appVersionDO);
}
