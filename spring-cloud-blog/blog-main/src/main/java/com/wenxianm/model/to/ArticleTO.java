package com.wenxianm.model.to;

import com.wenxianm.model.enums.knowledge.ArticleStatus;
import com.wenxianm.model.enums.knowledge.ArticleType;
import com.wenxianm.model.po.common.BasePO;

/**
 * 文章
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class ArticleTO extends BasePO {

    private String content;

    private ArticleStatus status;

    private ArticleType type;

    private String title;

    private String contentText;

    public ArticleTO() {
    }

    public ArticleTO(String content) {
        this.content = content;
    }

    public ArticleTO(String content, ArticleStatus status, ArticleType type) {
        this.content = content;
        this.status = status;
        this.type = type;
    }

    public ArticleTO(String content, ArticleStatus status, ArticleType type, String title) {
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

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
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
