package service.knowledge.impl;

import dao.java.knowledge.KnowledgeDaoImpl;
import exception.SerException;
import model.dto.knowledge.ArticleDTO;
import model.enums.knowledge.ArticleStatus;
import model.enums.knowledge.ArticleType;
import model.po.common.PagePO;
import model.po.knowledge.ArticlePO;
import model.to.ArticleTO;
import model.vo.knowledge.ArticleVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.knowledge.IKnowledgeService;
import utils.RandomUtil;
import utils.SerializeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-13 15:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Service
public class KnowledgeServiceImpl implements IKnowledgeService {

    @Autowired
    KnowledgeDaoImpl knowledgeDao;

    @Override
    public PagePO<ArticlePO> ListArticle(ArticleDTO dto) throws Exception {

        int count = knowledgeDao.countArticle(dto);
        List<ArticlePO> list = knowledgeDao.listArticle(dto);

        List<ArticleVO> vos = new ArrayList<ArticleVO>();
        for (ArticlePO po : list) {
            ArticleVO vo = new ArticleVO();
//            BeanUtils.copyProperties(vo, po);
//            vo.setStatus(ArticleStatus.articleStatus(po.getStatus()));
            vo.setId(po.getId());
            vo.setType(ArticleType.getName(po.getType()));
            vo.setTitle(po.getTitle());
            vo.setCreateTime(po.getCreateTime());
            /*将content字段反序列化*/
//            列表不用获取html内容
//            vo.setContent((String) SerializeUtil.deSerialize(po.getContent()));
            vo.setContentText(po.getContentText() == null ? null : po.getContentText().substring(0, 100));
            vos.add(vo);

        }
        PagePO pagePO = new PagePO(count, vos);
        return pagePO;
    }

    @Override
    public ArticleVO getArticle(String id) throws SerException, Exception{
        ArticlePO po = knowledgeDao.getArticle(id);
        ArticleVO vo = new ArticleVO();
        vo.setType(ArticleType.getName(po.getType()));
        vo.setTitle(po.getTitle());
        vo.setCreateTime(po.getCreateTime());
        /*将content字段反序列化*/
        vo.setContent((String) SerializeUtil.deSerialize(po.getContent()));

        return vo;
    }

    @Override
    public void addArticle(ArticleTO to) throws SerException, Exception {

        ArticlePO po = new ArticlePO();
        BeanUtils.copyProperties(po, to);
        if (null != to.getStatus()) {
            po.setStatus(to.getStatus().getCode());
        }
        if (null != to.getType()) {
            po.setType(to.getType().getCode());
        }
        /*将content字段序列化存储*/
        byte[] content = SerializeUtil.serialize(to.getContent());
        po.setContent(content);
        po.setId(RandomUtil.getUid());
        knowledgeDao.addArticle(po);
    }
}
