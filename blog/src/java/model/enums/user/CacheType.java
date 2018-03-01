package model.enums.user;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-27 16:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public enum CacheType {

    USER_INFO("用户缓存");

    private String name;

    CacheType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
