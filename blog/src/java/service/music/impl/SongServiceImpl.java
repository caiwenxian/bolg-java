package service.music.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.java.music.ISongDao;
import exception.ErrorMessage;
import exception.SerException;
import model.constant.NetseaseUrl;
import model.enums.music.TopListType;
import model.po.music.SongInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import service.music.ISongService;
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
public class SongServiceImpl implements ISongService {


    @Autowired
    ISongDao songDao;

    public void addSong(SongInfoPO po) throws SerException {
        po.setId(RandomUtil.getUid());
        songDao.add(po);
    }

    public void addMp3Url(String songId, String mp3Url) throws SerException {
        SongInfoPO po = songDao.getBySongId(songId);
        if (null == po) {
            throw new SerException(ErrorMessage.NOT_FOUND);
        }
        po.setMp3Url(mp3Url);
        songDao.update(po);
    }

    public void reptileMp3Url(String songId) throws SerException {
        Thread thread = new Thread(new Reptilep(songId));
        thread.start();
    }

    class Reptilep implements Runnable {

        String songId;

        public Reptilep(String songId) {
            this.songId = songId;
        }

        public void run() {
            StringBuffer url = new StringBuffer();
            url.append(NetseaseUrl.API);
            url.append("/api/song/enhance/player/url/");
            url.append("?id=" + this.songId);
            url.append("&ids=[" + this.songId + "]");
            url.append("&br=3200000");

            String result = HttpClientHelper.sendPost(url.toString(), null, "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray songs = jsonObject.getJSONArray("data");
            JSONObject song = songs.getJSONObject(0);
            String map3Url = song.getString("url");
            String size = song.getString("size");
            String type = song.getString("type");

            try {
                addMp3Url(this.songId, map3Url);
            } catch (SerException e) {
                e.printStackTrace();
            }
        }
    }
}
