package com.IpManage.controller;

import com.IpManage.bo.IpPlannBO;
import com.IpManage.common.api.cmdb.PingVO;
import com.IpManage.common.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yl
 * @Date 2020/2/13 0013 14:55
 */
@Slf4j
public class PingThrend extends Thread{

    private final IpPlannBO ipPlannBO = SpringUtils.getBean(IpPlannBO.class);

    private List<PingVO> pingVOList;

    public PingThrend(List<PingVO> pingVOList){
        this.pingVOList = pingVOList;
    }

    @Override
    public void run() {
        Object object = ipPlannBO.pingMonitor(pingVOList);
        log.debug("result:{}",object);
    }



}
