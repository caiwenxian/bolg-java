package com.wenxianm.model.po.user;

import com.wenxianm.model.po.common.BasePO;

import javax.validation.constraints.NotNull;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-26 17:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class LoginPO extends BasePO {

    @NotNull(message = "帐号不能为空")
    private String name;

    private String password;

    private boolean rememberMe;

    private String verifyCode;

    private String refUrl;

    public LoginPO() {
    }

    public LoginPO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginPO(String name, String password, boolean rememberMe) {
        this.name = name;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getRefUrl() {
        return refUrl;
    }

    public void setRefUrl(String refUrl) {
        this.refUrl = refUrl;
    }
}
