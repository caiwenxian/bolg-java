package service.mq.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import dao.java.mq.IRabbitMessageDao;
import model.po.mq.MessagePO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import service.common.impl.BaseServiceImpl;
import service.music.reptile.IReptileSongService;
import utils.SerializeUtil;

import java.time.LocalDateTime;

/**
 * 消费者
 * @author caiwx
 * @date 2019年02月25日 11:11
 *
 */
public class QueueListener extends BaseServiceImpl implements ChannelAwareMessageListener {

    @Autowired
    IReptileSongService reptileSongService;
    @Autowired
    IRabbitMessageDao rabbitMessageDao;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        Thread.sleep(5000);
        byte[] body = message.getBody();
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        Boolean redelivered = message.getMessageProperties().getRedelivered();
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


        // 代表投递的标识符，唯一标识了当前信道上的投递，通过 deliveryTag ，消费者就可以告诉 RabbitMQ 确认收到了当前消息，见下面的方法
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // 如果是重复投递的消息，redelivered 为 true
//        Boolean redelivered = message.getMessageProperties().getRedelivered();
//        logger.info("deliveryTag:" + deliveryTag + ", redelivered:" + redelivered);
        // 代表消费者确认收到当前消息，第二个参数表示一次是否 ack 多条消息
//        channel.basicAck(deliveryTag, false);

        // 代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
//        channel.basicNack(deliveryTag, false, false);
        // 代表消费者拒绝当前消息，第二个参数表示是否把当前消息重新入队
//        channel.basicReject(deliveryTag,false);

        MessagePO messagePO = (MessagePO) SerializeUtil.deSerialize(body);
        messagePO.setConsumeTime(LocalDateTime.now());
        try {
            logger.info("消息id：" + messagePO.getId());
            if ("1".equals(messagePO.getType())) {
                reptileSongService.saveSongWithSongList(JSONObject.parseObject(messagePO.getData()));
            }

            channel.basicAck(deliveryTag, false);
            //更新消息状态
            messagePO.setStatus(1);

            rabbitMessageDao.updateMessageStatus(messagePO);

        } catch(Exception e) {
            logger.error("消费消息失败", e);
            //拒绝消息，并将消息重新加入队列
            if (!redelivered) {
                channel.basicReject(deliveryTag,true);
            } else {
               // 如果是重复投递的消息,拒绝消息并且不重新放入队列
                channel.basicReject(deliveryTag,false);
                //更新消息状态
                messagePO.setStatus(-1);
                rabbitMessageDao.updateMessageStatus(messagePO);
            }

        }

    }



}
