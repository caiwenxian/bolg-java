package com.wenxianm.model.po.music;

import com.wenxianm.model.po.common.BasePO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-27 10:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TopListDetailsPO extends BasePO {

    /**
     * topListId
     */
    private String topListId;

    /**
     * songId
     */
    private String songId;

    /**
     * num 序号
     */
    private Integer num;


    public TopListDetailsPO() {
    }

    public TopListDetailsPO(String topListId, String songId, Integer num) {
        this.topListId = topListId;
        this.songId = songId;
        this.num = num;
    }

    @Override
    public String toString() {
        return "TopListDetailsPO{" +
                "topListId='" + topListId + '\'' +
                ", songId='" + songId + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public String getTopListId() {
        return topListId;
    }

    public void setTopListId(String topListId) {
        this.topListId = topListId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
