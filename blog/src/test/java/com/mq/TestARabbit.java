package com.mq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.mq.rabbitmq.other.MyProducer;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-rabbitmq.xml", "classpath:spring-mvc.xml"})
public class TestARabbit
{
    @Autowired
    MyProducer myProducer;

    private static final String QUEUE_KEY = "test_queue";

    @Test
    public void send()
    {
        String message = "hello rabbitMQ!";
//        Map<String,Object> msg = new HashMap<String,Object>();
//        msg.put("data","hello,rabbmitmq!");
        myProducer.sendDataToQueue(QUEUE_KEY,message);
    }





}