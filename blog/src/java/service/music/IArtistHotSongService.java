package service.music;

import exception.SerException;
import model.dto.music.ArtistHotSongDTO;
import model.po.music.ArtistHotSongPO;

/**
 * 歌手的热门歌曲
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-29 15:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IArtistHotSongService {


    /**
     * 添加歌手热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    void addArtistHotSong(ArtistHotSongPO po) throws SerException;

}
