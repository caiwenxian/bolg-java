package service.music.reptile;

import exception.SerException;
import model.dto.music.ArtistHotSongDTO;
import model.dto.music.TopListDTO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-30 09:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IReptileSongService {

    /**
     * 排行榜歌曲爬取
     *
     * @param
     * @return class
     * @version v1
     */
    void reptileSongs(TopListDTO dto) throws SerException;

    /**
     * 爬取歌曲url
     * 开启多线程
     * @param
     * @return class
     * @version v1
     */
    void reptileMp3Url(String songId) throws SerException;

    /**
     * 爬取歌手热门歌曲
     *
     * @param dto
     * @return class
     * @version v1
     */
    void reptileHotSongs(ArtistHotSongDTO dto) throws SerException;

    /**
     * 爬取歌曲(模糊搜索)
     *
     * @param
     * @return class
     * @version v1
     */
    void reptileSongs(String name) throws SerException;

    /**
     * 异步爬取
     *
     * @param
     * @return class
     * @version v1
     */
    void asynReptile(int type, String params[]) throws SerException;
}
