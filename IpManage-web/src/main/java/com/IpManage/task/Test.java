package com.IpManage.task;


import com.google.gson.Gson;
import io.parallec.core.ParallecResponseHandler;
import io.parallec.core.ParallelClient;
import io.parallec.core.ParallelTask;
import io.parallec.core.ResponseOnSingleTask;
import io.parallec.core.bean.ping.PingMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Test {

    @org.junit.Test
    public void test1(){
        List<String> ips = new ArrayList<>();
        ips.add("192.168.1.101");
        ips.add("192.168.1.102");
        ips.add("192.168.1.103");
        ips.add("192.168.1.104");
        ips.add("192.168.1.105");
        ips.add("192.168.1.106");
        ips.add("192.168.1.107");
        ips.add("192.168.1.108");
        ips.add("192.168.1.109");
        ips.add("192.168.1.110");
        ips.add("192.168.1.111");
        ips.add("192.168.1.112");
        ips.add("192.168.1.113");
        ips.add("192.168.1.114");
        ips.add("192.168.1.115");
        ips.add("192.168.1.116");
        ips.add("192.168.1.117");
        ips.add("192.168.1.118");
        ips.add("192.168.1.119");
        ips.add("192.168.1.120");
        ips.add("192.168.1.121");
        ips.add("192.168.1.122");
        ips.add("192.168.1.123");
        ips.add("192.168.1.124");
        ips.add("192.168.1.125");
        ips.add("192.168.1.126");
        ips.add("192.168.1.127");
        ips.add("192.168.1.128");
        ips.add("192.168.1.129");
        ips.add("192.168.1.130");
        ips.add("192.168.1.131");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
        ips.add("192.168.1.105");
        ips.add("192.168.1.100");
//        try {
//            System.out.println( pingPro1(ips));
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        testPing(ips);
    }

    @org.junit.Test
    public void test2() throws ExecutionException, InterruptedException {
        Callable<String> threadImplCallable = new ThreadImplCallable<>("Thread1");
        FutureTask<String> futureTask = new FutureTask<>(threadImplCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        String rValue = futureTask.get();
        System.out.println("Thread1 return value is " + rValue);
        threadImplCallable = new ThreadImplCallable<>("Thread2");
        futureTask = new FutureTask<>(threadImplCallable);
        thread = new Thread(futureTask);
        thread.start();
        rValue = futureTask.get();
        System.out.println("Thread2 return value is " + rValue);

        System.out.println("end");
    }

    public static List<Map> pingPro(List<String> ips) throws ExecutionException, InterruptedException {
        List<Map> res= new ArrayList<>();
        List<FutureTask<Map>>  futureTaskList = new ArrayList<>();
//        ExecutorService executor = Executors.newScheduledThreadPool(6);
        for (String ip:ips) {
            PingRun<Map> pingRun = new PingRun(ip);
            FutureTask<Map> futureTask =  new FutureTask<>(pingRun);
            Thread thread = new Thread(futureTask);
            thread.start();
            futureTaskList.add(futureTask);
        }
        for (FutureTask<Map> map:futureTaskList
             ) {
            res.add(map.get());
        }
        return res;
    }

    public static List<Map> pingPro1(List<String> ips) throws ExecutionException, InterruptedException {
        List<Map> res= new ArrayList<>();
        List<Future>  futureTaskList = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (String ip:ips) {
            PingRun<Map> pingRun = new PingRun(ip);
            futureTaskList.add(executor.submit(pingRun));
        }
        executor.shutdown();
        //之所以这样做（重新遍历一次），是应为.get会阻塞线程
        for (Future map:futureTaskList
        ) {
            res.add((Map)map.get());
        }
        return res;
    }



    public void testPing(List<String> ips){
        List<Map<String,Object>> listRes = new ArrayList<>();
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
                            Map<String,Object> mapIpInfo = new HashMap<>();
                            mapIpInfo.put("ip",res.getHost());
                            System.out.println("================"+res.toString());
                            if("UNREACHABLE".equals(res.getStatusCode())){
                                mapIpInfo.put("isPing",false);
                            }
                            if("LIVE".equals(res.getStatusCode())){
                                mapIpInfo.put("isPing",true);
                            }
                            listRes.add(mapIpInfo);
                        }
                    });
//            Map<String, Integer> resultCountSummary = task.getAggregateResultCountSummary();
            //释放资源
            System.out.println("======================================================="+new Gson().toJson(listRes));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            pc.releaseExternalResources();
        }
    }
}
