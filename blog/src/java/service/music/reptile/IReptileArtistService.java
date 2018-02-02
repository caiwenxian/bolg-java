package service.music.reptile;

import exception.SerException;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-30 09:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IReptileArtistService {

    /**
     * 爬取歌手
     *
     * @param
     * @return class
     * @version v1
     */
    void reptileArtist(String name) throws SerException;

    /**
     * 爬取热门歌手
     *
     * @param
     * @return class
     * @version v1
     */
    void reptileHotArtist(int limit) throws SerException;

    /**
     * 异步爬取
     *
     * @param
     * @return class
     * @version v1
     */
    void asynReptile(int type, String params[]) throws SerException;
}
