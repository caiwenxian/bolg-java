package model.po.common;

import java.io.Serializable;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-18 17:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BasePO implements Serializable{

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
