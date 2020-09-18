package com.IpManage.common.constants;

/**
 * @author yl
 * @Date 2020/4/1 0001 13:22
 */
public class CmdbConstants extends BaseUyunConstants {


    /**
     * 查询全部分层
     */
    public static final String GET_ALL =UYUN_IP+"/store/openapi/v2/models/classes/layers/get_all?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * code
     * 查询分层下的属性
     */
    public static final String GET_BY_LAYER = UYUN_IP+"/store/openapi/v2/models/classes/get_by_layer?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * 查询资源
     */
    public static final String QUERY = UYUN_IP+"/store/openapi/v2/resources/query?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * 查询cmdb仓库树
     */
    public static final String REPO_CLASS_TREE = UYUN_IP+"/cmdb/api/v3/categories/repoClassTree?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * class_code
     * 根据classCode查询 资产应用的模板
     */
    public static final String GET_BY_CLASS = UYUN_IP+"/store/openapi/v2/models/interfaces/get_by_class?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * interface_code
     * 根据模板code查询 模板详情
     */
    public static final String GET_BY_INTERFACE = UYUN_IP+"/store/openapi/v2/models/attrs/get_by_interface?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * code
     * 查询单个属性
     */
    public static final String ATTR_GET = UYUN_IP+"/store/openapi/v2/models/attrs/get?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * 更新资源
     */
    public static final String UPDATE = UYUN_IP+"/store/openapi/v2/resources/update?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * 批量保存资源
     */
    public static final String BATCH_SAVE = UYUN_IP+"/store/openapi/v2/resources/batch_save?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * 查询单个资产
     */
    public static final String GET = UYUN_IP+"/store/openapi/v2/resources/get?apikey=e10adc3949ba59abbe56e057f2gg88dd";

    /**
     * 添加单个资源
     */
    public static final String SAVE = UYUN_IP+"/store/openapi/v2/resources/save?apikey=e10adc3949ba59abbe56e057f2gg88dd";
}
