package model.dto.knowledge;

import model.dto.common.BaseDTO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-22 16:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class ArticleCommentDTO extends BaseDTO {

    /**
     * 文章id
     */
    private String articleId;

    public ArticleCommentDTO() {
    }

    public ArticleCommentDTO(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
