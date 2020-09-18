package com.IpManage.dao.appVersion;


import com.IpManage.dataobject.AppVersionDO;

/**
 * Created by yangfei on 2017/6/5.
 */
public interface AppVersionDAO {

    /**
     * param appVersionDO
     * return
     * desc 插入升级app
     */
    public int insertAppVersion(AppVersionDO appVersionDO);


    /**
     * param appVersionDO
     * return
     * desc 根据appname查下最新app信息
     */
    public AppVersionDO selectAppVersionByAppName(AppVersionDO appVersionDO);
}
