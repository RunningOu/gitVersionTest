package com.IpManage.service;


import java.util.List;
import java.util.Map;

/**
 * ip统计信息
 * ouln
 * 2020年6月16日10:24:21
 */
public interface IpmCountInfoService {
    /**
     * @return
     * @desc 按照项目统计
     */
    List<Map> countByProject();

    /**
     * @return
     * @desc 按照部门统计
     */
    List<Map> countByAffiliatedUnit();

}
