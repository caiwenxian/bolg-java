package dao.java.music;

import exception.QueryException;
import model.po.music.ArtistPO;
import org.springframework.stereotype.Repository;

/**
 * 歌手业务操作
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Repository
public interface IArtistDao {

    /**
     * 保存歌手
     *
     * @param
     * @return class
     * @version v1
     */
    void addArtist(ArtistPO po) throws QueryException;

    /**
     * 根据歌手id获取歌手信息
     *
     * @param
     * @return class
     * @version v1
     */
    ArtistPO getArtistByArtistId(String id) throws QueryException;
}
