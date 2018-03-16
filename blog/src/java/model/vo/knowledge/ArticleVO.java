package model.vo.knowledge;

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
public class ArticleVO extends BasePO{

    private String content;

    private ArticleStatus status;

    private String type;

    private String title;

    private String contentText;

    public ArticleVO(){}

    public ArticleVO(String content) {
        this.content = content;
    }

    public ArticleVO(String content, ArticleStatus status, String type) {
        this.content = content;
        this.status = status;
        this.type = type;
    }

    public ArticleVO(String content, ArticleStatus status, String type, String title) {
        this.content = content;
        this.status = status;
        this.type = type;
        this.title = title;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
