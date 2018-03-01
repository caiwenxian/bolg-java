package model.po.user;

import model.po.common.BasePO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-26 17:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class LoginPO extends BasePO{

    private String name;

    private String password;

    public LoginPO() {
    }

    public LoginPO(String name, String password) {
        this.name = name;
        this.password = password;
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
}
