package com.wenxianm.service.music;

import com.wenxianm.exception.SerException;
import com.wenxianm.model.dto.music.ArtistHotSongDTO;
import com.wenxianm.model.po.music.ArtistHotSongPO;
import com.wenxianm.model.po.music.SongInfoPO;

import java.util.List;

/**
 * 歌手的热门歌曲
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-29 15:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IArtistHotSongService {


    /**
     * 添加歌手热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    void addArtistHotSong(ArtistHotSongPO po) throws SerException;

    /**
     * 根据歌手id获取热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    List<SongInfoPO> listHotSongByArtistId(String artistId) throws SerException;

}
