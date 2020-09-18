package com.IpManage.dao.Ipm;


import com.github.pagehelper.PageHelper;
import com.IpManage.dataobject.IpmEnumInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangfei on 2017/6/5.
 */
@Repository
public class IpmEnumInfoDAOImpl implements IpmEnumInfoDAO {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<IpmEnumInfo> queryEnumList(IpmEnumInfo ipmEnumInfo, int pageNum, int pageSize) {
        if(pageNum!=0||pageSize!=0){
            PageHelper.startPage(pageNum,pageSize);
        }
        return sqlSessionTemplate.selectList("queryEnumList",ipmEnumInfo);
    }

    @Override
    public int updateEnumInfo(IpmEnumInfo ipmEnumInfo) {
        return sqlSessionTemplate.update("updateEnumInfo",ipmEnumInfo);
    }

    @Override
    public int insertEnumInfo(IpmEnumInfo ipmEnumInfo) {
        return sqlSessionTemplate.insert("insertEnumInfo",ipmEnumInfo);
    }

    @Override
    public int deleteEnumInfo(IpmEnumInfo ipmEnumInfo) {
        return sqlSessionTemplate.delete("deleteEnumInfo",ipmEnumInfo);
    }
}
