package controller.mq;

import controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.mq.rabbitmq.RabbitProducer;

@Controller
@RequestMapping("rabbitmq")
public class RabbitMQController extends BaseController {

    @Autowired
    private RabbitProducer rabbiProducer;

//    @Resource(name = "myProducer")
//    MyProducer myProducer;

    private static final String QUEUE_KEY = "test_queue";

    @RequestMapping("test")
    @ResponseBody
    public void test(){
        try {
            for (int i = 0; i < 10; i ++) {
                rabbiProducer.sendMessage("测试数据");
                Thread.sleep(500);
            }

//            String message = "hello rabbitMQ!";
//            myProducer.sendDataToQueue(QUEUE_KEY,message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
