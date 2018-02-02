package service.music.impl;

import dao.java.music.IArtistHotSongDao;
import model.po.music.ArtistHotSongPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.music.IArtistHotSongService;
import utils.RandomUtil;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-29 15:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class ArtistHotSongServiceImpl implements IArtistHotSongService {

//    @Autowired
//    ISongService songService;
//    @Autowired
//    IArtistService artistService;
    @Autowired
    IArtistHotSongDao artistHotSongDao;



    /**
     * 添加热门歌曲
     * @param po
     */
    @Override
    public void addArtistHotSong(ArtistHotSongPO po) {
        ArtistHotSongPO old = artistHotSongDao.getBySongIdAndArtistId(po.getSongId(), po.getArtistId());
        if (old != null) {
            return;
        }
        po.setId(RandomUtil.getUid());
        artistHotSongDao.addArtistHotSong(po);
    }
}
