package model.dto.music;

import model.dto.common.BaseDTO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-29 16:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ArtistHotSongDTO extends BaseDTO {

    /**
     * 歌手id集合
     */
    private String[] ids;

    private String name;

    public ArtistHotSongDTO() {
    }

    public ArtistHotSongDTO(String[] ids) {
        this.ids = ids;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ArtistSongDTO{" +
                "ids='" + ids + '\'' +
                '}';
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
