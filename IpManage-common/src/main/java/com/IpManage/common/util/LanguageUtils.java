package com.IpManage.common.util;

import org.apache.commons.lang3.StringUtils;
import uyun.whale.i18n.api.I18nContext;

import java.util.Locale;

/**
 * Created by wujiaqi on 17/10/24.
 */
public class LanguageUtils {

    /**
     * 获取Locale语言
     *
     * @return
     */
    public static Locale getLocale() {
        String language = I18nContext.getLang();
        if (StringUtils.isNotBlank(language) && language.equalsIgnoreCase(Locale.US.toString())) {
            return Locale.US;
        } else {
            //默认中文
            return Locale.SIMPLIFIED_CHINESE;
        }
    }
}
