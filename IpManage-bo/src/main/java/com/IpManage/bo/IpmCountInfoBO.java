package com.IpManage.bo;

import java.util.List;
import java.util.Map;

/**
 * ouln
 * 2020年6月16日10:37:01
 */
public interface IpmCountInfoBO {
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
