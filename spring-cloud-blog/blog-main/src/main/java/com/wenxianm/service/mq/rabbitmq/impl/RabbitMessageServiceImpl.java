package com.wenxianm.service.mq.rabbitmq.impl;

import com.wenxianm.dao.java.mq.IRabbitMessageDao;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.dto.mq.MessageDTO;
import com.wenxianm.model.po.common.PagePO;
import com.wenxianm.model.po.mq.MessagePO;
import com.wenxianm.model.vo.mq.MessageVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenxianm.service.common.impl.BaseServiceImpl;
import com.wenxianm.service.mq.rabbitmq.IRabbitMessageService;
import com.wenxianm.service.mq.rabbitmq.RabbitProducer;
import com.wenxianm.utils.ApprBeanUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class RabbitMessageServiceImpl extends BaseServiceImpl implements IRabbitMessageService {

    @Autowired
    IRabbitMessageDao rabbitMessageDao;
    @Autowired
    private RabbitProducer rabbiProducer;

    @Override
    public PagePO<MessageVO> listRabbitService(MessageDTO messageDTO) throws SerException{
        try {
            int count = rabbitMessageDao.countMessage(messageDTO);
            List<MessagePO> list = rabbitMessageDao.listMessage(messageDTO);
            List<MessageVO> listVO = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(list)) {
                for (MessagePO po : list) {

                    listVO.add((MessageVO) ApprBeanUtil.poToVo(po, MessageVO.class));
                }            }
            return new PagePO<MessageVO>(count, listVO);
        } catch (SerException e) {
            logger.error("获取rabbit消息列表失败：", e);
            throw new SerException();
        }
    }

    @Override
    public void reSendRabbitMessage(MessageDTO messageDTO) throws SerException {
        try {
            MessagePO messagePO = rabbitMessageDao.getMessage(messageDTO);
            if (messagePO == null) {
                throw new SerException("消息实体不存在");
            }

            rabbiProducer.reSendMessage(messagePO);
        } catch (Exception e) {

        }
    }
}
