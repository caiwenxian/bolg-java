package service.music;

import exception.SerException;
import model.po.music.SongInfoPO;

/**
 * 歌曲基础业务
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ISongService {

    /**
     * 添加歌曲信息
     *
     * @param
     * @return class
     * @version v1
     */
    void addSong(SongInfoPO po) throws SerException;

    /**
     * 根据songId获取歌曲信息
     *
     * @param
     * @return class
     * @version v1
     */
    SongInfoPO getBySongId(String songId) throws SerException;

    /**
     * 更新
     *
     * @param
     * @return class
     * @version v1
     */
    void update(SongInfoPO po) throws SerException;


    /**
     * 添加mp3url
     *
     * @param
     * @return class
     * @version v1
     */
    void addMp3Url(String songId, String mp3Url) throws SerException;
}
