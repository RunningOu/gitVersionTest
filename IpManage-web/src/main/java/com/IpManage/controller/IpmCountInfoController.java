package com.IpManage.controller;

import com.IpManage.base.BaseResult;
import com.IpManage.base.BaseResultGenerator;
import com.IpManage.bo.IpmCountInfoBO;
import com.IpManage.dataobject.IpmAffiliatedUnitCountInfo;
import com.IpManage.dataobject.IpmProjectCountInfo;
import io.swagger.annotations.Api;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("count")
@Api("IP地址统计")
public class IpmCountInfoController {

    @Autowired
    IpmCountInfoBO ipmCountInfoBO;

    /**
     * 按项目统计
     * @return
     */
    @RequestMapping(value = "ipProjectCount",method = RequestMethod.GET)
    public BaseResult ipProjectCount(){
        List<Map> ipmPlanInfos = ipmCountInfoBO.countByProject();

        Map<String,IpmProjectCountInfo> dataMap = new HashMap<>();
        for (Map info:ipmPlanInfos
        ) {
            String key = info.get("project").toString()+info.get("projectCode").toString();
            if(dataMap.containsKey(key)){
                if("1".equals(info.get("onlineStatusCode").toString())){
                    dataMap.get(key).setUnOnline(Integer.parseInt(info.get("count").toString()));
                }

                if("2".equals(info.get("onlineStatusCode").toString())){
                    dataMap.get(key).setOnline(Integer.parseInt(info.get("count").toString()));
                }
            }else{
                IpmProjectCountInfo ipmProjectCountInfo = new IpmProjectCountInfo();
                ipmProjectCountInfo.setProject(info.get("project").toString());
                if("1".equals(info.get("onlineStatusCode").toString())){
                    ipmProjectCountInfo.setUnOnline(Integer.parseInt(info.get("count").toString()));
                }

                if("2".equals(info.get("onlineStatusCode").toString())){
                    ipmProjectCountInfo.setOnline(Integer.parseInt(info.get("count").toString()));
                }
                dataMap.put(key,ipmProjectCountInfo);
            }
        }

        return BaseResultGenerator.success("查询成功",dataMap.values());
    }

    public Map<String, IpmAffiliatedUnitCountInfo> getAffiliatedUnitCount( List<Map> ipmPlanInfos ){
        Map<String, IpmAffiliatedUnitCountInfo> dataMap = new HashMap<>();
        for (Map info:ipmPlanInfos
        ) {
            String key = info.get("affiliatedUnit").toString()+info.get("affiliatedUnitCode").toString();
            if(dataMap.containsKey(key)){
                if("1".equals(info.get("onlineStatusCode").toString())){
                    dataMap.get(key).setUnOnline(Integer.parseInt(info.get("count").toString()));
                    if("2".equals(info.get("useStatusCode").toString())) {
                        dataMap.get(key).setDistribution(dataMap.get(key).getOnline() + Integer.parseInt(info.get("count").toString()));
                    }
                }

                if("2".equals(info.get("onlineStatusCode").toString())){
                    dataMap.get(key).setOnline(Integer.parseInt(info.get("count").toString()));
                    dataMap.get(key).setDistribution(dataMap.get(key).getUnOnline()+Integer.parseInt(info.get("count").toString()));
                }

                if("1".equals(info.get("useStatusCode").toString())){
                    dataMap.get(key).setUnDistribution(Integer.parseInt(info.get("count").toString()));
                }

//                if("2".equals(info.get("useStatusCode").toString())){
//                    dataMap.get(key).setDistribution(Integer.parseInt(info.get("count").toString()));
//                }
            }else{
                IpmAffiliatedUnitCountInfo ipmAffiliatedUnitCountInfo = new IpmAffiliatedUnitCountInfo();
                ipmAffiliatedUnitCountInfo.setAffiliatedUnit(info.get("affiliatedUnit").toString());
                if("1".equals(info.get("onlineStatusCode").toString())){
                    ipmAffiliatedUnitCountInfo.setUnOnline(Integer.parseInt(info.get("count").toString()));
                    if("2".equals(info.get("useStatusCode").toString())) {
                        ipmAffiliatedUnitCountInfo.setDistribution(ipmAffiliatedUnitCountInfo.getOnline() + Integer.parseInt(info.get("count").toString()));
                    }
                }

                if("2".equals(info.get("onlineStatusCode").toString())){
                    ipmAffiliatedUnitCountInfo.setOnline(Integer.parseInt(info.get("count").toString()));
                    ipmAffiliatedUnitCountInfo.setDistribution(ipmAffiliatedUnitCountInfo.getUnOnline()+Integer.parseInt(info.get("count").toString()));
                }

                if("1".equals(info.get("useStatusCode").toString())){
                    ipmAffiliatedUnitCountInfo.setUnDistribution(Integer.parseInt(info.get("count").toString()));
                }
                dataMap.put(key,ipmAffiliatedUnitCountInfo);
            }
        }
        return dataMap;
    }
    /**
     * 按派出所统计
     * @return
     */
    @RequestMapping(value = "ipAffiliatedUnitCount",method = RequestMethod.GET)
    public BaseResult ipAffiliatedUnitCount(){
        return BaseResultGenerator.success("查询成功",getAffiliatedUnitCount(ipmCountInfoBO.countByAffiliatedUnit()).values());
    }
    /**
     * 按派出所统计报表
     * @return
     */
    @RequestMapping(value = "ipAffiliatedUnitExportXls",method = RequestMethod.GET)
    public ModelAndView ipAffiliatedUnitExportXls(HttpServletRequest request){
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());

        Map<String,IpmAffiliatedUnitCountInfo> dataMap = getAffiliatedUnitCount(ipmCountInfoBO.countByAffiliatedUnit());

        List<IpmAffiliatedUnitCountInfo> res = new ArrayList<>(dataMap.values());
        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "应用单位IP统计信息");
        // 注解对象Class
        mv.addObject(NormalExcelConstants.CLASS, IpmAffiliatedUnitCountInfo.class);
        // 自定义表格参数
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("应用单位IP统计信息列表", "统计信息"));
        // 导出数据列表
        mv.addObject(NormalExcelConstants.DATA_LIST, res);

        return mv;
    }
    /**
     * 按项目统计报表
     * @return
     */
    @RequestMapping(value = "ipProjectExportXls",method = RequestMethod.GET)
    public ModelAndView ipProjectExportXls(HttpServletRequest request){
//        List<Map> res = ipmCountInfoBO.countByProject();
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<Map> ipmPlanInfos = ipmCountInfoBO.countByProject();

        Map<String,IpmProjectCountInfo> dataMap = new HashMap<>();
        for (Map info:ipmPlanInfos
        ) {
            String key = info.get("project").toString()+info.get("projectCode").toString();
            if(dataMap.containsKey(key)){
                if("1".equals(info.get("onlineStatusCode").toString())){
                    dataMap.get(key).setUnOnline(Integer.parseInt(info.get("count").toString()));
                }

                if("2".equals(info.get("onlineStatusCode").toString())){
                    dataMap.get(key).setOnline(Integer.parseInt(info.get("count").toString()));
                }
            }else{
                IpmProjectCountInfo ipmProjectCountInfo = new IpmProjectCountInfo();
                ipmProjectCountInfo.setProject(info.get("project").toString());
                if("1".equals(info.get("onlineStatusCode").toString())){
                    ipmProjectCountInfo.setUnOnline(Integer.parseInt(info.get("count").toString()));
                }

                if("2".equals(info.get("onlineStatusCode").toString())){
                    ipmProjectCountInfo.setOnline(Integer.parseInt(info.get("count").toString()));
                }
                dataMap.put(key,ipmProjectCountInfo);
            }
        }

        List<IpmProjectCountInfo> res = new ArrayList<>(dataMap.values());
        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "项目IP统计信息");
        // 注解对象Class
        mv.addObject(NormalExcelConstants.CLASS, IpmProjectCountInfo.class);
        // 自定义表格参数
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("项目IP统计信息列表", "统计信息"));
        // 导出数据列表
        mv.addObject(NormalExcelConstants.DATA_LIST, res);

        return mv;
    }
}
