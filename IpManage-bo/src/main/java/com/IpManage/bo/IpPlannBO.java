package com.IpManage.bo;


import com.IpManage.common.api.cmdb.CmdbForm;
import com.IpManage.common.api.cmdb.PingVO;

import java.util.List;
import java.util.Map;

/**
 * @author yl
 * @Date 2020/5/8 0008 14:03
 */
public interface IpPlannBO {

    /**
     * 新增ip规划点位
     * @param param
     * @return
     */
    String save(Map<String, Object> param) throws Exception;

    /**
     * 查询资产
     * @param cmdbForm
     * @return
     */
    String select(CmdbForm cmdbForm);

    /**
     * 更新资源
     * @param params
     * @return
     */
    String update(Map<String, Object> params);
    /**
     * 查询编码 属性
     * @param code
     * @return
     */
    String getCode(String code);

    /**
     * 批量添加或修改
     * @param
     * @return
     */
    String batchSave(List<Map<String, Object>> param);

    /**
     * ping 检测在线情况
     * @param pingList
     */
    String pingMonitor(List<PingVO> pingList);

}
