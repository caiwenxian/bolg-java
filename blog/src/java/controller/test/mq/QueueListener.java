package controller.test.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                TextMessage textMessage = (TextMessage) message;
                String messageStr = textMessage.getText();
                System.out.println("队列监听器收到的文本消息：" + messageStr);
            } catch (JMSException e){

            }
        }
    }
}
