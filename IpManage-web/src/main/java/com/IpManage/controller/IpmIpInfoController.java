package com.IpManage.controller;

import com.IpManage.bo.IpmEnumInfoBO;
import com.IpManage.common.util.ExcelUtil;
import com.IpManage.config.DeptExcel;
import com.IpManage.config.UserExcel;
import com.IpManage.dataobject.*;
import com.IpManage.task.IpAddressCheck;
import com.github.pagehelper.PageInfo;
import com.IpManage.base.BaseResult;
import com.IpManage.base.BaseResultGenerator;
import com.IpManage.bo.IpmIpInfoBO;
import com.IpManage.common.util.Page;
import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jeecg.common.util.DateUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.def.MapExcelConstants;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecgframework.poi.excel.view.JeecgMapExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("ipInfo")
@Api("IP信息管理")
public class IpmIpInfoController {

    @Autowired
    IpmIpInfoBO ipmIpInfoBO;
    @Autowired
    IpmEnumInfoBO ipmEnumInfoBO;

    /**
     * 查询IP地址信息集合
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "queryIpInfo", method = RequestMethod.POST)
    public BaseResult query(@RequestBody Page<IpmIpInfo> page) {
        PageInfo<IpmIpInfo> pageInfo = ipmIpInfoBO.queryList(page.getModel(), page.getPageNum(), page.getPageSize());
        Map res = new HashMap();
        res.put("dataList", pageInfo.getList());
        res.put("total", pageInfo.getTotal());
        return BaseResultGenerator.success("查询成功！", res);
    }

    /**
     * IP地址分配
     *
     * @param ipmIpInfo
     * @return
     */
    @RequestMapping(value = "ipInfoBatch", method = RequestMethod.POST)
    public BaseResult planIpInfo(@RequestBody IpmIpInfo ipmIpInfo) {
        ipmIpInfo.setUseStatus("2");
        ipmIpInfo.setOnlineStatus("1");
        String[] ips = ipmIpInfo.getIp().split(",");
        for (String ip : ips
                ) {
            IpmIpInfo ipmIpInfo1 = new IpmIpInfo();
            ipmIpInfo1 = ipmIpInfo;
            ipmIpInfo1.setIp(ip);
            ipmIpInfoBO.updateIpInfo(ipmIpInfo1);
        }
        return BaseResultGenerator.success("操作成功！", ips.length);
    }

    /**
     * IP地址单个查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selIpInfoById", method = RequestMethod.GET)
    public BaseResult selIpInfoById(@RequestParam(value = "id") String id) {
        return BaseResultGenerator.success("查询成功！", ipmIpInfoBO.selIpInfoById(id));
    }

    /**
     * IP地址多个查询
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "selIpInfoByIds", method = RequestMethod.POST)
    public BaseResult selIpInfoByIds(@RequestBody List<String> ids) {
        return BaseResultGenerator.success("查询成功！", ipmIpInfoBO.selIpInfoByIds(ids));
    }

    /**
     * IP地址单个更新
     *
     * @param ipmIpInfo
     * @return
     */
    @RequestMapping(value = "updateIpInfoById", method = RequestMethod.POST)
    public BaseResult updateIpInfoById(@RequestBody IpmIpInfo ipmIpInfo) {
        return BaseResultGenerator.success("操作成功！", ipmIpInfoBO.updateIpInfo(ipmIpInfo));
    }

    /**
     * IP地址回收
     *
     * @param ips
     * @return
     */
    @RequestMapping(value = "recoveryIpInfo", method = RequestMethod.GET)
    public BaseResult recoveryIpInfo(@RequestParam(value = "ips") String ips) {

        IpmIpInfo ipmIpInfo = new IpmIpInfo();

        ipmIpInfo.setUseStatus("1");
        ipmIpInfo.setOnlineStatus("1");
        for (String ip : ips.split(",")
                ) {
            ipmIpInfo.setIp(ip);
            ipmIpInfoBO.updateIpInfo(ipmIpInfo);
        }
        return BaseResultGenerator.success("操作成功！", ips.split(",").length);
    }

    /**
     * 查询ip地址规划列表
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "queryIpPlanInfo", method = RequestMethod.POST)
    public BaseResult queryIpPlanInfo(@RequestBody Page<IpmPlanInfo> page) {
        PageInfo<IpmPlanInfo> pageInfo = ipmIpInfoBO.queryIpPlanInfo(page.getModel(), page.getPageNum(), page.getPageSize());
        Map res = new HashMap();
        res.put("dataList", pageInfo.getList());
        res.put("total", pageInfo.getTotal());
        return BaseResultGenerator.success("查询成功！", res);
    }

    /**
     * IP地址规划
     *
     * @param ipmPlanInfo
     * @return
     */
    @RequestMapping(value = "planIpInfo", method = RequestMethod.POST)
    public BaseResult planIpInfo(@RequestBody IpmPlanInfo ipmPlanInfo) {
        return BaseResultGenerator.success("操作成功！", ipmIpInfoBO.planBatch(ipmPlanInfo));
    }


    /**
     * 导出规划信息
     *
     * @param ipmPlanInfo
     * @return
     */
    @RequestMapping(value = "/plan/exportXls", method = RequestMethod.POST)
    public ModelAndView planExportXls(HttpServletRequest request, @RequestBody IpmPlanInfo ipmPlanInfo) {
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//        List<IpmPlanInfo> ipmPlanInfos = new ArrayList<>();
//        for (int i = 1; i < 9999; i++) {
//            List<IpmPlanInfo> resList = ipmIpInfoBO.queryIpPlanInfo(ipmPlanInfo,i,100);
//            if(resList.size()==0){
//                break;
//            }else{
//                ipmPlanInfos.addAll(resList);
//            }
//        }
        List<IpmPlanInfo> ipmPlanInfos = ipmIpInfoBO.ipPlanInfoExportXls(ipmPlanInfo);
        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "IP地址规划信息列表excel报表信息");
        // 注解对象Class
        mv.addObject(NormalExcelConstants.CLASS, IpmPlanInfo.class);
        // 自定义表格参数
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("IP地址规划信息列表", "规划信息"));
        // 导出数据列表
        mv.addObject(NormalExcelConstants.DATA_LIST, ipmPlanInfos);

        return mv;
    }

    /**
     * 导出excel
     *
     * @param request
     */
    @RequestMapping(value = "/exportXls", method = RequestMethod.POST)
    public ModelAndView exportXls(HttpServletRequest request, @RequestBody IpmIpInfo ipmIpInfo) {
//        List<IpmIpInfo> ipmIpInfos = ipmIpInfoBO.ipinfoExportXls(ipmIpInfo);
//        ExcelUtil<IpmIpInfo> util = new ExcelUtil<IpmIpInfo>(IpmIpInfo.class);
//         util.exportExcel(ipmIpInfos,"dictData",response);
//        //Step.2 AutoPoi 导出Excel
//        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
////        List<IpmIpInfo> ipmIpInfos = new ArrayList<>();
////        for (int i = 1; i < 9999; i++) {
////            List<IpmIpInfo> resList = ipmIpInfoBO.queryList(ipmIpInfo,i,100);
////            if(resList.size()==0){
////                break;
////            }else{
////                ipmIpInfos.addAll(resList);
////            }
////        }
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<IpmIpInfo> ipmIpInfos = ipmIpInfoBO.ipinfoExportXls(ipmIpInfo);
        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "IP地址信息列表excel报表信息");
        // 注解对象Class
        mv.addObject(NormalExcelConstants.CLASS, IpmIpInfo.class);
        // 自定义表格参数
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("IP地址信息列表", "IP地址信息"));
        // 导出数据列表
        mv.addObject(NormalExcelConstants.DATA_LIST, ipmIpInfos);
        return mv;
    }


    @RequestMapping(value = "/exportFileXls", method = RequestMethod.POST)
    public BaseResult exportFileXls(@RequestParam("file") MultipartFile file) {
        int fail = 0;
        int fails = 0;
        try {
            ExcelUtil<IpmIpInfos> util = new ExcelUtil<IpmIpInfos>(IpmIpInfos.class);
            InputStream inputStream = file.getInputStream();
            List<IpmIpInfos> list = util.importExcel(inputStream);

            Page<IpmEnumInfo> page = new Page<>();
            IpmEnumInfo ipmEnumInfo = new IpmEnumInfo();
            ipmEnumInfo.setParentCode("manufactor");
            page.setPageNum(0);
            page.setPageNum(50);
            page.setModel(ipmEnumInfo);
            List<IpmEnumInfo> listsCs = ipmEnumInfoBO.queryEnumList(page.getModel(), page.getPageNum(), page.getPageSize());
            ipmEnumInfo.setParentCode("domain");
            page.setModel(ipmEnumInfo);
            List<IpmEnumInfo> listsDomain = ipmEnumInfoBO.queryEnumList(page.getModel(), page.getPageNum(), page.getPageSize());
            ipmEnumInfo.setParentCode("project");
            page.setModel(ipmEnumInfo);
            List<IpmEnumInfo> listsProject = ipmEnumInfoBO.queryEnumList(page.getModel(), page.getPageNum(), page.getPageSize());
            ipmEnumInfo.setParentCode("networkDesc");
            page.setModel(ipmEnumInfo);
            List<IpmEnumInfo> listsNetworkDesc = ipmEnumInfoBO.queryEnumList(page.getModel(), page.getPageNum(), page.getPageSize());
            ipmEnumInfo.setParentCode("affiliatedUnit");
            page.setModel(ipmEnumInfo);
            List<IpmEnumInfo> listsAff = ipmEnumInfoBO.queryEnumList(page.getModel(), page.getPageNum(), page.getPageSize());
            ipmEnumInfo.setParentCode("useStatus");
            page.setModel(ipmEnumInfo);
            List<IpmEnumInfo> listsUseStatus = ipmEnumInfoBO.queryEnumList(page.getModel(), page.getPageNum(), page.getPageSize());
            ipmEnumInfo.setParentCode("onlineStatus");
            page.setModel(ipmEnumInfo);
            List<IpmEnumInfo> listsOnlineStatus = ipmEnumInfoBO.queryEnumList(page.getModel(), page.getPageNum(), page.getPageSize());


            List<IpmIpInfos> ipmIpInfos = new ArrayList<>();
            for (IpmIpInfos ipinfo : list) {
                IpmIpInfos ipmIpInfo = new IpmIpInfos();
                ipmIpInfo.setIp(ipinfo.getIp());
                ipmIpInfo.setProjectManager(ipinfo.getProjectManager());
                ipmIpInfo.setCreateTime(ipinfo.getCreateTime());
                ipmIpInfo.setCreateTimeEnd(ipinfo.getCreateTimeEnd());
                ipmIpInfo.setCreateTimeStart(ipinfo.getCreateTimeStart());

                for (IpmEnumInfo info : listsCs) {
                    if (ipinfo.getManufactor().equals(info.getEnumName())) {
                        ipmIpInfo.setManufactor(info.getEnumCode());
                    }
                }
                for (IpmEnumInfo info : listsDomain) {
                    if (ipinfo.getDomain().equals(info.getEnumName())) {
                        ipmIpInfo.setDomain(info.getEnumCode());
                    }
                }
                for (IpmEnumInfo info : listsProject) {
                    if (ipinfo.getProject().equals(info.getEnumName())) {
                        ipmIpInfo.setProject(info.getEnumCode());
                    }
                }
                for (IpmEnumInfo info : listsNetworkDesc) {
                    if (ipinfo.getNetworkDesc().equals(info.getEnumName())) {
                        ipmIpInfo.setNetworkDesc(info.getEnumCode());
                    }
                }
                for (IpmEnumInfo info : listsAff) {
                    if (ipinfo.getAffiliatedUnit().equals(info.getEnumName())) {
                        ipmIpInfo.setAffiliatedUnit(info.getEnumCode());
                    }
                }
                for (IpmEnumInfo info : listsUseStatus) {
                    if (ipinfo.getUseStatus().equals(info.getEnumName())) {
                        ipmIpInfo.setUseStatus(info.getEnumCode());
                    }
                }
                for (IpmEnumInfo info : listsOnlineStatus) {
                    if (ipinfo.getOnlineStatus().equals(info.getEnumName())) {
                        ipmIpInfo.setOnlineStatus(info.getEnumCode());
                    }
                }

                ipmIpInfos.add(ipmIpInfo);
            }
            ipmIpInfoBO.insertLists(ipmIpInfos);
        } catch (Exception e) {
            e.printStackTrace();
            fail++;
        }
        return BaseResultGenerator.success("操作成功！");
//        return AjaxJson.success("上传完成! 失败："+fail+"个,重复上传工单："+fails+"个");
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export() {
//   IpAddressCheck ipAddressCheck=new IpAddressCheck();
        IpAddressCheck ipAddressCheck = new IpAddressCheck();
        ipAddressCheck.ping();
    }



}