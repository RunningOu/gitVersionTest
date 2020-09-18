package com.IpManage.common.api;

import com.google.gson.*;
import com.IpManage.common.api.cmdb.Uyunkey;
import com.IpManage.common.constants.TenantConstants;
import com.IpManage.common.util.HttpRestUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yl
 * @Date 2020/3/19 0019 10:02
 */
public class Tenant {

    /**
     * 查询用户列表
     * @return
     * @throws Exception
     */
    public static String selectUyunUserList() throws Exception {
        Map<String,Object> map = new HashMap<>(10);
        map.put("rows",1000);
        String uyunUserList = HttpRestUtil.post(TenantConstants.SELECT_UYUN_USERLIST, new Gson().toJson(map));
        return uyunUserList;
    }

    /**
     * 通过userID 查询用户详情 (apikey 保存在详情中 apikeys)
     * @param userId
     * @return
     * @throws Exception
     */
    public static String view(String userId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(TenantConstants.VIEW);
        sb.append("&userId="+userId);
        String user = HttpRestUtil.get(sb.toString());
        return user;
    }

    /**
     * 通过用户名(昵称 登陆名)查询 对应uyun账户的
     * @param userName
     * @return
     * @throws Exception
     */
    public static Uyunkey getUyunkey(String userName) throws Exception {
        Uyunkey uyunkey = new Uyunkey();
        String userList = selectUyunUserList();
        JsonArray asJsonArray = new JsonParser().parse(userList).getAsJsonObject().get("data").getAsJsonObject().get("records").getAsJsonArray();
        for(JsonElement array:asJsonArray) {
            JsonObject asJsonObject = array.getAsJsonObject();
            String realname = asJsonObject.get("realname").getAsString();
            String userId = asJsonObject.get("userId").getAsString();
            String apikey = null;
            String view = view(userId);
            JsonArray apikeyArray = new JsonParser().parse(view).getAsJsonObject().get("data").getAsJsonObject().get("apiKeys").getAsJsonArray();
            if(apikeyArray.size()>0){
                apikey= apikeyArray.get(0).getAsJsonObject().get("key").getAsString();
            }
            if(userName.equals(realname)){
                uyunkey.setApikey(apikey);
                uyunkey.setUserId(userId);
                uyunkey.setRealname(realname);
                break;
            }
        }
        return uyunkey;
    }

    public static String getAllUserList() throws Exception {
        return HttpRestUtil.get(TenantConstants.ALL_USER);
    }

    public static String getDept() throws Exception {
     return  HttpRestUtil.get(TenantConstants.DEPT);
    }
}
