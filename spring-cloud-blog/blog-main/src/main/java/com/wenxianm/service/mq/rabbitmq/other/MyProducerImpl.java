package com.wenxianm.service.mq.rabbitmq.other;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyProducerImpl implements MyProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final static Logger LOGGER = Logger.getLogger(MyProducerImpl.class);

    public void sendDataToQueue(String queueKey, Object object) {
        try {
            LOGGER.info("=========发送消息开始=============消息："+object.toString());
            amqpTemplate.convertAndSend(queueKey, object);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }
}
