package service.music;

import exception.SerException;
import model.dto.music.TopListDTO;
import model.po.music.SongInfoPO;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 17:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ITopListService {

    void addSong(SongInfoPO po);

    /**
     * 排行榜歌曲爬取
     *
     * @param
     * @return class
     * @version v1
     */
    void reptileSongs(TopListDTO dto) throws SerException;
}
