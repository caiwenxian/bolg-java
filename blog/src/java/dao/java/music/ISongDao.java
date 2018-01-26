package dao.java.music;

import exception.QueryException;
import model.po.music.SongInfoPO;
import org.springframework.stereotype.Repository;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Repository
public interface ISongDao {

    /**
     * 添加歌曲信息
     *
     * @param
     * @return class
     * @version v1
     */
    void add(SongInfoPO po) throws QueryException;

    /**
     * 更新歌曲信息
     *
     * @param
     * @return class
     * @version v1
     */
    void update(SongInfoPO po) throws QueryException;

    /**
     * 根据songId获取歌曲信息
     *
     * @param
     * @return class
     * @version v1
     */
    SongInfoPO getBySongId(String id) throws QueryException;

}
