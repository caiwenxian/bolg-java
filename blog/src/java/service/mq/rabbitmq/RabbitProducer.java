package service.mq.rabbitmq;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import service.common.impl.BaseServiceImpl;

import java.util.UUID;

/**
 * 生产者
 * @author caiwx
 * @date 2019年02月25日 11:04
 *
 */

public class RabbitProducer extends BaseServiceImpl implements ConfirmCallback, RabbitTemplate.ReturnCallback, InitializingBean {

    private RabbitTemplate rabbitTemplate;

  /*  @Autowired
    public RabbitProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }*/

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送消息
     * @author caiwx
     * @date 2019年02月25日 11:09
     *
     */
    public void sendMessage(String content){
        try {
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            logger.info("开始发送消息");
            rabbitTemplate.convertAndSend("spring-boot-exchange", "spring-boot-routingKey", content, correlationData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * @author caiwx
     * @date 2019年02月25日 11:09
     *
     */
    public void sendMessage(MessagePO messagePO){
        try {
            Message message = MessageBuilder
                    .withBody(SerializationUtils.serialize(messagePO))
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            logger.info("开始发送消息");
            rabbitTemplate.convertAndSend("spring-boot-exchange", "spring-boot-routingKey", message, correlationData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("回调id：" + correlationData + "b:" + b + " s: + s");
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.info("回调message：" + message + ", i:" + i + ", s:" + s + ", s1:" + s1 + ", s2:" + s2);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
}
