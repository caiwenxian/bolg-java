package service.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * 消费者
 * @author caiwx
 * @date 2019年02月25日 11:11
 *
 */
public class QueueListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        byte[] body = message.getBody();
        System.out.println("receive message:" + new String(body));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
