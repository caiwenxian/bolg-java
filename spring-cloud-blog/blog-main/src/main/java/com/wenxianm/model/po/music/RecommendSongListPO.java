package com.wenxianm.model.po.music;

import com.wenxianm.model.po.common.BasePO;

/**
 * 推荐的歌单
 *
 * @Author: [caiwenxian]
 * @Date: [2018-02-08 11:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecommendSongListPO extends BasePO {

    /**
     * 歌单id
     */
    private String songListId;

    /**
     * 序号
     */
    private int num;

    /**
     * 更新时间
     */
    private String trackUpdateTime;

    public RecommendSongListPO() {
    }

    public RecommendSongListPO(String songListId, int num, String trackUpdateTime) {
        this.songListId = songListId;
        this.num = num;
        this.trackUpdateTime = trackUpdateTime;
    }

    @Override
    public String toString() {
        return "recommendSongListPO{" +
                "songListId='" + songListId + '\'' +
                ", num=" + num +
                ", trackUpdateTime='" + trackUpdateTime + '\'' +
                '}';
    }

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTrackUpdateTime() {
        return trackUpdateTime;
    }

    public void setTrackUpdateTime(String trackUpdateTime) {
        this.trackUpdateTime = trackUpdateTime;
    }
}
