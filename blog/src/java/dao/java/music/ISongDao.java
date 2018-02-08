package dao.java.music;

import exception.QueryException;
import model.po.music.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    SongInfoPO getBySongId(@Param("songId") String songId) throws QueryException;

    /**
     * 根据歌名模糊查询
     *
     * @param
     * @return class
     * @version v1
     */
    List<SongInfoPO> listSongByName(@Param("name") String name) throws QueryException;

    /**
     * 根据歌名模糊查询总条数
     *
     * @param
     * @return class
     * @version v1
     */
    int countSongByName(@Param("name") String name) throws QueryException;

    /**
     * 获取排行榜歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    List<SongInfoPO> listTopList(@Param("ids") List ids) throws QueryException;

    /**
     * 添加歌单
     *
     * @param
     * @return classs
     * @version v1
     */
    void addSongList(SongListPO po) throws QueryException;

    /**
     * 更新歌单信息
     *
     * @param
     * @return class
     * @version v1
     */
    void updateSongList(@Param("songListId") String songListId, @Param("trackUpdateTime") String trackUpdateTime) throws QueryException;

    /**
     * 根据歌单id获取单个歌单
     *
     * @param
     * @return class
     * @version v1
     */
    SongListPO getSongListBySongListId(String songListId) throws QueryException;

    /**
     * 添加推荐歌单
     *
     * @param
     * @return class
     * @version v1
     */
    void addRecommendSongList(RecommendSongListPO po) throws QueryException;

    /**
     * 根据歌单id获取单个推荐歌单
     *
     * @param
     * @return class
     * @version v1
     */
    RecommendSongListPO getRecommendSongListBySongListId(String songListId) throws QueryException;

    /**
     * 添加歌单详情（与歌曲关联）
     *
     * @param
     * @return class
     * @version v1
     */
    void addSongListDetails(SongListDetailsPO po) throws QueryException;

    /**
     * 获取歌单歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    SongListDetailsPO getSongListDetails(@Param("songListId") String songListId, @Param("songId") String songId) throws QueryException;

}
