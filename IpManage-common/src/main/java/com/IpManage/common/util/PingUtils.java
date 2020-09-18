package com.IpManage.common.util;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author yl
 * @Date 2020/5/8 0008 15:17
 */
public class PingUtils {

    //isReachable使用icmp实现此功能，需要root权限
    //可能不准
    public static boolean ping(String ip) {
        //超时应该在3钞以上
        int  timeOut =  1 ;
        // 当返回值是true时，说明host是可用的，false则不可。
        try {
            return InetAddress.getByName(ip).isReachable(timeOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isNodeReachable(String ip) {
        try {
            return 0==Runtime.getRuntime().exec("ping -c 1 "+ip).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        String ip = "15.15.13.80";
        System.out.println(ping(ip));
        System.out.println(isNodeReachable(ip));
    }
}
