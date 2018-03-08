package model.bo.user;

/**
 * 在线用户信息
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-08 15:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class Client {

    /**
     * 账户
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    public Client() {
    }

    public Client(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
