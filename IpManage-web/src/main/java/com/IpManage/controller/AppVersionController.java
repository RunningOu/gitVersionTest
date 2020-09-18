package com.IpManage.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.IpManage.bo.AppVersionBO;
import com.IpManage.dataobject.AppVersionDO;
import com.IpManage.gateway.api.AppVersionApi;
import com.IpManage.controller.BaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author  : yangfei
 * email   : yangfei1@IpManagesoft.cn
 * time    : 2017/9/2621:53
 * desc    : 输入描述
 * version : v1.0
 */

@RestController
public class AppVersionController extends BaseController implements AppVersionApi {


    @Autowired
    AppVersionBO appVersionBO;


    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello World!";
    }


    @Override
    public ResponseEntity<AppVersionDO> insertAppVersion(String apikey, AppVersionDO appVersionDO) {
        log.debug("apikey:" + apikey + ",AppVersionDO:" + JSON.toJSONString(appVersionDO));
        AppVersionDO testStr = appVersionBO.selectAppVersionByAppName(appVersionDO);
        return ResponseEntity.ok(testStr);
    }

    @Override
    public ResponseEntity<AppVersionDO> queryVersionByAppName(String apikey, String appName) {
        AppVersionDO appVersionDO = new AppVersionDO();
        appVersionDO.setApp_name(appName);
        AppVersionDO testStr = appVersionBO.selectAppVersionByAppName(appVersionDO);
        return ResponseEntity.ok(testStr);
    }
}
