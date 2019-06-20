package model.po.user;

import model.dto.common.BaseDTO;
import model.po.base.annotation.TableName;
import model.po.common.BasePO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-25 16:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@TableName(name = "t_user")
public class UserPO extends BasePO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 类型
     */
    private Integer type;

    public UserPO() {
    }

    public UserPO(String name, String password, Integer type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
