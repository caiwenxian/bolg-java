package service.music.reptile;

import com.alibaba.fastjson.JSONObject;
import exception.SerException;
import model.dto.music.ArtistHotSongDTO;
import model.dto.music.MusicDTO;
import model.dto.music.SongListDTO;
import model.dto.music.TopListDTO;
import model.enums.music.SongListType;

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
     *
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
    void asynReptile(int type, Object params[]) throws SerException;

    /**
     * 爬取推荐歌单
     *
     * @param limit 条数
     * @return class
     * @version v1s
     */
    void reptileRecommendSongList(Integer offset, Integer limit) throws SerException;

    /**
     * 爬取歌单（模糊搜索）
     *
     * @param
     * @return class
     * @version v1
     */
    void reptileSongList(MusicDTO dto) throws SerException;

    /**
     * 根据类别获取歌单
     *
     * @param
     * @return class
     * @version v1
     */
    void reptileSongListByType(SongListDTO dto) throws SerException;

     /**
       * Description: 爬取歌曲，并保存歌曲和歌单的关系
       * @author: caiwx
       * @date: 2019/10/30 10:47
       * @param:
       * @return:
       */
    void saveSongWithSongList(JSONObject jsonObject) throws SerException;

}
