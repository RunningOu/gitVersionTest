package com.IpManage.common.util;//package com.IpManage.common.util;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.*;
//
///**
// * IP地址管理类
// * ouln
// * 2020年6月13日09:35:18
// */
//public class IPAddressUtil {
//
//    public static List<Map> ping(List<String> ips){
//        List<Map> res= new ArrayList<>();
////        ExecutorService executor = Executors.newScheduledThreadPool(6);
//        ExecutorService executor = Executors.newFixedThreadPool(8);
//        for (String ip:ips) {
//            PingR pingRun = new PingRun(ip);
//            Future<Map> result = executor.submit(pingRun);
//            try {
//                res.add(result.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        executor.shutdown();
//        System.out.println(res);
//        return res;
//    }
//
//    public static List<Map> pingPro(List<String> ips){
//        List<Map> res= new ArrayList<>();
////        ExecutorService executor = Executors.newScheduledThreadPool(6);
//        ExecutorService executor = Executors.newFixedThreadPool(8);
//        for (String ip:ips) {
//            PingRun pingRun = new PingRun(ip);
////            Future<Map> result = executor.submit(pingRun);
//            FutureTask<Map> futureTask =  new FutureTask<>(pingRun);
//            Thread thread = new Thread(futureTask);
//            thread.start();
//            try {
//                Map result = futureTask.get();
////                res.add(result.get());
//                res.add(result);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        executor.shutdown();
//        System.out.println(res);
//        return res;
//    }
//}
