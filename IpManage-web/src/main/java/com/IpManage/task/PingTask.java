package com.IpManage.task;

import com.IpManage.common.util.HttpRestUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author yl
 * @Date 2020/5/9 0009 13:29
 */
public class PingTask implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            HttpRestUtil.get("http://192.168.0.28/IpManage/api/ping/ip/plann/ping_start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
