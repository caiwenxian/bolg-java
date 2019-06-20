package service.knowledge.impl;

import dao.java.knowledge.impl.KnowledgeDaoImpl;
import exception.SerException;
import model.bo.user.Client;
import model.dto.knowledge.ArticleDTO;
import model.enums.knowledge.ArticleType;
import model.po.common.PagePO;
import model.po.knowledge.ArticlePO;
import model.to.ArticleTO;
import model.vo.knowledge.ArticleVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.knowledge.IKnowledgeService;
import service.user.IClientService;
import utils.RandomUtil;
import utils.SerializeUtil;

import java.time.LocalDateTime;
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
    @Autowired
    IClientService clientService;

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
//            列表不用获取html内容
//            vo.setContent((String) SerializeUtil.deSerialize(po.getContent()));
            if (StringUtils.isNotBlank(po.getContentText())) {
                vo.setContentText(po.getContentText().length() > 150 ? po.getContentText().substring(0, 150) + "..." : po.getContentText());
            }
            vo.setUserName(po.getUserName());
            vo.setBrowseAmount(po.getBrowseAmount());
            vo.setCommentAmount(po.getCommentAmount());
            vos.add(vo);

        }
        PagePO pagePO = new PagePO(count, vos);
//        throw new Exception("code");
        return pagePO;
    }

    @Override
    public ArticleVO getArticle(String id) throws SerException, Exception {
        ArticlePO po = knowledgeDao.getArticle(id);
        if (po == null) {
            return null;
        }
        ArticleVO vo = new ArticleVO();
        vo.setId(po.getId());
        vo.setType(ArticleType.getName(po.getType()));
        vo.setTitle(po.getTitle());
        vo.setCreateTime(po.getCreateTime());
        /*将content字段反序列化*/
        vo.setContent((String) SerializeUtil.deSerialize(po.getContent()));
        vo.setUserName(po.getUserName());
        vo.setBrowseAmount(po.getBrowseAmount());
        vo.setCommentAmount(po.getCommentAmount());
        return vo;
    }

    @Override
    public void addArticle(ArticleTO to) throws SerException, Exception {
        Client client = clientService.getCurrentUser();
        String userId = client.getUserId();
        ArticlePO po = new ArticlePO();
        po.setUserId(userId);
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

    @Override
    public void updateArticleBrowseAmount(String articleId) throws SerException {
        ArticlePO po = knowledgeDao.getArticle(articleId);
        if (null == po) {
            throw new SerException("更新实体不存在");
        }
        ArticlePO entity = new ArticlePO();
        entity.setId(articleId);
        entity.setBrowseAmount(po.getBrowseAmount() == null ? 1 : po.getBrowseAmount() + 1);
        entity.setModifyTime(LocalDateTime.now());

        knowledgeDao.updateLocal(entity);
    }

    @Override
    public void updateArticleCommentAmount(String articleId) throws SerException {
        ArticlePO po = knowledgeDao.getArticle(articleId);
        if (null == po) {
            throw new SerException("更新实体不存在");
        }
        ArticlePO entity = new ArticlePO();
        entity.setId(articleId);
        entity.setCommentAmount(po.getCommentAmount() == null ? 1 : po.getCommentAmount() + 1);
        entity.setModifyTime(LocalDateTime.now());

        knowledgeDao.updateLocal(entity);
    }
}
