package com.wenxianm.model.po.mq;

import com.wenxianm.model.po.base.annotation.TableName;
import com.wenxianm.model.po.common.BasePO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
   * Description: rabbit消息实体
   * @author: caiwx
   * @date: 2019/10/29 14:55
   * @param:
   * @return:
   */
 @TableName(name = "t_rabbit_message")
public class MessagePO extends BasePO implements Serializable {

    private static final long serialVersionUID = -9018118238271043366L;

    /** 消息类型
     * 0: 测试消息
     * 1：爬取并存储歌曲详情（）
     * */
    private String type;

    /** 消息状态：0：初始状态、1：消费成功、-1、消费失败*/
    private int status;

    /** 消息主体，json串*/
    private String data;

    /** rabbit产生的消息id */
    private String correlationDataId;

    /** 发送时间 */
    private LocalDateTime sendTime;

    /** 消费时间 */
    private LocalDateTime consumeTime;

    /** 消费后返回消息 */
    private String returnMsg;

    public MessagePO() {}

     public MessagePO(String type, int status, String data) {
         this.type = type;
         this.status = status;
         this.data = data;
     }

     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

     public String getData() {
         return data;
     }

     public void setData(String data) {
         this.data = data;
     }

     public int getStatus() {
         return status;
     }

     public void setStatus(int status) {
         this.status = status;
     }

     public String getCorrelationDataId() {
         return correlationDataId;
     }

     public void setCorrelationDataId(String correlationDataId) {
         this.correlationDataId = correlationDataId;
     }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public LocalDateTime getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(LocalDateTime consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
