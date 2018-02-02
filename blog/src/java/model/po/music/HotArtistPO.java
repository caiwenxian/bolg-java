package model.po.music;

import model.po.common.BasePO;

/**
 * 热门歌手
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-31 16:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class HotArtistPO extends BasePO{

    /**
     * 歌手id
     */
    private String artistId;

    /**
     * 序号
     */
    private Integer num;

    public HotArtistPO() {
    }

    public HotArtistPO(String artistId, Integer num) {
        this.artistId = artistId;
        this.num = num;
    }

    @Override
    public String toString() {
        return "HotArtistPO{" +
                "artistId='" + artistId + '\'' +
                ", num=" + num +
                '}';
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
