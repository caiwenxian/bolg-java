package com.wenxianm.model.dto.mq;

import com.wenxianm.model.dto.common.BaseDTO;

public class MessageDTO extends BaseDTO {

    private String id;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
