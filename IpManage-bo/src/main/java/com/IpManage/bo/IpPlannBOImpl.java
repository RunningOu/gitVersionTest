package com.IpManage.bo;

import com.google.gson.Gson;
import com.IpManage.common.api.Cmdb;
import com.IpManage.common.api.cmdb.CmdbForm;
import com.IpManage.common.api.cmdb.PingVO;
import com.IpManage.common.util.IpManageUtils;
import io.parallec.core.ParallecResponseHandler;
import io.parallec.core.ParallelClient;
import io.parallec.core.ParallelTask;
import io.parallec.core.ResponseOnSingleTask;
import io.parallec.core.bean.ping.PingMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yl
 * @Date 2020/5/8 0008 14:03
 */
@Service
@Slf4j
public class IpPlannBOImpl implements IpPlannBO{

    private Gson gson = new Gson();

    @Override
    public String save(Map<String,Object> param) throws Exception {
        String startip = param.get("startIp").toString();
        String endip = param.get("endIp").toString();
        List<String> ipArea = IpManageUtils.getIpArea(startip, endip);
        List<Map<Object,Object>> params = new ArrayList<>();
        for(String  ip: ipArea){
            Map<Object,Object> map = new LinkedHashMap<>();
            map.put("ip",ip);
            map.put("name",ip);
            map.put("classCode","IPAddress");
            map.put("sources",new String[]{"user"});
            params.add(map);
        }
        log.debug("batch_save =====> msg:{}",gson.toJson(params));
        String result = Cmdb.batchSave(gson.toJson(params));
        return result;
    }

    @Override
    public String select(CmdbForm cmdbForm) {
        try {
            return Cmdb.query(cmdbForm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
    }

    @Override
    public String update(Map<String, Object> params) {
        try {
            return Cmdb.update(gson.toJson(params));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    @Override
    public String getCode(String code) {
        try {
            return Cmdb.getAttrs(code);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
    }

    @Override
    public String batchSave(List<Map<String,Object>> params) {
        try {
            log.debug("batchSave====>>>>:{}",gson.toJson(params));
            return Cmdb.batchSave(gson.toJson(params));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }
    @Override
    public String pingMonitor(List<PingVO> pingList) {
        List<String> ipList = new ArrayList<>();
        for (int i=0;i<pingList.size();i++){
            ipList.add(pingList.get(i).getIp());
        }
        List<Map<Object,Object>> params = new ArrayList<>();
        try {
            ParallelClient pc = new ParallelClient();
            ParallelTask task = pc.preparePing().setConcurrency(1500).setTargetHostsFromList(ipList)
                    .setPingMode(PingMode.INET_ADDRESS_REACHABLE_NEED_ROOT)
                    .setPingNumRetries(3)
                    .setPingTimeoutMillis(500)
                    .execute(new ParallecResponseHandler() {
                        @Override
                        public void onCompleted(ResponseOnSingleTask res,
                                                Map<String, Object> responseContext) {
                            String unCode = "UNREACHABLE";
                            String liveCode= "LIVE";
                            if(unCode.equals(res.getStatusCode())){
                                for (int k=0;k<pingList.size();k++) {
                                    while (res.getHost().equals(pingList.get(k).getIp())) {
                                        Map<Object, Object> map = new LinkedHashMap<>();
                                        map.put("id", pingList.get(k).getId());
                                        map.put("isonline", 0);
                                        map.put("jcsj", System.currentTimeMillis());
                                        map.put("classCode", "IPAddress");
                                        map.put("sources", new String[]{"user"});
                                        params.add(map);
                                        break;
                                    }
                                }
                            }else if(liveCode.equals(res.getStatusCode())){
                                for (int k=0;k<pingList.size();k++) {
                                    while (res.getHost().equals(pingList.get(k).getIp())) {
                                        Map<Object, Object> map = new LinkedHashMap<>();
                                        map.put("id", pingList.get(k).getId());
                                        map.put("isonline", 1);
                                        map.put("jcsj", System.currentTimeMillis());
                                        map.put("classCode", "IPAddress");
                                        map.put("sources", new String[]{"user"});
                                        params.add(map);
                                        break;
                                    }
                                }
                            }
                        }
                    });
            Map<String, Integer> resultCountSummary = task.getAggregateResultCountSummary();
            //释放资源
            //pc.releaseExternalResources();
            log.debug("诊断结果====>>>>>:{}",resultCountSummary);
            String  result = Cmdb.batchSave(gson.toJson(params));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ping update fail=====>>>>:{}",e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> ipArea = IpManageUtils.getIpArea("192.168.0.70", "192.168.0.70");
        System.out.println(ipArea);
    }
}
