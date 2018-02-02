package service.music.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.java.music.ISongDao;
import exception.ErrorMessage;
import exception.SerException;
import model.constant.NetseaseUrl;
import model.enums.music.TopListType;
import model.po.music.SongInfoPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.music.ISongService;
import service.music.reptile.IReptileSongService;
import utils.HttpClientHelper;
import utils.RandomUtil;

import java.util.UUID;

/**
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class SongServiceImpl implements ISongService {


    @Autowired
    ISongDao songDao;
    @Autowired
    IReptileSongService reptileMp3Url;

    @Override
    public void addSong(SongInfoPO po) throws SerException {
        SongInfoPO old = songDao.getBySongId(po.getSongId());
        if (null != old) {
//            throw new SerException(ErrorMessage.MUSIC_IS_EXIST);
            System.out.println(ErrorMessage.MUSIC_IS_EXIST);
            if (StringUtils.isNotBlank(old.getMp3Url())) {
                return;
            }
            reptileMp3Url.reptileMp3Url(old.getSongId());
        }
        po.setId(RandomUtil.getUid());
        songDao.add(po);
        //爬取mp3url
        reptileMp3Url.reptileMp3Url(po.getSongId());
    }

    @Override
    public SongInfoPO getBySongId(String songId) throws SerException {
        SongInfoPO po = songDao.getBySongId(songId);
        return po;
    }

    @Override
    public void update(SongInfoPO po) throws SerException {
        songDao.update(po);
    }

    @Override
    public void addMp3Url(String songId, String mp3Url) throws SerException {
        SongInfoPO po = this.getBySongId(songId);
        if (null == po) {
//            throw new SerException(ErrorMessage.NOT_FOUND);
            System.out.println(ErrorMessage.MUSIC_NOT_EXIST);
            return;
        }
        po.setMp3Url(mp3Url);
        this.update(po);
    }
}
