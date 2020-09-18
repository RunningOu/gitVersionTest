package com.IpManage;


import com.IpManage.task.IpAddressCheck;
import com.IpManage.task.IpAddressChecks;
import com.IpManage.task.PingTask;
import com.IpManage.task.QuartzManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @discription Task模块启动执行类
 * @time 2018年7月27日 12:32:23
 * @author ouln
 */
@Component
public class TaskRunner implements CommandLineRunner {

    /**
     * @discription 启动后检索数据库里面的task信息状态是否是启动（value：1），为1自动启动相应的定时器
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("success：==================================《启动task定时任务成功》========================================");

       // startRunning();
    }
//    public void startRunning(){
//        QuartzManager.addJob("ping","pingGrop",
//                "pingTrigger","pingGropTrigger",
//                IpAddressChecks.class,"0 0 1 * * ?");
//
//        QuartzManager.addJob("pings","pingGrops",
//                "pingTriggers","pingGropTriggers",
//                IpAddressCheck.class,"0 * */1 * * ?");
//        System.out.println("success：==================================《启动task定时任务成功》========================================");
//        }

    public static void main(String[] args) {
        IpAddressCheck ipAddressCheck=new IpAddressCheck();
        ipAddressCheck.ping();
    }
}
