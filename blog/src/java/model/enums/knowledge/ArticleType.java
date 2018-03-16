package model.enums.knowledge;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-14 14:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public enum ArticleType {

    /**
     * 技术类别
     */
    TECHNOLOGY(1, "技术"),

    /**
     * 生活类别
     */
    LIFE(2, "生活");

    private Integer code;

    private String name;

    public static String getName(Integer code) {
        for (ArticleType type : ArticleType.values()) {
            if (type.getCode().equals(code)) {
                return type.getName();
            }
        }
        return null;
    }

    ArticleType(Integer code) {
        this.code = code;
    }

    ArticleType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ArticleType articleType(Integer code) {
        for (ArticleType top : ArticleType.values()) {
            if (top.getCode().equals(code)) {
                return top;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
