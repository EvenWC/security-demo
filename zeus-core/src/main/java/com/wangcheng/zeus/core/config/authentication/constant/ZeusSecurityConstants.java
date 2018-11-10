package com.wangcheng.zeus.core.config.authentication.constant;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2018/10/11 21:09
 * @description:
 */
public class ZeusSecurityConstants {
    /**未登陆跳转到的url*/
    public static final   String DEFAULT_UNAUTHENTICATION_URL           = "/authentication/require";
    /**使用spring security 默认的浏览器登陆的url*/
    public static final   String DEFAULT_LOGIN_PROCESSING_URL_FORM     = "/authentication/login";
    /**自定义登陆的url*/
    public static final   String ACCOUNT_LOGIN_PROCESSING_URL_FORM      = "/account/login";
    /**手机登陆的url*/
    public static final   String DEFAULT_LOGIN_PROCESSING_URL_MOBILE    = "/authentication/mobile";
    /**验证码前缀*/
    public static final   String VALIDATE_CODE_URL_PREFIX               = "/code/";
    /**注册用户*/
    public static final   String URI_REGISTER                           = "/user/register";
    /** 过滤swagger 配置*/
    public static final   String[] URI_EXCLUDE_SWAGGER                  = new String[]{"/swagger-ui.html","/swagger-resources/configuration/ui","/v2/*","/swagger-resources"};
    /**
     * 过滤的uri
     * @return
     */
    public static final String[] URI_EXCLUDE(String ...uris){
        List<String> list = Lists.newArrayList(URI_EXCLUDE_SWAGGER);
        list.addAll(Arrays.asList(uris));
        list.add(DEFAULT_UNAUTHENTICATION_URL);
        list.add(DEFAULT_LOGIN_PROCESSING_URL_FORM);
        list.add(VALIDATE_CODE_URL_PREFIX + "*");
        list.add(URI_REGISTER);
        String[] excludeURIS = new String[list.size()];
        return list.toArray(excludeURIS);
    }
}
