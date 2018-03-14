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
    TECHNOLOGY(1),

    /**
     * 生活类别
     */
    LIFE(2);

    private Integer code;

    ArticleType(Integer code) {
        this.code = code;
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
}
