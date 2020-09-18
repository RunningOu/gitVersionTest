package com.IpManage.controller;

import com.github.pagehelper.PageInfo;
import com.IpManage.base.BaseResult;
import com.IpManage.base.BaseResultGenerator;
import com.IpManage.bo.IpmEnumInfoBO;
import com.IpManage.common.util.Page;
import com.IpManage.dataobject.IpmEnumInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("enum")
@Api("IP字典信息管理")
public class IpmEnumInfoController {

    @Autowired
    IpmEnumInfoBO ipmEnumInfoBO;

    /**
     * 查询IP字典信息集合
     * @param page
     * @return
     */
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public BaseResult query(@RequestBody Page<IpmEnumInfo> page){
        List<IpmEnumInfo> list = ipmEnumInfoBO.queryEnumList(page.getModel(),page.getPageNum(),page.getPageSize());
        PageInfo<IpmEnumInfo> pageInfo = new PageInfo<>(list);
        Map res = new HashMap();
        res.put("dataList",pageInfo.getList());
        res.put("total",pageInfo.getSize());
        return BaseResultGenerator.success("查询成功！",res);
    }

    /**
     * 修改IP字典信息
     * @param ipmEnumInfo
     * @return
     */
    @RequestMapping(value = "updateEnumInfo",method = RequestMethod.POST)
    public BaseResult updateEnumInfo(@RequestBody IpmEnumInfo ipmEnumInfo){
        return BaseResultGenerator.success("操作成功！",ipmEnumInfoBO.updateEnumInfo(ipmEnumInfo));
    }

    /**
     * 新增IP字典信息
     * @param ipmEnumInfo
     * @return
     */
    @RequestMapping(value = "insertEnumInfo",method = RequestMethod.POST)
    public BaseResult insertEnumInfo(@RequestBody IpmEnumInfo ipmEnumInfo){
        return BaseResultGenerator.success("操作成功！",ipmEnumInfoBO.insertEnumInfo(ipmEnumInfo));
    }
    /**
     * 删除IP字典信息
     * @param ipmEnumInfo
     * @return
     */
    @RequestMapping(value = "deleteEnumInfo",method = RequestMethod.POST)
    public BaseResult deleteEnumInfo(@RequestBody IpmEnumInfo ipmEnumInfo){
        return BaseResultGenerator.success("操作成功！",ipmEnumInfoBO.deleteEnumInfo(ipmEnumInfo));
    }
}
