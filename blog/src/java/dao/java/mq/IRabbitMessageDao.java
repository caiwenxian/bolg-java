package dao.java.mq;

import exception.SerException;
import model.dto.mq.MessageDTO;
import model.po.mq.MessagePO;

import java.util.List;

public interface IRabbitMessageDao {

     /**
       * Description: 添加mq消息
       * @author: caiwx
       * @date: 2019/10/31 14:41
       * @param:
       * @return:
       */
    void addMessage(MessagePO messagePO) throws SerException;

     /**
       * Description: 更新消息状态
       * @author: caiwx
       * @date: 2019/10/31 16:26
       * @param:
       * @return:
       */
    void updateMessageStatus(MessagePO messagePO) throws SerException;

     /**
       * Description: 计算rabbit消息总数
       * @author: caiwx
       * @date: 2019/11/4 10:05
       * @param:
       * @return:
       */
    int countMessage(MessageDTO messageDTO) throws SerException;

     /**
       * Description: 获取消息列表
       * @author: caiwx
       * @date: 2019/11/4 10:06
       * @param:
       * @return:
       */
    List<MessagePO> listMessage(MessageDTO messageDTO) throws SerException;

    /**
     * 获取消息
     * @author caiwx
     * @date 2019/12/19 17:57
     * @return
     */
    MessagePO getMessage(MessageDTO messageDTO) throws SerException;


}
