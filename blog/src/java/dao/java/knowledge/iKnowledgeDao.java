package dao.java.knowledge;

import model.dto.knowledge.ArticleDTO;
import model.po.knowledge.ArticlePO;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public interface iKnowledgeDao {

    int countArticle(ArticleDTO dto);

    List<ArticlePO> listArticle(ArticleDTO dto);

    void addArticle(ArticlePO po);

    ArticlePO getArticle(String id);
}
