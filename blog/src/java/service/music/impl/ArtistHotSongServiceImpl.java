package service.music.impl;

import dao.java.music.IArtistHotSongDao;
import exception.SerException;
import model.dto.music.ArtistHotSongDTO;
import model.po.music.ArtistHotSongPO;
import model.po.music.SongInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.music.IArtistHotSongService;
import service.music.reptile.IReptileArtistService;
import service.music.reptile.IReptileSongService;
import utils.RandomUtil;

import java.util.List;

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
    @Autowired
    IReptileArtistService reptileArtistService;
    @Autowired
    IReptileSongService reptileSongService;



    @Override
    public void addArtistHotSong(ArtistHotSongPO po) {
        ArtistHotSongPO old = artistHotSongDao.getBySongIdAndArtistId(po.getSongId(), po.getArtistId());
        if (old != null) {
            return;
        }
        po.setId(RandomUtil.getUid());
        artistHotSongDao.addArtistHotSong(po);
    }

    @Override
    public List<SongInfoPO> listHotSongByArtistId(String artistId) throws SerException {
        List<SongInfoPO> list = artistHotSongDao.listHotSongByArtistId(artistId);
        if (list.size() == 0) { //本地无数据，爬取官网
            String ids[] = new String[] {artistId};
            reptileSongService.asynReptile(2, new Object[] {new ArtistHotSongDTO(ids)});
            try {
                Thread.currentThread().sleep(3000);
                list = artistHotSongDao.listHotSongByArtistId(artistId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
