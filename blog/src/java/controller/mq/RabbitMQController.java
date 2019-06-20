package controller.mq;

import org.springframework.web.bind.annotation.RequestMapping;
import service.mq.rabbitmq.RabbitProducer;

import javax.annotation.Resource;

public class RabbitMQController {

    @Resource(name = "producer")
    private RabbitProducer rabbiProducer;

    @RequestMapping("test")
    private void test(){
        rabbiProducer.sendMessage("测试数据");
    }

}
