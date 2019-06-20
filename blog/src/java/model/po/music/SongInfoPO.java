package model.po.music;

import model.po.common.BasePO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 16:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SongInfoPO extends BasePO {

    /**
     * 歌曲id
     */
    private String songId;

    /**
     * 名称
     */
    private String name;

    /**
     * 歌手id
     */
    private String artistId;

    /**
     * 歌词id
     */
    private String lyricId;

    /**
     * map3Url
     */
    private String mp3Url;

    /**
     * 来源
     */
    private String origin;

    /**
     * 序号
     */
    private Integer num;


    public SongInfoPO() {
    }

    public SongInfoPO(String songId, String name, String artistId, String lyricId, String mp3Url, String origin, Integer num) {
        this.songId = songId;
        this.name = name;
        this.artistId = artistId;
        this.lyricId = lyricId;
        this.mp3Url = mp3Url;
        this.origin = origin;
        this.num = num;
    }

    @Override
    public String toString() {
        return "SongInfoPO{" +

                ", songId='" + songId + '\'' +
                ", name='" + name + '\'' +
                ", artistId='" + artistId + '\'' +
                ", lyricId='" + lyricId + '\'' +
                '}';
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getLyricId() {
        return lyricId;
    }

    public void setLyricId(String lyricId) {
        this.lyricId = lyricId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
