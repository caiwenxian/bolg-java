package com.wenxianm.service.mq.rabbitmq.other;

public interface MyProducer {

    /**
     * 发送消息到指定队列
     * @param queueKey
     * @param object
     */
    public void sendDataToQueue(String queueKey, Object object);
}
