package dao.java.knowledge;

import model.dto.knowledge.ArticleCommentDTO;
import model.po.knowledge.ArticleCommentPO;
import model.vo.knowledge.ArticleCommentVO;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-22 16:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public interface IArticleCommentDao {

    /**
     * 获取文章评论总条数
     *
     * @param
     * @return class
     * @version v1
     */
    int countArticleComment(ArticleCommentDTO dto);

    /**
     * 获取文章评论列表
     *
     * @param
     * @return class
     * @version v1
     */
    List<ArticleCommentVO> listArticleComment(ArticleCommentDTO dto);
}
