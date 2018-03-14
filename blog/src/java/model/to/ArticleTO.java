package model.to;

import model.enums.knowledge.ArticleStatus;
import model.enums.knowledge.ArticleType;
import model.po.common.BasePO;

/**
 * 文章
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class ArticleTO extends BasePO{

    private String content;

    private ArticleStatus status;

    private ArticleType type;

    public ArticleTO(){}

    public ArticleTO(String content) {
        this.content = content;
    }

    public ArticleTO(String content, ArticleStatus status, ArticleType type) {
        this.content = content;
        this.status = status;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }
}
