package service.music;

import exception.SerException;
import model.dto.music.TopListDTO;
import model.po.music.SongInfoPO;
import model.po.music.TopListDetailsPO;
import model.po.music.TopListPO;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 17:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ITopListService {

//    void addSong(SongInfoPO po);


    /**
     * 添加排行榜
     *
     * @param
     * @return class
     * @version v1
     */
    void addTopList(TopListPO po) throws SerException;

    /**
     * 添加排行榜-歌曲关联
     *
     * @param
     * @return class
     * @version v1
     */
    void addTopListDetails(TopListDetailsPO po) throws SerException;

    /**
     * 根据排行榜id获取排行榜
     *
     * @param
     * @return class
     * @version v1
     */
    TopListPO getByTopListId(String topListId) throws SerException;

    /**
     * 删除排行榜-歌曲关联
     *
     * @param
     * @return class
     * @version v1
     */
    void deleteTopListDetails(String topListId) throws SerException;

}
