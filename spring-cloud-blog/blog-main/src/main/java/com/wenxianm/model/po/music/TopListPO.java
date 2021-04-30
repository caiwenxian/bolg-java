package com.wenxianm.model.po.music;

import com.wenxianm.model.po.common.BasePO;

import java.time.LocalDateTime;

/**
 * 排行榜
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-27 10:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TopListPO extends BasePO {

    /**
     * 排行榜名称
     */
    private String name;

    /**
     * 排行榜id
     */
    private String topListId;

    /**
     * 来源
     */
    private String origin;

    /**
     * 官方排行榜更新时间(时间戳)
     */
    private String trackUpdateTime;

    public TopListPO() {
    }

    public TopListPO(String name, String topListId, String origin, String trackUpdateTime) {
        this.name = name;
        this.topListId = topListId;
        this.origin = origin;
        this.trackUpdateTime = trackUpdateTime;
    }

    @Override
    public String toString() {
        return "TopListPO{" +
                "name='" + name + '\'' +
                ", topListId='" + topListId + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopListId() {
        return topListId;
    }

    public void setTopListId(String topListId) {
        this.topListId = topListId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTrackUpdateTime() {
        return trackUpdateTime;
    }

    public void setTrackUpdateTime(String trackUpdateTime) {
        this.trackUpdateTime = trackUpdateTime;
    }


}
