package controller.mq;

import controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.common.Result;
import service.common.impl.ActResult;
import service.mq.MessageService;

import javax.jms.JMSException;

/**
 * mq控制器
 *
 * @author caiwx
 * @date 2019年01月18日 9:45
 *
 */

@Controller
@RequestMapping("activemq")
public class ActiveMQController extends BaseController  {

    @Autowired
    private MessageService messageService;


    @PostMapping("test")
    @ResponseBody
    public Result test(@RequestParam String message) {

        messageService.sendQueueMessage(message);

        return new ActResult();
    }
}
