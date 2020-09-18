package com.IpManage.filter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.IpManage.common.constants.TenantConstants;
import com.IpManage.common.exception.RESTError;
import uyun.bird.tenant.api.UserService;
import uyun.whale.i18n.api.I18nContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by yangfei on 17/10/17.
 */
@WebFilter(filterName = "authFilter", urlPatterns = "/api/*", asyncSupported = true)
@Component
public class AuthFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Reference(protocol="dubbo")
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("-----------过滤器初始化------------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookieArray = httpServletRequest.getCookies();
        if (cookieArray == null || cookieArray.length == 0) {
            unauthorizedError((HttpServletResponse) response);
            return;
        }
        Map<String, Object> userInfo;
        String language;
        Cookie token = null;
        Cookie languageCookie = null;
        for (Cookie cookie : cookieArray) {
            if (TenantConstants.TOKEN.equals(cookie.getName()))
                token = cookie;
            if (TenantConstants.LANGUAGE.equals(cookie.getName()))
                languageCookie = cookie;
        }
        //根据token获取用户信息判断是否登录
        if (token != null) {
            try {
                userInfo = userService.verify(token.getValue());
                if (CollectionUtils.isEmpty(userInfo)) {
                    unauthorizedError((HttpServletResponse) response);
                    return;
                }
                httpServletRequest.getSession().setAttribute(TenantConstants.TENANT_ID, userInfo.get(TenantConstants.TENANT_ID));
                language = languageCookie == null ? (String) userInfo.get(TenantConstants.LANGUAGE) : languageCookie.getValue();
            } catch (Exception e) {
                unauthorizedError((HttpServletResponse) response);
                logger.error("userService verify error", e);
                return;
            }
        } else {
            unauthorizedError((HttpServletResponse) response);
            return;
        }
        //设置语言，如果没有值，默认英文
        I18nContext.setLang(StringUtils.isBlank(language) ? "en_US" : language);
        chain.doFilter(request, response);
    }

    private void unauthorizedError(HttpServletResponse response) throws IOException {
        RESTError errorResponse = new RESTError(HttpStatus.UNAUTHORIZED.value(), "user verify error, please login!");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(JSON.toJSONString(errorResponse));
    }

    @Override
    public void destroy() {
        logger.info("-----------过滤器销毁------------");
    }

}
