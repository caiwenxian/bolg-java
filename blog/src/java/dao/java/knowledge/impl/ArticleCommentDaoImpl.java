package dao.java.knowledge.impl;

import dao.java.impl.DaoImpl;
import dao.java.knowledge.IArticleCommentDao;
import model.dto.knowledge.ArticleCommentDTO;
import model.po.knowledge.ArticleCommentPO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章评论业务实现
 * @Author: [caiwenxian]
 * @Date: [2018-03-22 16:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Repository
public class ArticleCommentDaoImpl extends DaoImpl<ArticleCommentPO, String> implements IArticleCommentDao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplateASS;

    @Override
    public List<ArticleCommentPO> listArticleComment(ArticleCommentDTO dto) {
        return sqlSessionTemplateASS.selectList("listArticleComment", dto);
    }

    @Override
    public int countArticleComment(ArticleCommentDTO dto) {
        return sqlSessionTemplateASS.selectOne("countArticleComment", dto);
    }
}
