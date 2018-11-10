package com.wangcheng.zeus.core.config.properties;

import com.wangcheng.zeus.core.config.properties.enums.LoginType;

/**
 * @author : evan
 * @Date: 2018/9/18 22:55
 * @Description:
 */
public class BrowserProperties {

    private String loginPage = "/default-login.html";

    private LoginType loginType = LoginType.JSON;

    private long rememeberMeExpireIn = 60 * 60 * 24 * 5;

    private boolean serverSaveToken = true;

    private String secret = "525qz";


    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public long getRememeberMeExpireIn() {
        return rememeberMeExpireIn;
    }

    public void setRememeberMeExpireIn(long rememeberMeExpireIn) {
        this.rememeberMeExpireIn = rememeberMeExpireIn;
    }

    public boolean isServerSaveToken() {
        return serverSaveToken;
    }

    public void setServerSaveToken(boolean serverSaveToken) {
        this.serverSaveToken = serverSaveToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
