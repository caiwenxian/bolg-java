package com.wenxianm.model.po.music;

import com.wenxianm.model.po.common.BasePO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-08 11:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SongListDetailsPO extends BasePO {

    /**
     * 歌单id
     */
    private String songListId;

    /**
     * 歌曲id
     */
    private String songId;

    /**
     * 序号
     */
    private Integer num;


    public SongListDetailsPO() {
    }

    public SongListDetailsPO(String songListId, String songId, Integer num) {
        this.songListId = songListId;
        this.songId = songId;
        this.num = num;
    }

    @Override
    public String toString() {
        return "SongListDetailsPO{" +
                "songListId='" + songListId + '\'' +
                ", songId='" + songId + '\'' +
                ", num=" + num +
                '}';
    }

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
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
