package service.mq.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import service.common.impl.BaseServiceImpl;
import service.music.reptile.IReptileSongService;
import utils.SerializeUtil;

/**
 * 消费者
 * @author caiwx
 * @date 2019年02月25日 11:11
 *
 */
public class QueueListener extends BaseServiceImpl implements ChannelAwareMessageListener {

    @Autowired
    IReptileSongService reptileSongService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        Thread.sleep(5000);
        byte[] body = message.getBody();
//        logger.info("receive message:" + new String(body));
       /* long deliveryTag = message.getMessageProperties().getDeliveryTag();
        Boolean redelivered = message.getMessageProperties().getRedelivered();
        logger.info("deliveryTag:" + deliveryTag + ", redelivered:" + redelivered);*/
       /* try {
            logger.info("message.getMessageProperties().getDeliveryTag():" + message.getMessageProperties().getDeliveryTag());
            logger.info("thread:" + Thread.currentThread().getName());
//            if (message.getMessageProperties().getDeliveryTag()%10 == 0) {
            if ("messageContainer-2".equals(Thread.currentThread().getName())) {
                throw new Exception();
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//            logger.info("进入catch");
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }*/


       /* // 代表投递的标识符，唯一标识了当前信道上的投递，通过 deliveryTag ，消费者就可以告诉 RabbitMQ 确认收到了当前消息，见下面的方法
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // 如果是重复投递的消息，redelivered 为 true
        Boolean redelivered = message.getMessageProperties().getRedelivered();
        logger.info("deliveryTag:" + deliveryTag + ", redelivered:" + redelivered);
        // 代表消费者确认收到当前消息，第二个参数表示一次是否 ack 多条消息
        channel.basicAck(deliveryTag, false);*/

        // 代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
//        channel.basicNack(deliveryTag, false, false);
        // 代表消费者拒绝当前消息，第二个参数表示是否把当前消息重新入队
//        channel.basicReject(deliveryTag,false);


        if (message!= null) {
            MessagePO messagePO = (MessagePO) SerializeUtil.deSerialize(body);
            logger.info("消息id：" + messagePO.getId());
            if ("1".equals(messagePO.getType())) {
                reptileSongService.saveSongWithSongList((JSONObject) messagePO.getData());
            }
        }
    }



}
