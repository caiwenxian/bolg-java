package com.wenxianm.service.knowledge;

import com.wenxianm.exception.SerException;
import com.wenxianm.model.dto.knowledge.ArticleDTO;
import com.wenxianm.model.po.common.PagePO;
import com.wenxianm.model.po.knowledge.ArticlePO;
import com.wenxianm.model.po.music.ArtistPO;
import com.wenxianm.model.to.ArticleTO;
import com.wenxianm.model.vo.knowledge.ArticleVO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public interface IKnowledgeService {

    /**
     * 获取文章列表
     *
     * @param
     * @return class
     * @version v1
     */
    PagePO<ArticlePO> ListArticle(ArticleDTO dto) throws Exception;

    /**
     * 获取单个文章
     *
     * @param
     * @return class
     * @version v1
     */
    ArticleVO getArticle(String id) throws SerException, Exception;

    /**
     * 添加文章
     *
     * @param
     * @return class
     * @version v1
     */
    void addArticle(ArticleTO to) throws SerException, Exception;

    /**
     * 更新文章观阅数
     *
     * @param
     * @return class
     * @version v1
     */
    void updateArticleBrowseAmount(String article) throws SerException;

    /**
     * 更新文章评论数
     *
     * @param
     * @return class
     * @version v1
     */
    void updateArticleCommentAmount(String article) throws SerException;

}
