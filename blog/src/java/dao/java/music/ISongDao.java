package dao.java.music;

import exception.QueryException;
import model.po.music.SongInfoPO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ISongDao {

    /**
     * 添加歌曲信息
     *
     * @param
     * @return class
     * @version v1
     */
    void add(SongInfoPO po) throws QueryException;

}
