package com.wenxianm.model.po.common;

import com.wenxianm.model.po.base.Po;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础类
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-18 17:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BasePO extends Po implements Serializable {

    private String id;

    @Column(name = "createTime")
    private LocalDateTime createTime;

    @Column(name = "modifyTime")
    private LocalDateTime modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

}
