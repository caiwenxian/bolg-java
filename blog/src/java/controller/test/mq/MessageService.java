package controller.test.mq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

//@Service
public class MessageService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "testQueue")
    private Destination testQueue;

    @Resource(name = "testTopic")
    private Destination testTopic;

    public void sendQueueMessage(final String messageContent) {
        jmsTemplate.send(testQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(messageContent);
                return message;
            }
        });
    }

    public void sendTopicMessage(final String messageContent) {
        jmsTemplate.send(testTopic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(messageContent);
                return textMessage;
            }
        });
    }


}
