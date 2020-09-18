package com.IpManage.service;

import com.IpManage.dataobject.IpmEnumInfo;

import java.util.List;


public interface IpmEnumInfoService {

    /**
     * @param ipmEnumInfo
     * @return
     * @desc 查询
     */
    List<IpmEnumInfo> queryEnumList(IpmEnumInfo ipmEnumInfo, int pageNum, int pageSize);

    /**
     * 修改
     * @param ipmEnumInfo
     * @return
     */
    int updateEnumInfo(IpmEnumInfo ipmEnumInfo);

    /**
     * 新增
     * @param ipmEnumInfo
     * @return
     */
    int insertEnumInfo(IpmEnumInfo ipmEnumInfo);
    /**
     * 删除
     * @param ipmEnumInfo
     * @return
     */
    int deleteEnumInfo(IpmEnumInfo ipmEnumInfo);
}
