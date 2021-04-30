package com.wenxianm.service.music.impl;

import com.wenxianm.dao.java.music.IArtistHotSongDao;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.dto.music.ArtistHotSongDTO;
import com.wenxianm.model.po.music.ArtistHotSongPO;
import com.wenxianm.model.po.music.SongInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenxianm.service.common.impl.BaseServiceImpl;
import com.wenxianm.service.music.IArtistHotSongService;
import com.wenxianm.service.music.reptile.IReptileArtistService;
import com.wenxianm.service.music.reptile.IReptileSongService;
import com.wenxianm.utils.RandomUtil;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-29 15:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class ArtistHotSongServiceImpl extends BaseServiceImpl implements IArtistHotSongService {

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
            String ids[] = new String[]{artistId};
            reptileSongService.asynReptile(2, new Object[]{new ArtistHotSongDTO(ids)});
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
