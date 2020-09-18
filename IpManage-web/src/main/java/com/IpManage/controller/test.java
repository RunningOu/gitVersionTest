package com.IpManage.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;

import com.IpManage.config.DeptExcel;
import com.IpManage.config.UserExcel;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * @author zmg
 * @desc 描述：
 * <p>
 * 实现方案：
 * @date 2020-07-14 16:48
 */
@RestController
@CrossOrigin
@RequestMapping("ipInfo")
@Api("IP信息管理")
public class test {

    @RequestMapping(value = "/exportXlsss",method = RequestMethod.GET)
    public void exportXlss(HttpServletRequest request, HttpServletResponse response) {
        // 准备测试数据
        List<UserExcel> userExcels = new ArrayList<>();
        UserExcel userExcel = new UserExcel();
        userExcel.setName("gourd");
        userExcel.setAge(23);
        userExcel.setSex("M");
        userExcel.setMobilePhone("10086");
        userExcel.setEmail("163.com");
        userExcel.setNickName("gourd");
        userExcel.setBirth(new Date());
        userExcels.add(userExcel);
        UserExcel userExcel2 = new UserExcel();
        userExcel2.setName("葫芦");
        userExcel2.setAge(23);
        userExcel2.setSex("X");
        userExcel2.setMobilePhone("110");
        userExcel2.setEmail("136@163.com");
        userExcel2.setNickName("gourd");
        userExcel2.setBirth(new Date());
        userExcels.add(userExcel2);
        List<DeptExcel> deptExcels = new ArrayList<>();
        DeptExcel deptExcel = new DeptExcel();
        deptExcel.setCode("T-01");
        deptExcel.setName("技术一部");
        deptExcel.setUsers(userExcels);
        deptExcels.add(deptExcel);
        DeptExcel deptExcel2 = new DeptExcel();
        deptExcel2.setCode("T-02");
        deptExcel2.setName("技术二部");
        deptExcel2.setUsers(userExcels);
        deptExcels.add(deptExcel2);

        // 创建参数对象
        ExportParams exportParams1 = new ExportParams();
        // 设置sheet得名称
        exportParams1.setSheetName("部门人员信息");
        ExportParams exportParams2 = new ExportParams();
        exportParams2.setSheetName("用户信息");

        // 创建sheet1使用得map
        Map<String, Object> deptDataMap = new HashMap<>(4);
        // title的参数为ExportParams类型
        deptDataMap.put("title", exportParams1);
        // 模版导出对应得实体类型
        deptDataMap.put("entity", DeptExcel.class);
        // sheet中要填充得数据
        deptDataMap.put("data", deptExcels);

        // 创建sheet2使用得map
        Map<String, Object> userDataMap = new HashMap<>(4);
        userDataMap.put("title", exportParams2);
        userDataMap.put("entity", UserExcel.class);
        userDataMap.put("data", userExcels);
        // 将sheet1和sheet2使用得map进行包装
        List<Map<String, Object>> sheetsList = new ArrayList<>();
        sheetsList.add(deptDataMap);
        sheetsList.add(userDataMap);
        // 执行方法
        Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);

        com.IpManage.controller.ExcelUtil.downLoadExcel("用户信息"+new Date() +".xls",response,workbook);

    }
}
