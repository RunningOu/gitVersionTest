package com.IpManage.task;

import com.IpManage.common.util.ConnectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class PingRun<T> implements Callable<T> {
    private String ip;
    public PingRun(String ip){
        super();
        this.ip=ip;
    }

    @Override
    public T call() throws Exception {
        Map<String,Object> res = new HashMap<>();
        res.put("ip",this.ip);
        res.put("isPing", ConnectionUtil.pingPro(this.ip,3));
        return (T)res;
    }
}