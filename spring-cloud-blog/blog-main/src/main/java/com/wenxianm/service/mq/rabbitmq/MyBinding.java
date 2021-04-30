package com.wenxianm.service.mq.rabbitmq;

import org.springframework.amqp.core.*;

public class MyBinding {

    private Queue queue;

    private DirectExchange exchange;

    private String routingKey;

    public MyBinding(Queue queue, DirectExchange exchange, String routingKey) {
        this.queue = queue;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public Binding createBinding(){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
}
