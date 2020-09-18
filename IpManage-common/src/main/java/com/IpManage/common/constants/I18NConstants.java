package com.IpManage.common.constants;

import uyun.whale.i18n.api.I18nMessage;
import uyun.whale.i18n.api.I18nMessageSource;

/**
 * 这里定义的语言的为中文，其他语言通过资源文件定义。
 */
@I18nMessageSource("zh_CN")
public class I18NConstants {
    //itsm
    @I18nMessage("ITSM服务异常")
    public static final String ITSM_SERVICE_EXCEPTION = "itsm.service.exception";

    //alert
    @I18nMessage("Alert服务异常")
    public static final String ALERT_SERVICE_EXCEPTION = "alert.service.exception";

    @I18nMessage("服务器内部错误")
    public static final String SERVER_INTERNAL_ERROR = "sever.internal.error";

    //tenant
    @I18nMessage("租户不存在")
    public static final String TENANT_NOT_EXIST = "tenant.not.exist";
}
