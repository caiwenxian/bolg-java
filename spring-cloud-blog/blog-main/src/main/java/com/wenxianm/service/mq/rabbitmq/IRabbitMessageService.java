package com.wenxianm.service.mq.rabbitmq;

import com.wenxianm.exception.SerException;
import com.wenxianm.model.dto.mq.MessageDTO;
import com.wenxianm.model.po.common.PagePO;
import com.wenxianm.model.po.mq.MessagePO;
import com.wenxianm.model.vo.mq.MessageVO;

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

    /**
     * 重新发送消息
     * @author caiwx
     * @date 2019/12/19 17:56
     * @return
     */
    void reSendRabbitMessage(MessageDTO messageDTO) throws SerException;
}
