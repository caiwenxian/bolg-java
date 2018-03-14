package service.music;

import exception.SerException;
import model.dto.common.BaseDTO;
import model.dto.music.MusicDTO;
import model.po.common.PagePO;
import model.po.common.PaginationPO;
import model.po.music.RecommendSongListPO;
import model.po.music.SongInfoPO;
import model.po.music.SongListDetailsPO;
import model.po.music.SongListPO;
import model.vo.music.TopListVO;

import java.util.List;

/**
 * 歌曲基础业务
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ISongService {

    /**
     * 添加歌曲信息
     *
     * @param
     * @return class
     * @version v1
     */
    void addSong(SongInfoPO po) throws SerException;

    /**
     * 根据songId获取歌曲信息
     *
     * @param
     * @return class
     * @version v1
     */
    SongInfoPO getBySongId(String songId) throws SerException;

    /**
     * 更新
     *
     * @param
     * @return class
     * @version v1
     */
    void update(SongInfoPO po) throws SerException;


    /**
     * 添加mp3url
     *
     * @param
     * @return class
     * @version v1
     */
    void addMp3Url(String songId, String mp3Url) throws SerException;

    /**
     * 根据歌名搜索歌曲列表
     *
     * @param
     * @return class
     * @version v1
     */
    List<SongInfoPO> listSongByName(String name) throws SerException;

    /**
     * 根据歌名搜索歌曲列表
     *
     * @param
     * @return class
     * @version v1
     */
    PagePO<SongInfoPO> listSongByNameByPage(String name, int page, int limit) throws SerException;

    /**
     * 获取所有排行榜歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    List<SongInfoPO> listAllTopList() throws SerException;

    /**
     * 根据排行榜id获取排行榜歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    TopListVO listTopListByTopListId(String topListId) throws SerException;

    /**
     * 获取热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    PagePO listHotSong(BaseDTO dto) throws SerException;

    /**
     * 添加歌单
     *
     * @param
     * @return class
     * @version v1
     */
    void addSongList(SongListPO po) throws SerException;

    /**
     * 更新歌单信息
     *
     * @param
     * @return class
     * @version v1
     */
    void updateSongList(String songListId, String trackUpdateTime) throws SerException;

    /**
     * 添加推荐歌单
     *
     * @param
     * @return class
     * @version v1
     */
    void addRecommendSongList(RecommendSongListPO po) throws SerException;

    /**
     * 添加歌单详情
     *
     * @param
     * @return class
     * @version v1
     */
    void addSongListDetails(SongListDetailsPO po) throws SerException;


}
