package com.IpManage.dao.Ipm;


import com.IpManage.dataobject.IpmIpInfos;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.IpManage.dataobject.IpmIpInfo;
import com.IpManage.dataobject.IpmPlanInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangfei on 2017/6/5.
 */
@Repository
public class IpmIpInfoDAOImpl implements IpmIpInfoDAO {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int insertList(List<IpmIpInfo> ipmIpInfo) {
        return sqlSessionTemplate.insert("insertList",ipmIpInfo);
    }

    @Override
    public int insertLists(List<IpmIpInfos> ipmIpInfo) {
        return sqlSessionTemplate.insert("insertLists",ipmIpInfo);
    }

    @Override
    public int updateIpInfo(IpmIpInfo ipmIpInfo) {
        return sqlSessionTemplate.update("updateIpInfo",ipmIpInfo);
    }

    @Override
    public PageInfo<IpmIpInfo> queryList(IpmIpInfo ipmIpInfo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<IpmIpInfo> list = sqlSessionTemplate.selectList("queryList",ipmIpInfo);
        PageInfo<IpmIpInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<IpmIpInfo> ipinfoExportXls(IpmIpInfo ipmIpInfo) {
        return sqlSessionTemplate.selectList("ipinfoExportXls",ipmIpInfo);
    }

    @Override
    public List<IpmPlanInfo> ipPlanInfoExportXls(IpmPlanInfo ipmPlanInfo) {
        return sqlSessionTemplate.selectList("ipPlanInfoExportXls",ipmPlanInfo);
    }

    @Override
    public int insertIpPlanInfo(IpmPlanInfo ipmPlanInfo) {
        return sqlSessionTemplate.insert("insertIpPlanInfo",ipmPlanInfo);
    }

    @Override
    public IpmIpInfo selIpInfoById(String id) {
        return sqlSessionTemplate.selectOne("selIpInfoById",id);
    }

    @Override
    public List<IpmIpInfo> selIpInfoByIds(List<String> ids) {
        return sqlSessionTemplate.selectList("selIpInfoByIds",ids);
    }

    @Override
    public PageInfo<IpmPlanInfo> queryIpPlanInfo(IpmPlanInfo ipmPlanInfo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<IpmPlanInfo> list = sqlSessionTemplate.selectList("queryIpPlanInfo",ipmPlanInfo);
        PageInfo<IpmPlanInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
