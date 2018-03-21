package model.bo.user;

import java.io.Serializable;

/**
 * 在线用户信息
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-08 15:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class Client implements Serializable{

    /**
     * 账户
     */
    private String userId;

    /**
     * 账户
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * token
     */
    private String token;

    public Client() {
    }

    public Client(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public Client(String userId, String name, Integer type) {
        this.userId = userId;
        this.name = name;
        this.type = type;
    }

    public Client(String userId, String name, Integer type, String token) {
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.token = token;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
