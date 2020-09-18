package com.IpManage.common.i18n;

import uyun.whale.i18n.api.I18nConstants;
import uyun.whale.i18n.impls.I18nService;

/**
 * 利用Spring初始化多语言SDK i18n服务
 */
public class I18nServiceInit {
    private String defaultLanguage;
    private String languageResourceFilePath;

    public I18nServiceInit(String defaultLanguage, String languageResourceFilePath) {
        this.defaultLanguage = defaultLanguage;
        this.languageResourceFilePath = languageResourceFilePath;
    }

    public void init() {
        // 初始化多语言资源，必须调用，否则后续调用  I18nService.getMessage 将读取不到消息
        if (defaultLanguage == null || defaultLanguage.equals(""))
            defaultLanguage = "zh_CN";
        System.setProperty(I18nConstants.KEY_I18N_SOURCE_FILE_PATH, languageResourceFilePath);
        I18nService.init(defaultLanguage); // 默认语言设置
    }
}
