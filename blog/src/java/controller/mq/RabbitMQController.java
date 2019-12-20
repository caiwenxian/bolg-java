package controller.mq;

import controller.common.BaseController;
import exception.SerException;
import model.dto.mq.MessageDTO;
import model.po.common.PagePO;
import model.po.mq.MessagePO;
import model.vo.mq.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.common.Result;
import service.common.impl.ActResult;
import service.mq.rabbitmq.IRabbitMessageService;
import service.mq.rabbitmq.RabbitProducer;

@Controller
@RequestMapping("rabbitmq")
public class RabbitMQController extends BaseController {

    @Autowired
    private RabbitProducer rabbiProducer;
    @Autowired
    IRabbitMessageService rabbitMessageService;

//    @Resource(name = "myProducer")
//    MyProducer myProducer;

    private static final String QUEUE_KEY = "test_queue";

    @RequestMapping("test")
    @ResponseBody
    public void test(String message){
        try {
            for (int i = 0; i < 1; i ++) {
                rabbiProducer.sendMessage(message);
                Thread.sleep(500);
            }

//            String message = "hello rabbitMQ!";
//            myProducer.sendDataToQueue(QUEUE_KEY,message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     /**
       * Description: rabbit消息列表页面
       * @author: caiwx
       * @date: 2019/11/1 14:51
       * @param:
       * @return:
       */
    @RequestMapping("rabbitMessagePage")
    public ModelAndView rabbitMessagePage() {
        ModelAndView modelAndView = new ModelAndView("mq/rabbit/rabbit-message");
        return modelAndView;
    }

     /**
       * Description: rabbit消息列表
       * @author: caiwx
       * @date: 2019/11/4 10:53
       * @param:
       * @return:
       */
    @RequestMapping("listRabbitMessage/{page}")
    @ResponseBody
    public Result listRabbitMessage(@PathVariable Integer page, MessageDTO messageDTO) throws SerException{
        try {
            messageDTO.setPage(page);
            messageDTO.setLimit(15);
            PagePO<MessageVO> pagePO = rabbitMessageService.listRabbitService(messageDTO);
            return ActResult.data(pagePO);
        } catch (SerException e) {
            logger.error("获取rabbit消息列表失败：", e);
            throw new SerException();
        }
    }

    /**
     * 重新发送消息
     * @author caiwx
     * @date 2019/12/19 17:55
     * @return
     */
    @RequestMapping("reSendRabbitMessage")
    @ResponseBody
    public Result reSendRabbitMessage(MessageDTO messageDTO) throws SerException{
        try {
            rabbitMessageService.reSendRabbitMessage(messageDTO);
            return ActResult.success();
        } catch (SerException e) {
            logger.error("操作失败：", e);
            throw new SerException();
        }
    }


}
