package dao.java.knowledge;

import dao.java.impl.DaoImpl;
import model.dto.knowledge.ArticleDTO;
import model.po.knowledge.ArticlePO;
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
public class KnowledgeDaoImpl extends DaoImpl<ArticlePO, String> implements iKnowledgeDao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplateASS;

    public int countArticle(ArticleDTO dto){
        return sqlSessionTemplateASS.selectOne("countArticle", dto);
    }

    public List<ArticlePO> listArticle(ArticleDTO dto){
        return sqlSessionTemplateASS.selectList("listArticleByPage", dto);
    }
}
