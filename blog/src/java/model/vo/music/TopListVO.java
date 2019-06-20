package model.vo.music;

import model.po.music.SongInfoPO;

import java.util.List;

/**
 * 排行榜
 *
 * @Author: [caiwenxian]
 * @Date: [2018-02-07 14:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TopListVO {

    /**
     * 名称
     */
    private String name;

    /**
     * id
     */
    private String toplistId;

    /**
     * 歌曲对象
     */
    private List<SongInfoPO> songInfoPO;

    public TopListVO() {
    }

    public TopListVO(String name, String toplistId, List<SongInfoPO> songInfoPO) {
        this.name = name;
        this.toplistId = toplistId;
        this.songInfoPO = songInfoPO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToplistId() {
        return toplistId;
    }

    public void setToplistId(String toplistId) {
        this.toplistId = toplistId;
    }

    public List<SongInfoPO> getSongInfoPO() {
        return songInfoPO;
    }

    public void setSongInfoPO(List<SongInfoPO> songInfoPO) {
        this.songInfoPO = songInfoPO;
    }
}
