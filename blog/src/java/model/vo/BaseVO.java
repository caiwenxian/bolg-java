package model.vo;

import java.time.LocalDateTime;

/**
 * 基础vo
 *
 * @Author: [caiwenxian]
 * @Date: [2018-04-08 10:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class BaseVO {

    private String id;

    private LocalDateTime createTime;

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
