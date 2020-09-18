package com.IpManage.dao.appVersion;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.IpManage.dataobject.AppVersionDO;

/**
 * Created by yangfei on 2017/6/5.
 */
@Repository
public class AppVersionDAOImpl implements AppVersionDAO {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int insertAppVersion(AppVersionDO appVersionDO) {
        return sqlSessionTemplate.insert("insertAppVersion", appVersionDO);
    }

    @Override
    public AppVersionDO selectAppVersionByAppName(AppVersionDO appVersionDO) {
        return sqlSessionTemplate.selectOne("selectAppVersionByAppName", appVersionDO);
    }
}
