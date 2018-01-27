package service.music.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.java.music.ITopListDao;
import dao.java.music.ITopListDetailsDao;
import exception.SerException;
import model.constant.NetseaseUrl;
import model.dto.music.TopListDTO;
import model.enums.music.Origin;
import model.enums.music.TopListType;
import model.po.music.ArtistPO;
import model.po.music.SongInfoPO;
import model.po.music.TopListDetailsPO;
import model.po.music.TopListPO;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.music.IArtistService;
import service.music.ISongService;
import service.music.ITopListService;
import utils.HttpClientHelper;
import utils.RandomUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 17:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class TopListServiceImpl implements ITopListService{

    @Autowired
    IArtistService artistService;
    @Autowired
    ISongService songService;
    @Autowired
    ITopListDao topListDao;
    @Autowired
    ITopListDetailsDao topListDetailsDao;

    public void addSong(SongInfoPO po) {

    }

    public void reptileSongs(TopListDTO dto) throws SerException{

        StringBuffer url = new StringBuffer();
        url.append(NetseaseUrl.API);
        String url2 = TopListType.getUrl(dto.getTopListType().getCode());
        url.append(url2);
        url.append("&limit=" + dto.getLimit());
        url.append("&order=new");

        String result = HttpClientHelper.sendGet(url.toString(), null, "UTF-8");

        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject object = (JSONObject) jsonObject.get("result");
        JSONArray songs = object.getJSONArray("tracks");

        //保存排行榜
        String topListId = object.getString("id");
        String topListName = object.getString("name");
        String trackUpdateTime = object.getString("trackUpdateTime");

        TopListPO old = topListDao.getByTopListId(topListId);
        if (old != null && trackUpdateTime.equals(old.getTrackUpdateTime())) {
            System.out.println("官方排行榜暂未更新");
            throw new SerException("官方排行榜暂未更新");
        }
        addTopList(new TopListPO(topListName, topListId, Origin.WANG_YI.name(), trackUpdateTime));

        Iterator<Object> it = songs.iterator();
        int num = 1;
        while (it.hasNext()) {
            JSONObject song = (JSONObject) it.next();
            JSONArray artist = song.getJSONArray("artists");
            //保存歌手
            ArtistPO artistPO = new ArtistPO();
            artistPO.setArtistId(artist.getJSONObject(0).getString("id"));
            artistPO.setName(artist.getJSONObject(0).getString("name"));
            artistService.addArtist(artistPO);

            //保存歌曲
            SongInfoPO po = new SongInfoPO();
            String songId = song.getString("id");
            po.setSongId(songId);
            po.setName(song.getString("name"));
            po.setArtistId(artistPO.getArtistId());
            songService.addSong(po);

            //爬取歌曲url
            songService.reptileMp3Url(po.getSongId());

            //排行榜-歌曲关联
            addTopListDetails(new TopListDetailsPO(topListId, songId, num));
            num ++;
        }
        System.out.println("爬取列表完成" + Calendar.MILLISECOND);
    }

    void addTopList(TopListPO po) {
        TopListPO old = topListDao.getByTopListId(po.getTopListId());
        if (old == null) {
            po.setId(RandomUtil.getUid());
            topListDao.add(po);
            return;
        }
        old.setTrackUpdateTime(po.getTrackUpdateTime());
        topListDao.updateTrackUpDateTime(old);
    }

    /**
     *
     * 排行榜-歌曲关联
     * @param
     * @return class
     * @version v1
     */
    void addTopListDetails(TopListDetailsPO po) {
        TopListDetailsPO old = topListDetailsDao.getByTopListIdAndSongId(po.getTopListId(), po.getSongId());
        if (null != old) {
            System.out.println("排行榜-歌曲关联已存在");
            return;
        }
        po.setId(RandomUtil.getUid());
        topListDetailsDao.add(po);
    }
}
