package model.po.music;

import model.po.common.BasePO;

/**
 * 歌手热门歌曲
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-29 16:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ArtistHotSongPO extends BasePO{

    /**
     * 歌曲id
     */
    private String songId;

    /**
     * 歌手id
     */
    private String artistId;

    /**
     * 序号
     */
    private Integer num;


    public ArtistHotSongPO() {
    }

    public ArtistHotSongPO(String songId, String artistId, Integer num) {
        this.songId = songId;
        this.artistId = artistId;
        this.num = num;
    }

    @Override
    public String toString() {
        return "ArtistHotSongPO{" +
                "songId='" + songId + '\'' +
                ", artistId='" + artistId + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
