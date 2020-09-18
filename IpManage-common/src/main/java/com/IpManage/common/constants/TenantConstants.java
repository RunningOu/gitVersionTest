package com.IpManage.common.constants;

public class TenantConstants extends BaseUyunConstants{
    public static final String USER_ID = "userId";
    public static final String TENANT_ID = "tenantId";
    public static final String TOKEN = "token";
    public static final String LANGUAGE = "language";

    /**
     * 默认用户名称
     */
    public static final String USER_REAL_NAME = "IpManage";

    /**
     * IpManage用户对应的apikey
     */
    public static final String API_KEY = "e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * 默认邮箱
     */
    public static final String EMAIL = "IpManage@IpManagesoft.cn";

    /**
     * 手机号
     */
    public static final String MOBILE = "15957196656";

    /**
     * 查询用户列表
     */
    public static final String SELECT_UYUN_USERLIST = UYUN_IP+"/tenant/api/v1/user/openapi/list?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * 通过用户id查询用户详情
     */
    public static final String VIEW =UYUN_IP+"/tenant/serviceapi/api/v1/user/details/view?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * 查询用户对应的部门信息（层级关系）
     */
    public static final String DEPT = UYUN_IP+"/tenant/api/v1/depart/openapi/listtree?apikey=e10adc3949ba59abbe56e057f2gg88dd&tenantId=e10adc3949ba59abbe56e057f20f88dd";

    public static final String ALL_USER = UYUN_IP+"/tenant/api/v1/user/openapi/listall?apikey=e10adc3949ba59abbe56e057f2gg88dd&pageNo=1&rows=1000";

}
