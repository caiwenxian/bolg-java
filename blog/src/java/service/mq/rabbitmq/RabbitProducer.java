package service.mq.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * 生产者
 * @author caiwx
 * @date 2019年02月25日 11:04
 *
 */
public class RabbitProducer implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitProducer(RabbitTemplate rabbitTemplate){
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
            rabbitTemplate.convertAndSend("spring-boot-exchange", "spring-boot-routingKey", content, correlationData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("回调id：" + correlationData + "b:" + b);
    }
}
