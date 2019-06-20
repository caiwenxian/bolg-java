package model.po.music;

import model.enums.music.Origin;
import model.po.common.BasePO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 17:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ArtistPO extends BasePO {

    /**
     * 歌手id
     */
    private String artistId;

    /**
     * 名称
     */
    private String name;

    /**
     * 来源
     */
    private String origin;

    public ArtistPO() {
    }

    public ArtistPO(String name) {
        this.name = name;
    }

    public ArtistPO(String artistId, String name, String origin) {
        this.artistId = artistId;
        this.name = name;
        this.origin = origin;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
