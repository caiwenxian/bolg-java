package service.knowledge.impl;

import dao.java.knowledge.IArticleCommentDao;
import dao.java.knowledge.impl.ArticleCommentDaoImpl;
import exception.SerException;
import model.dto.knowledge.ArticleCommentDTO;
import model.po.common.PagePO;
import model.po.knowledge.ArticleCommentPO;
import model.vo.knowledge.ArticleCommentVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.knowledge.IArticleCommentService;
import service.user.IClientService;
import utils.RandomUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章评论业务实现
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-22 16:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Service
public class ArticleCommentServiceImpl implements IArticleCommentService {

    @Autowired
    ArticleCommentDaoImpl articleCommentDao;
    @Autowired
    IClientService clientService;

    @Override
    public void addComment(ArticleCommentPO po) throws SerException {
        try {
            po.setUserId(clientService.getCurrentUser().getUserId());
            po.setId(RandomUtil.getUid());
            articleCommentDao.add(po);
        } catch (SerException e) {
            throw new SerException(e.getCode(), e.getMessage());
        }

    }

    @Override
    public PagePO<ArticleCommentVO> listArticleComment(ArticleCommentDTO dto) throws SerException, InvocationTargetException, IllegalAccessException {

        int count = articleCommentDao.countArticleComment(dto);
        if (count < 1) {
            return new PagePO(0, null);
        }
        List<ArticleCommentPO> pos = articleCommentDao.listArticleComment(dto);
        List<ArticleCommentVO> vos = convertComment(pos);
        return new PagePO<ArticleCommentVO>(count, vos);
    }

    private List<ArticleCommentVO> convertComment(List<ArticleCommentPO> pos) throws InvocationTargetException, IllegalAccessException {
        List<ArticleCommentVO> vos = new ArrayList<ArticleCommentVO>();
        for (ArticleCommentPO po : pos) {
            ArticleCommentVO vo = new ArticleCommentVO();
            BeanUtils.copyProperties(vo, po);

            if (po.getParentId() == null) {
                continue;
            }
            List<ArticleCommentVO> childs = new ArrayList<ArticleCommentVO>();
            for (ArticleCommentPO po1 : pos) {
                if (po1.getParentId().equals(po.getId())) {
                    ArticleCommentVO vo1 = new ArticleCommentVO();
                    BeanUtils.copyProperties(vo1, po1);
                    childs.add(vo1);
                    pos.remove(po1);
                }
            }
            vo.setChilds(childs);
            vos.add(vo);
        }
        return vos;
    }
}
