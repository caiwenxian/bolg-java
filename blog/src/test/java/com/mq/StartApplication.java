package com.mq;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class StartApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("activeMQ-config.xml2");
        MessageService messageService = (MessageService)applicationContext.getBean("messageService");

        messageService.sendQueueMessage("发送的消息1");
        messageService.sendQueueMessage("发送的消息2");
        messageService.sendQueueMessage("发送的消息3");
        messageService.sendQueueMessage("发送的消息4");
        messageService.sendTopicMessage("发送的消息22");
    }
}
