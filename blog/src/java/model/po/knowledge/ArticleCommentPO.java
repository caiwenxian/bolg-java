package model.po.knowledge;

import model.po.base.annotation.TableName;
import model.po.common.BasePO;

/**
 * 文章评论
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-22 16:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
@TableName(name = "t_article_comment")
public class ArticleCommentPO extends BasePO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 被回复的评论id
     */
    private String parentId;

    /**
     * 评论内容
     */
    private String comment;

    public ArticleCommentPO() {
    }

    public ArticleCommentPO(String userId, String articleId, String parentId, String comment) {
        this.userId = userId;
        this.articleId = articleId;
        this.parentId = parentId;
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
