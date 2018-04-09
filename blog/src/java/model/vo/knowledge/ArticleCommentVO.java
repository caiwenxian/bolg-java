package model.vo.knowledge;

import model.po.user.UserPO;
import model.vo.BaseVO;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-22 16:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class ArticleCommentVO extends BaseVO {



    /**
     * 用户id
     */
    private UserPO user;

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

    /**
     * 评论内容
     */
    private String approve;

    /**
     * 子评论
     */
    List<ArticleCommentVO> childs;

    public ArticleCommentVO() {
    }

    public ArticleCommentVO(UserPO user, String articleId, String parentId, String comment, List<ArticleCommentVO> childs) {
        this.user = user;
        this.articleId = articleId;
        this.parentId = parentId;
        this.comment = comment;
        this.childs = childs;
    }

    public UserPO getUser() {
        return user;
    }

    public void setUser(UserPO user) {
        this.user = user;
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

    public List<ArticleCommentVO> getChilds() {
        return childs;
    }

    public void setChilds(List<ArticleCommentVO> childs) {
        this.childs = childs;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }
}
