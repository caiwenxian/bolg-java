package dao.java.music;

import exception.QueryException;
import model.po.music.TopListDetailsPO;
import model.po.music.TopListPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 排行榜详细
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-27 10:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Repository
public interface ITopListDetailsDao {

    /**
     * 添加排行榜详细
     *
     * @param
     * @return class
     * @version v1
     */
    void add(TopListDetailsPO po) throws QueryException;

    /**
     * 更新
     *
     * @param
     * @return class
     * @version v1
     */
    void update(TopListDetailsPO po) throws QueryException;

    /**
     * 删除
     *
     * @param
     * @return class
     * @version v1
     */
    void delete(@Param("topListId") String topListId) throws QueryException;

    /**
     * 获取单个
     *
     * @param
     * @return class
     * @version v1
     */
    TopListDetailsPO getByTopListIdAndSongId(@Param("topListId") String topListId, @Param("songId") String songId) throws QueryException;

}
