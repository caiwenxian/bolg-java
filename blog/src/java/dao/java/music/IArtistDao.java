package dao.java.music;

import exception.QueryException;
import model.po.music.ArtistPO;

/**
 * 歌手业务操作
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */


public interface IArtistDao {

    /**
     * 保存歌手
     *
     * @param
     * @return class
     * @version v1
     */
    void addArtist(ArtistPO po) throws QueryException;
}
