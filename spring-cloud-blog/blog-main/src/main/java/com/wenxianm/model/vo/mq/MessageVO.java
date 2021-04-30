package com.wenxianm.model.vo.mq;

import com.wenxianm.model.vo.BaseVO;

import java.time.LocalDateTime;

public class MessageVO extends BaseVO {

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

    /**消息状态*/
    private String statusStr;

    /** 消费后返回消息 */
    private String returnMsg;


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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        //消息状态：0：初始状态、1：消费成功、-1、消费失败

        switch (this.status) {
            case 0:
                return "初始状态";
            case 1:
                return "消费成功";
            case -1:
                return "消费失败";
        }
        return null;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
