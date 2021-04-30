package com.wenxianm.service.knowledge;

import com.wenxianm.exception.SerException;
import com.wenxianm.model.dto.knowledge.ArticleCommentDTO;
import com.wenxianm.model.po.common.PagePO;
import com.wenxianm.model.po.knowledge.ArticleCommentPO;
import com.wenxianm.model.vo.knowledge.ArticleCommentVO;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-22 16:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public interface IArticleCommentService {

    /**
     * 添加评论
     *
     * @param
     * @return class
     * @version v1
     */
    void addComment(ArticleCommentPO po) throws SerException;

    /**
     * 获取评论列表
     *
     * @param
     * @return class
     * @version v1
     */
    PagePO<ArticleCommentVO> listArticleComment(ArticleCommentDTO dto) throws SerException, InvocationTargetException, IllegalAccessException;


}
