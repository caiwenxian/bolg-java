package dao.java.mq.impl;

import dao.java.impl.DaoImpl;
import dao.java.mq.IRabbitMessageDao;
import exception.SerException;
import model.dto.mq.MessageDTO;
import model.po.base.WherePrams;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import model.po.mq.MessagePO;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class RabbitMessageDaoImpl extends DaoImpl<MessagePO, String> implements IRabbitMessageDao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplateASS;


    @Override
    public void addMessage(MessagePO messagePO) throws SerException {
        try {
            messagePO.setData(messagePO.getData().replaceAll("'", "''"));   //单引号替换成两个单引号
            add(messagePO, true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public void updateMessageStatus(MessagePO messagePO) throws SerException {
        try {
            MessagePO old = get(messagePO.getId());
            if (old != null) {
                if (messagePO.getSendTime() != null) {
                    old.setSendTime(messagePO.getSendTime());
                }
                if (messagePO.getConsumeTime() != null) {
                    old.setConsumeTime(messagePO.getConsumeTime());
                }
                old.setStatus(messagePO.getStatus());
                old.setData(old.getData().replaceAll("'", "''"));
                update(old);
                return;
            }
            throw new SerException("更新主体不存在");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public int countMessage(MessageDTO messageDTO) throws SerException {
        try {
            WherePrams wherePrams = new WherePrams(true);
            if (StringUtils.isNotBlank(messageDTO.getId())) {
                wherePrams.and("id", "=", messageDTO.getId());
            }
            if (messageDTO.getStatus() != null) {
                wherePrams.and("status", "=", messageDTO.getStatus());
            }

            return (int) count(wherePrams);
        } catch (Exception e) {
            logger.error("获取消息总数失败：", e);
            throw new SerException();
        }
    }

    @Override
    public List<MessagePO> listMessage(MessageDTO messageDTO) throws SerException {
        try {
            WherePrams wherePrams = new WherePrams(true);
            if (StringUtils.isNotBlank(messageDTO.getId())) {
                wherePrams.and("id", "like", messageDTO.getId());
            }
            if (messageDTO.getStatus() != null) {
                wherePrams.and("status", "=", messageDTO.getStatus());
            }
            wherePrams.orderBy("status asc, createTime desc");
            wherePrams.limit(messageDTO.getStartRow(), messageDTO.getLimit());
            return list(wherePrams);
        } catch (Exception e) {
            logger.error("获取消息列表失败：", e);
            throw new SerException();
        }
    }

    @Override
    public MessagePO getMessage(MessageDTO messageDTO) throws SerException {
        try {
            WherePrams wherePrams = new WherePrams(true);
            wherePrams.and("id", "=", messageDTO.getId());
            return get(wherePrams);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException();
        }
    }
}
