package dao.java.music;

import exception.QueryException;
import model.po.music.ArtistHotSongPO;
import model.po.music.SongInfoPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.xml.ws.soap.Addressing;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-29 16:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Repository
public interface IArtistHotSongDao {

    /**
     * 添加歌手热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    void addArtistHotSong(ArtistHotSongPO po) throws QueryException;

    /**
     * 获取单个实体
     *
     * @param
     * @return class
     * @version v1
     */
    ArtistHotSongPO getBySongIdAndArtistId(@Param("songId") String songId, @Param("artistId") String artistId) throws QueryException;

    /**
     * 根据歌手id获取热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    List<SongInfoPO> listHotSongByArtistId(@Param("artistId") String artistId) throws QueryException;
}
