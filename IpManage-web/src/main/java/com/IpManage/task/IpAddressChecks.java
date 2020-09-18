package com.IpManage.task;

import com.IpManage.bo.IpmIpInfoBO;
import com.IpManage.common.util.SpringUtils;
import com.IpManage.dataobject.IpmIpInfo;
import io.parallec.core.ParallecResponseHandler;
import io.parallec.core.ParallelClient;
import io.parallec.core.ParallelTask;
import io.parallec.core.ResponseOnSingleTask;
import io.parallec.core.bean.ping.PingMode;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * IP地址检测
 * ouln
 * 2020年6月13日13:52:10
 */
public class IpAddressChecks implements Job {


    private final IpmIpInfoBO ipmIpInfoBO = SpringUtils.getBean(IpmIpInfoBO.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ping();
    }

    /**
     * 开始ping
     */
    public void ping(){
        System.out.println("----------定时任务执行成功---------");
        List<IpmIpInfo> resPing = new ArrayList<>();
        IpmIpInfo ipmIpInfo = new IpmIpInfo();

        ipmIpInfo.setLastOnlineTimeStart(getMonthStart());
        ipmIpInfo.setLastOnlineTimeEnd(getMonthEnd());
//        ipmIpInfo.setUseStatus("2");
        for (int i = 1; i < 99999; i++) {
            List<IpmIpInfo> resList = ipmIpInfoBO.queryList(ipmIpInfo,i,500).getList();
            if (resList.size()==0){
                break;
            }else{
                List<String> ips = new ArrayList<>();
                Map<String,IpmIpInfo> ipinfoMap = new HashMap();
                for (IpmIpInfo ipinfo:resList
                     ) {
                    ipinfoMap.put(ipinfo.getIp(),ipinfo);
                    ips.add(ipinfo.getIp());
                }
//                try {
//                    resPing.addAll(ipDown(preparePing(ips),ipinfoMap));
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                resPing.addAll(ipDown(preparePing(ips),ipinfoMap));
            }
        }
        System.out.println("-------------------总条数"+resPing.size());
        //持久化
        ipmIpInfoBO.insertList(resPing);
    }
    public List<IpmIpInfo> ipDown(List<Map> resPing,Map<String,IpmIpInfo> IpInfolist){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map ipInfo:resPing
             ) {
            IpInfolist.get(ipInfo.get("ip")).setTestTime(sdf.format(date));
            if((boolean)ipInfo.get("isPing")){
                IpInfolist.get(ipInfo.get("ip")).setLastOnlineTime(sdf.format(date));
                IpInfolist.get(ipInfo.get("ip")).setOnlineStatus("2");
            }else{
                IpInfolist.get(ipInfo.get("ip")).setOnlineStatus("1");
            }
        }
        return new ArrayList<>(IpInfolist.values());
    }
    public static List<Map> pingPro(List<String> ips) throws ExecutionException, InterruptedException {
        List<Map> res= new ArrayList<>();
        List<Future>  futureTaskList = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (String ip:ips) {
            PingRun<Map> pingRun = new PingRun(ip);
            futureTaskList.add(executor.submit(pingRun));
        }
        executor.shutdown();
        //之所以这样做（重新遍历一次），是因为.get会阻塞线程
        for (Future map:futureTaskList
        ) {
            res.add((Map)map.get());
        }
        return res;
    }

    public List<Map> preparePing(List<String> ips){
        List<Map> listRes = new ArrayList<>();
        ParallelClient pc = new ParallelClient();
        try {
            ParallelTask task = pc.preparePing().setConcurrency(1500).setTargetHostsFromList(ips)
                    .setPingMode(PingMode.INET_ADDRESS_REACHABLE_NEED_ROOT)
                    .setPingNumRetries(2)
                    .setPingTimeoutMillis(2000)
                    .execute(new ParallecResponseHandler() {
                        @Override
                        public void onCompleted(ResponseOnSingleTask res,
                                                Map<String, Object> responseContext) {
                            Map mapIpInfo = new HashMap<>();
                            mapIpInfo.put("ip",res.getHost());
                            if("UNREACHABLE".equals(res.getStatusCode())){
                                mapIpInfo.put("isPing",false);
                            }
                            if("LIVE".equals(res.getStatusCode())){
                                mapIpInfo.put("isPing",true);
                            }
                            listRes.add(mapIpInfo);
                        }
                    });
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                //释放资源,
    //            pc.releaseExternalResources();
            }
            return listRes;
    }
    /**
     * 获取本月开始日期
     * @return String
     * **/
    public static String getMonthStart(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 00：00：00";
    }

    /**
     * 获取本月最后一天
     * @return String
     * **/
    public static String getMonthEnd(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time)+" 23:59:59";
    }
}
