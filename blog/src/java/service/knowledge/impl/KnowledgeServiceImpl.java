package service.knowledge.impl;

import dao.java.knowledge.KnowledgeDaoImpl;
import exception.SerException;
import model.dto.knowledge.ArticleDTO;
import model.enums.knowledge.ArticleStatus;
import model.enums.knowledge.ArticleType;
import model.po.base.WherePrams;
import model.po.common.Page;
import model.po.common.PagePO;
import model.po.knowledge.ArticlePO;
import model.po.music.ArtistPO;
import model.to.ArticleTO;
import model.vo.knowledge.ArticleVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.knowledge.IKnowledgeService;
import utils.RandomUtil;
import utils.SerializeUtil;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
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
    public PagePO<ArticlePO> ListArticle(ArticleDTO dto) throws Exception{

        int count = knowledgeDao.countArticle(dto);
        List<ArticlePO> list = knowledgeDao.listArticle(dto);

        List<ArticleVO> vos = new ArrayList<ArticleVO>();
        for (ArticlePO po : list) {
            ArticleVO vo = new ArticleVO();
//            BeanUtils.copyProperties(vo, po);
            vo.setStatus(ArticleStatus.articleStatus(po.getStatus()));
            vo.setType(ArticleType.articleType(po.getType()));
            vo.setContent((String) SerializeUtil.deSerialize(po.getContent()));
            vos.add(vo);

        }
        PagePO pagePO = new PagePO(count, vos);
        return pagePO;
    }

    @Override
    public ArticlePO getArticle(String id) {
        return knowledgeDao.get(id);
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
        byte[] content = SerializeUtil.serialize(to.getContent());
        po.setContent(content);
        po.setId(RandomUtil.getUid());
        System.out.println("po:" + (String) SerializeUtil.deSerialize(po.getContent()));
        int result = knowledgeDao.add(po);
    }
}
