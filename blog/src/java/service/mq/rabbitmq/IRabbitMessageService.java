package service.mq.rabbitmq;

import exception.SerException;
import model.dto.mq.MessageDTO;
import model.po.common.PagePO;
import model.po.mq.MessagePO;
import model.vo.mq.MessageVO;

/**
   * Description: rabbit消息业务
   * @author: caiwx
   * @date: 2019/11/4 9:55
   * @param:
   * @return:
   */
public interface IRabbitMessageService {

     /**
       * Description: 获取rabbit消息列表
       * @author: caiwx
       * @date: 2019/11/4 9:59
       * @param:
       * @return:
       */
    PagePO<MessageVO> listRabbitService(MessageDTO messageDTO) throws SerException;
}
