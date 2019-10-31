package service.mq.rabbitmq;

import java.io.Serializable;

 /**
   * Description: rabbit消息实体
   * @author: caiwx
   * @date: 2019/10/29 14:55
   * @param:
   * @return:
   */
public class MessagePO implements Serializable {

    /** 消息类型
     * 1：爬取并存储歌曲详情（）
     * */
    private String type;

    /** 消息唯一标识 */
    private String id;

    private Object data;

    public MessagePO() {}

    public MessagePO(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }
 }
