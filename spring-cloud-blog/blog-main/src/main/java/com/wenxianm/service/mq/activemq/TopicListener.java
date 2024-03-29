package com.wenxianm.service.mq.activemq;

import com.wenxianm.service.common.impl.BaseServiceImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 主题消息监听器
 *
 * @author caiwx
 * @date 2019年01月18日 9:39
 *
 */
public class TopicListener extends BaseServiceImpl implements MessageListener {

    @Override
    public void onMessage(Message message) {
        //监听文本消息
        if (message instanceof TextMessage) {
            try {
                TextMessage textMessage = (TextMessage) message;
                String messageStr = textMessage.getText();
                logger.info("主题监听器收到的文本消息：" + messageStr);
            } catch (JMSException e){

            }
        }
    }
}
