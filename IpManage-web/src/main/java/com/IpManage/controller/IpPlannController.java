package com.IpManage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.IpManage.bo.IpPlannBO;
import com.IpManage.common.api.Cmdb;
import com.IpManage.common.api.cmdb.CmdbForm;
import com.IpManage.common.api.cmdb.ConditionsBean;
import com.IpManage.common.api.cmdb.PingVO;
import com.IpManage.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yl
 * @Date 2020/5/8 0008 14:02
 */
@RestController
@CrossOrigin
@RequestMapping("/ip/plann")
public class IpPlannController {

    @Autowired
    private IpPlannBO ipPlannBO;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Map<String,Object> params) throws Exception {
        return ResponseEntity.ok(JSONObject.parse(ipPlannBO.save(params)));
    }

    @PostMapping("/select")
    public ResponseEntity select(@RequestBody CmdbForm cmdbForm){
        return ResponseEntity.ok(JSONObject.parse(ipPlannBO.select(cmdbForm)));
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Map<String,Object> params){
        return ResponseEntity.ok(JSONObject.parse(ipPlannBO.update(params)));
    }

    @PostMapping("/batchSave")
    public ResponseEntity batchSave(@RequestBody List<Map<String,Object>> params){
        return ResponseEntity.ok(JSONObject.parse(ipPlannBO.batchSave(params)));
    }

    @GetMapping("/getCode")
    public ResponseEntity getCode(@RequestParam String code){
        return ResponseEntity.ok(JSONObject.parse(ipPlannBO.getCode(code)));
    }

    @GetMapping("/ping_start")
    public ResponseEntity pingStart(){
        try {
             ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-线程-%d").build();
             ExecutorService taskExe = new ThreadPoolExecutor(8,15,
                    200L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);
            CmdbForm cmdbForm = new CmdbForm();
            List<String> req = new ArrayList<>();
            req.add("ip");
            req.add("id");
            cmdbForm.setRequiredFields(req);
            List<ConditionsBean> conditionList = new ArrayList<>();
            ConditionsBean conditionsBean = new ConditionsBean();
            conditionsBean.setField("classCode");
            conditionsBean.setOperator("EQ");
            conditionsBean.setValue("IPAddress");
            conditionList.add(conditionsBean);
            cmdbForm.setConditions(conditionList);
            int initSize = 100000;
            for(int k=0; k<initSize;k++){
                cmdbForm.setPageNum(k);
                cmdbForm.setPageSize(200);
                String query = Cmdb.query(cmdbForm);
                JsonElement jsonElement = new JsonParser().parse(query).getAsJsonObject().get("dataList");
                List<PingVO> pingVOList = JSON.parseArray(new Gson().toJson(jsonElement),PingVO.class);
                if(pingVOList.size()==0){
                    break;
                }
                PingThrend pingThrend = new PingThrend(pingVOList);
                taskExe.execute(pingThrend);
            }
            taskExe.shutdown();
            while (true){
                if(taskExe.isTerminated()) {
                    return ResponseEntity.ok(true);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("=============>>>>============pingStart fail!",e);
        }
    }

}
