package com.wenxianm.dao.java.knowledge;

import com.wenxianm.model.dto.knowledge.ArticleDTO;
import com.wenxianm.model.po.knowledge.ArticlePO;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public interface IKnowledgeDao {

    int countArticle(ArticleDTO dto);

    List<ArticlePO> listArticle(ArticleDTO dto);

    void addArticle(ArticlePO po);

    ArticlePO getArticle(String id);
}
