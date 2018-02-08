package model.po.music;

import model.po.common.BasePO;

/**
 * 歌单
 * @Author: [caiwenxian]
 * @Date: [2018-02-08 10:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SongListPO extends BasePO{

    /**
     * 歌单id
     */
    private String songListId;

    /**
     * 歌单名称
     */
    private String name;

    /**
     * 图片url
     */
    private String picUrl;

    /**
     * 播放量
     */
    private Integer playCount;

    /**
     * 来源
     */
    private String origin;

    /**
     * 更新时间
     */
    private String trackUpdateTime;



    public SongListPO(){}

    public SongListPO(String songListId, String name, String picUrl, Integer playCount, String origin, String trackUpdateTime) {
        this.songListId = songListId;
        this.name = name;
        this.picUrl = picUrl;
        this.playCount = playCount;
        this.origin = origin;
        this.trackUpdateTime = trackUpdateTime;
    }

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
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
