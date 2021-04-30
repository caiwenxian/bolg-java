package com.wenxianm.model.enums.music;

/**
 * 来源
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-27 11:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Origin {

    WANG_YI("网易", 1),

    KU_GOU("酷狗", 2);

    private String name;

    private Integer code;

    Origin(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    Origin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    static String getName(int code) {
        for (Origin origin : Origin.values()) {
            if (code == origin.getCode()) {
                return origin.name;
            }
        }
        return null;
    }
}
