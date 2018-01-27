package dao.java.music;

import exception.QueryException;
import model.po.music.TopListPO;
import org.springframework.stereotype.Repository;

/**
 * 排行榜
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-27 10:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Repository
public interface ITopListDao {

    /**
     * 添加排行榜
     *
     * @param
     * @return class
     * @version v1
     */
    void add(TopListPO po) throws QueryException;

    /**
     * 获取单个
     *
     * @param
     * @return class
     * @version v1
     */
    TopListPO getByTopListId(String topListId) throws QueryException;

    /**
     * 更新字段trackUpDateTime
     *
     * @param
     * @return class
     * @version v1
     */
    void updateTrackUpDateTime(TopListPO po) throws QueryException;

}
