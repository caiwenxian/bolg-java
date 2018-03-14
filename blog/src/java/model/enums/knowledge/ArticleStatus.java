package model.enums.knowledge;

import javax.print.DocFlavor;

/**
 * 文章状态
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-14 14:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public enum ArticleStatus {

    /**
     * 发布状态
     */
    PUBLISH(1),

    /**
     * 草稿状态
     */
    DRAFT(2),

    /**
     * 删除状态
     */
    DELETED(3);

    private Integer code;

    ArticleStatus(Integer code) {
        this.code = code;
    }

    public static ArticleStatus articleStatus(Integer code) {
        for (ArticleStatus top : ArticleStatus.values()) {
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
