package com.IpManage.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionUtil {

    /**
     * ping 指定IP的联通情况
     * @param host
     * @return
     * @throws Exception
     */
    public static boolean ping(String host, int timeOut) throws Exception {
        Thread.sleep(100);
        boolean status = InetAddress.getByName(host).isReachable(timeOut);
        // 当返回值是true时，说明host是可用的，false则不可。
        return status;
    }

    /**
     * ping 指定IP的联通情况(网络不稳定的情况使用)
     * ps:timeOut 适用于linux环境
     * @param host
     * @param timeOut 单位为秒
     * @return
     * @throws Exception
     */
    public static boolean pingPro(String host, int timeOut) {
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            process = runtime.exec("ping -w "+timeOut+" " + host);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"GBK");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            isr.close();
            br.close();

            if (null != sb && !sb.toString().equals("")) {
                String logString = "";
                if (sb.toString().indexOf("TTL") > 0) {
                    // 网络畅通
                    connect = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connect;
    }

    /**
     * 测试telnet 机器端口的连通性
     * @param host
     * @param port
     * @param timeOut
     * @return
     */
    public static boolean telnet(String host, int port, int timeOut){
        Socket socket = new Socket();
        boolean isConnected = false;
        try {
            // 建立连接
            socket.connect(new InetSocketAddress(host, port), timeOut);
            isConnected = socket.isConnected();
        } catch (IOException e) {
            isConnected = false;
        }finally{
            try {
                socket.close();   // 关闭连接
            } catch (IOException e) {
                isConnected = false;
            }
        }
        return isConnected;
    }

    /**
     * 综合检索优先ping，然后Telnet
     * @param host
     * @param port
     * @param timeOut
     * @return
     * @throws Exception
     */
//    public static boolean pingAndTelnet(String host, int port, int timeOut) throws Exception{
//        if(ping(host,timeOut)){
//            return true;
//        }else{
//            if(telnet(host,port,timeOut)){
//                return true;
//            }else{
//                return false;
//            }
//        }
//    }
    public static void main(String[] args) {
//        String hostname = "192.168.3.204";
//        int port = 80;
//        int timeout = 3000;
//        boolean isConnected = false;
//        try {
//            isConnected = ping(hostname, timeout);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("telnet "+ hostname + " " + port + "\n==>isConnected: " + isConnected);

        try {
            System.out.println(pingPro("192.168.3.101",8000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}