package com.wenxianm.dao.java.knowledge.impl;

import com.wenxianm.dao.java.impl.DaoImpl;
import com.wenxianm.dao.java.knowledge.IKnowledgeDao;
import com.wenxianm.model.dto.knowledge.ArticleDTO;
import com.wenxianm.model.po.knowledge.ArticlePO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Repository
public class KnowledgeDaoImpl extends DaoImpl<ArticlePO, String> implements IKnowledgeDao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplateASS;

    public int countArticle(ArticleDTO dto) {
        return sqlSessionTemplateASS.selectOne("countArticle", dto);
    }

    public List<ArticlePO> listArticle(ArticleDTO dto) {
        List list = sqlSessionTemplateASS.selectList("listArticleByPage", dto);
        return list;
    }

    @Override
    public void addArticle(ArticlePO po) {
        sqlSessionTemplateASS.insert("addArticle", po);
    }

    @Override
    public ArticlePO getArticle(String id) {
        return sqlSessionTemplateASS.selectOne("getArticle", id);
    }
}
