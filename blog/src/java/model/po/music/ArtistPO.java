package model.po.music;

import model.po.common.BasePO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 17:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ArtistPO extends BasePO{

    /**
     * 歌手id
     */
    private String artistId;

    /**
     * 名称
     */
    private String name;

    public ArtistPO() {
    }

    public ArtistPO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ArtistPO{" +
                "name='" + name + '\'' +
                '}';
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
}
