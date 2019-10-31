package service.mq.rabbitmq.other;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class MyQueenListener implements MessageListener {
    public void onMessage(Message message) {
        String str = "";
        try {
            str = new String(message.getBody(), "UTF-8");
            System.out.println("=============监听【QueueListenter】消息"+message);
            System.out.print("=====获取消息"+str);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
