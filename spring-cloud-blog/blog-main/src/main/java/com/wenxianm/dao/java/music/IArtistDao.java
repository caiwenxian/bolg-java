package com.wenxianm.dao.java.music;

import com.wenxianm.exception.QueryException;
import com.wenxianm.model.po.music.ArtistPO;
import com.wenxianm.model.po.music.HotArtistPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手业务操作
 *
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

    /**
     * 添加热门歌手
     *
     * @param
     * @return class
     * @version v1
     */
    void addHotArtist(List<HotArtistPO> list) throws QueryException;

    /**
     * 根据姓名获取歌手列表
     *
     * @param name
     * @return class
     * @version v1
     */
    List<ArtistPO> listArtistByName(@Param("name") String name) throws QueryException;


}
