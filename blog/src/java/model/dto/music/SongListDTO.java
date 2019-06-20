package model.dto.music;

import model.dto.common.BaseDTO;
import model.enums.music.SongListType;

/**
 * 歌单dto
 *
 * @Author: [caiwenxian]
 * @Date: [2018-02-09 15:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SongListDTO extends BaseDTO {

    /**
     * "new":最新
     * "hot":最热
     */
    private String order;

    /**
     * 类别
     */
    private SongListType songListType;

    public SongListDTO() {
    }

    public SongListDTO(String order, SongListType songListType) {
        this.order = order;
        this.songListType = songListType;
    }

    @Override
    public String toString() {
        return "SongListDTO{" +
                "order='" + order + '\'' +
                ", songListType=" + songListType +
                '}';
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public SongListType getSongListType() {
        return songListType;
    }

    public void setSongListType(SongListType songListType) {
        this.songListType = songListType;
    }
}
