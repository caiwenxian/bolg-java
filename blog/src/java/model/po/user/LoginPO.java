package model.po.user;

import model.po.base.validate.ADD;
import model.po.common.BasePO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-26 17:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class LoginPO extends BasePO{

    @NotBlank(groups = {ADD.class}, message = "帐号不能为空")
//    @Size(min=3, max=8, message = "帐号长度3-8字符")
    private String name;

    private String password;

    private boolean rememberMe;

    private String verifyCode;

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
}
