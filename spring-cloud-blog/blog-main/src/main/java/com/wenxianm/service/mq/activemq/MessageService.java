package com.wenxianm.service.mq.activemq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 发送消息服务
 *
 * @author caiwx
 * @date 2019年01月18日 9:38
 *
 */
@Service
public class MessageService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "queueDestination")
    private Destination queueDestination;

    @Resource(name = "topicDestination")
    private Destination topicDestination;

    /**
     * 发送队列文本消息
     *
     * @author caiwx
     * @date 2019年01月18日 9:35
     *
     */
    public void sendQueueMessage(final String messageContent) {
        jmsTemplate.send(queueDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(messageContent);
                return message;
            }
        });
    }

    /**
     * 发送主题文本消息
     *
     * @author caiwx
     * @date 2019年01月18日 9:36
     *
     */
    public void sendTopicMessage(final String messageContent) {
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(messageContent);
                return textMessage;
            }
        });
    }


}
