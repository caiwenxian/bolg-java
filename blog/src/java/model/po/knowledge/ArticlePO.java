package model.po.knowledge;

import model.enums.knowledge.ArticleStatus;
import model.enums.knowledge.ArticleType;
import model.po.base.annotation.TableName;
import model.po.common.BasePO;

/**
 * 文章
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@TableName(name = "t_article")
public class ArticlePO extends BasePO {

    private byte[] content;

    private Integer status;

    private Integer type;

    private String title;

    private String contentText;


    public ArticlePO() {
    }

    public ArticlePO(byte[] content) {
        this.content = content;
    }

    public ArticlePO(byte[] content, Integer status, Integer type) {
        this.content = content;
        this.status = status;
        this.type = type;
    }

    public ArticlePO(byte[] content, Integer status, Integer type, String title) {
        this.content = content;
        this.status = status;
        this.type = type;
        this.title = title;
    }

    public ArticlePO(byte[] content, Integer status, Integer type, String title, String contentText) {
        this.content = content;
        this.status = status;
        this.type = type;
        this.title = title;
        this.contentText = contentText;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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
