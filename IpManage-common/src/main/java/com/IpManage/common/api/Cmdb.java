package com.IpManage.common.api;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.IpManage.common.api.cmdb.CmdbForm;
import com.IpManage.common.api.cmdb.Interfaces;
import com.IpManage.common.api.cmdb.ModelAttrs;
import com.IpManage.common.api.cmdb.ModelForm;
import com.IpManage.common.constants.CmdbConstants;
import com.IpManage.common.util.HttpRestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yl
 * @Date 2020/4/1 0001 13:20
 */
public class Cmdb {

    private static Gson gson = new Gson();

    /**
     * 获取cmdb所有的分层
     * @return
     * @throws Exception
     */
    public static String getAll() throws Exception {
        return HttpRestUtil.get(CmdbConstants.GET_ALL);
    }

    /**
     * 根据资产分层的编码查询分层下的属性
     * @param code
     * @return
     * @throws Exception
     */
    public static String getByLayer(String code) throws Exception {
        return HttpRestUtil.get(CmdbConstants.GET_BY_LAYER+"&code="+code.replaceAll(" ", "%20"));
    }

    /**
     * 综合条件查询资源
     * @param cmdbForm
     * @return
     * @throws Exception
     */
    public static String query(CmdbForm cmdbForm) throws Exception {
        return HttpRestUtil.post(CmdbConstants.QUERY,gson.toJson(cmdbForm));
    }

    /**
     * 查询cmdb导航栏树
     * @return
     */
    public static String repoClassTree() throws Exception {
        return HttpRestUtil.get(CmdbConstants.REPO_CLASS_TREE);
    }

    /**
     * 根据classCode 查询模板详情字段
     * @param classCode
     * @return
     * @throws Exception
     */
    public static List<ModelForm> getModelAttrsByClassCode(String classCode) throws Exception {
        List<ModelForm> modelForms = new ArrayList<>();
        String model = HttpRestUtil.get(CmdbConstants.GET_BY_CLASS + "&class_code=" + classCode);
        List<Interfaces> interfaces = JSONObject.parseArray(model, Interfaces.class);
        for(Interfaces itf: interfaces){
            ModelForm modelForm = new ModelForm();
            modelForm.setId(itf.getId());
            modelForm.setCode(itf.getCode());
            modelForm.setName(itf.getName());
            String attrs = HttpRestUtil.get(CmdbConstants.GET_BY_INTERFACE + "&interface_code=" + itf.getCode());
            List<ModelAttrs> modelAttrs = JSONObject.parseArray(attrs, ModelAttrs.class);
            modelForm.setAttrList(modelAttrs);
            modelForms.add(modelForm);
        }
        return modelForms;
    }

    /**
     * 查询单个属性字段详情
     * @param code
     * @return
     * @throws Exception
     */
    public static String getAttrs(String code) throws Exception {
        return HttpRestUtil.get(CmdbConstants.ATTR_GET + "&code=" + code);
    }

    /**
     * 更新资源
     * @param params
     * @return
     * @throws Exception
     */
    public static String update(String params) throws Exception {
        return HttpRestUtil.post(CmdbConstants.UPDATE,params);
    }

    /**
     * 批量保存资源
     * @param params
     * @return
     * @throws Exception
     */
    public static String batchSave(String params) throws Exception {
        return HttpRestUtil.post(CmdbConstants.BATCH_SAVE,params);
    }

    public static String get(String id) throws Exception {
        return HttpRestUtil.get(CmdbConstants.GET+"&id="+id);
    }

    public static String save(String params) throws Exception {
        return HttpRestUtil.post(CmdbConstants.SAVE,params);
    }

}
