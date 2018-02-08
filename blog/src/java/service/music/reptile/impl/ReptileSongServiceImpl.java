package service.music.reptile.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import exception.SerException;
import model.constant.NetseaseUrl;
import model.dto.music.ArtistHotSongDTO;
import model.dto.music.TopListDTO;
import model.enums.music.Origin;
import model.enums.music.TopListType;
import model.po.music.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.music.IArtistHotSongService;
import service.music.IArtistService;
import service.music.ISongService;
import service.music.ITopListService;
import service.music.reptile.IReptileSongService;
import utils.HttpClientHelper;

import javax.json.JsonObject;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-30 09:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class ReptileSongServiceImpl implements IReptileSongService{

    @Autowired
    ISongService songService;
    @Autowired
    IArtistService artistService;
    @Autowired
    ITopListService topListService;
    @Autowired
    IArtistHotSongService artistHotSongService;

    public void reptileSongs(TopListDTO dto) throws SerException {

        StringBuffer url = new StringBuffer();
        url.append(NetseaseUrl.API);
        url.append("/api/playlist/detail?id=");
        String url2 = TopListType.getId(dto.getTopListType().getCode());
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

        TopListPO old = topListService.getByTopListId(topListId);
        if (old != null && trackUpdateTime.equals(old.getTrackUpdateTime())) {
            System.out.println("官方排行榜暂未更新");
            return;
//            throw new SerException("官方排行榜暂未更新");
        }
        if (old != null && !trackUpdateTime.equals(old.getTrackUpdateTime())) {
            System.out.println("官方排行榜已更新");
            //删除旧排行榜歌曲
            topListService.deleteTopListDetails(old.getTopListId());
        }
        topListService.addTopList(new TopListPO(topListName, topListId, Origin.WANG_YI.name(), trackUpdateTime));

        Iterator<Object> it = songs.iterator();
        int num = 1;
        while (it.hasNext()) {
            JSONObject song = (JSONObject) it.next();
            JSONArray artist = song.getJSONArray("artists");
            //保存歌手
            ArtistPO artistPO = new ArtistPO();
            artistPO.setArtistId(artist.getJSONObject(0).getString("id"));
            artistPO.setName(artist.getJSONObject(0).getString("name"));
            artistPO.setOrigin(Origin.WANG_YI.getName());
            artistService.addArtist(artistPO);

            //保存歌曲
            SongInfoPO po = new SongInfoPO();
            String songId = song.getString("id");
            po.setSongId(songId);
            po.setName(song.getString("name"));
            po.setArtistId(artistPO.getArtistId());
            po.setOrigin(Origin.WANG_YI.getName());
            songService.addSong(po);

            //爬取歌曲url
//            songService.reptileMp3Url(po.getSongId());

            //排行榜-歌曲关联
            topListService.addTopListDetails(new TopListDetailsPO(topListId, songId, num));
            num ++;
        }
        System.out.println("爬取排行榜列表完成" + Calendar.MILLISECOND);
    }

    @Override
    public void reptileMp3Url(String songId) throws SerException {
        Thread thread = new Thread(new ReptilepMp3(songId));
        thread.start();
    }

    @Override
    public void reptileHotSongs(ArtistHotSongDTO dto) throws SerException{

        if (dto.getIds().length == 0) {
            System.out.println("歌手ids集合为空");
            return;
        }
        for (String id : dto.getIds()) {
            StringBuffer url = new StringBuffer();
            url.append("http://music.163.com/api/artist/");
            url.append(id);
            url.append("?limit=" + dto.getLimit());
            url.append("&offset=0");
            String result = HttpClientHelper.sendGet(url.toString(), null, "UTF8");
            JSONObject jsonObject = JSONObject.parseObject(result);
            String code = jsonObject.getString("code");
            if (!"200".equals(code)) {
                continue;
            }
            JSONObject artist = (JSONObject) jsonObject.get("artist");  //歌手信息
            JSONArray songs = jsonObject.getJSONArray("hotSongs");  //热门歌曲

            //保存歌手
            ArtistPO artistPO = new ArtistPO();
            String artistId = artist.getString("id");
            artistPO.setArtistId(artistId);
            artistPO.setName(artist.getString("name"));
            artistPO.setOrigin(Origin.WANG_YI.getName());
            artistService.addArtist(artistPO);

            //保存歌曲
            int num = 1;
            for (Object song1 : songs) {
                JSONObject song = (JSONObject) song1;
                SongInfoPO po = new SongInfoPO();
                String songId = song.getString("id");
                po.setSongId(songId);
                po.setName(song.getString("name"));
                po.setArtistId(artistPO.getArtistId());
                po.setOrigin(Origin.WANG_YI.getName());
                songService.addSong(po);
                //爬取歌曲url
//                songService.reptileMp3Url(po.getSongId());
                //歌手-热门歌曲关联
                ArtistHotSongPO artistHotSongPO = new ArtistHotSongPO(songId, artistId, num);
                artistHotSongService.addArtistHotSong(artistHotSongPO);

                num ++;
            }
            num = 1;

        }
    }

    @Override
    public void reptileSongs(String name) throws SerException {
        StringBuffer url = new StringBuffer();
        url.append(NetseaseUrl.API);
        url.append("/api/search/pc/");
        url.append("?s=" + name);
        url.append("&limit=10");
        url.append("&type=1");
        url.append("&offset=0");

        String result = HttpClientHelper.sendPost(url.toString(), null, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject object = (JSONObject) jsonObject.get("result");
        JSONArray songs = object.getJSONArray("songs");
        int num = 1;
        for (Object song1 : songs) {
            JSONObject song = (JSONObject) song1;
            SongInfoPO songInfoPO = new SongInfoPO();
            songInfoPO.setSongId(song.getString("id"));
            songInfoPO.setName(song.getString("name"));
            songInfoPO.setNum(num);
            songInfoPO.setOrigin(Origin.WANG_YI.getName());
            songService.addSong(songInfoPO);
            num ++;
        }
        System.out.println("爬取歌曲完成");
    }

    @Override
    public void asynReptile(int type, Object[] params) throws SerException {
        Thread thread = new Thread(new Reptilep(type, params));
        thread.start();
    }

    @Override
    public void reptileRecommendSongList(Integer offset, Integer limit) throws SerException {
        offset = offset == null ? 0 : offset;
        limit = limit == 0 ? 10 : limit;
        StringBuffer url = new StringBuffer();
        url.append(NetseaseUrl.API);
        url.append("/api/personalized/playlist");
        url.append("?offset=" + offset);
        url.append("&limit=" + limit);

        String result = HttpClientHelper.sendGet(url.toString(), null, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (!"200".equals(jsonObject.getString("code"))) {
            return;
        }
        JSONArray object = jsonObject.getJSONArray("result");
        int num = 1;
        for (Object song1 : object) {
            JSONObject song = (JSONObject) song1;
            String id = song.getString("id");
            String name = song.getString("name");
            String picUrl = song.getString("picUrl");
            Integer playCount = song.getInteger("playCount");
            SongListPO po = new SongListPO(id, name, picUrl, playCount, Origin.WANG_YI.name(), null);
            songService.addSongList(po);

            songService.addRecommendSongList(new RecommendSongListPO(id, num, String.valueOf(Calendar.getInstance().getTimeInMillis())));
            num ++;

            reptileSongListDetails(po.getSongListId());
        }
        System.out.println("爬取推荐歌单完成:" + Calendar.getInstance().getTime());
    }

    /**
     * 爬取歌单详情
     * @param songListId
     */
    private void reptileSongListDetails(String songListId) throws SerException{
        if (StringUtils.isBlank(songListId)) {
            return;
        }
        StringBuffer url = new StringBuffer();
        url.append(NetseaseUrl.API);
        url.append("/api/playlist/detail?id=" + songListId);

        String result = HttpClientHelper.sendGet(url.toString(), null, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (!"200".equals(jsonObject.getString("code"))) {
            return;
        }
        JSONObject object = (JSONObject) jsonObject.get("result");
        String trackUpdateTime = object.getString("trackUpdateTime");
        //更新歌单trackUpdateTime
        songService.updateSongList(songListId, trackUpdateTime);



        //保存歌曲
        JSONArray songs = object.getJSONArray("tracks");
        int num = 1;
        for (Object song1 : songs) {
            JSONObject song = (JSONObject) song1;
            //保存歌手
            JSONObject artist =  (JSONObject)song.getJSONArray("artists").get(0);  //歌手信息
            ArtistPO artistPO = new ArtistPO();
            String artistId = artist.getString("id");
            artistPO.setArtistId(artistId);
            artistPO.setName(artist.getString("name"));
            artistPO.setOrigin(Origin.WANG_YI.getName());
            artistService.addArtist(artistPO);


            String songId = song.getString("id");
            String name = song.getString("name");

            SongInfoPO songInfoPO = new SongInfoPO(songId, name, artistId, null, null, Origin.WANG_YI.name(), num);
            songService.addSong(songInfoPO);

            //保存歌单-歌曲关联
            songService.addSongListDetails(new SongListDetailsPO(songListId, songId, num));
            num ++;
        }

    }



    /**
     * 异步爬取
     *
     * @version v1
     */
    class Reptilep implements Runnable {

        private Integer type;

        private Object params[];

        public Reptilep(Integer type, Object[] params) {
            this.type = type;
            this.params = params;
        }

        public void run() {
            try {
                switch (type) {
                    case 1:  //歌手搜索
                        reptileSongs(String.valueOf(params[0]));
                        break;
                    case 2:  //歌手热门歌曲
                        reptileHotSongs((ArtistHotSongDTO)params[0]);
                        break;
                    default:
                        break;
                }
            } catch (SerException e) {
                e.printStackTrace();
            }

        }
    }

    void reptileMp3 () {

    }

    /**
     * 异步爬取mp3url
     *
     */
    class ReptilepMp3 implements Runnable {

        private String songId;

        public ReptilepMp3(String songId) {
            this.songId = songId;
        }

        public void run() {
            StringBuffer url = new StringBuffer();
            url.append(NetseaseUrl.API);
            url.append("/api/song/enhance/player/url/");
            url.append("?id=" + this.songId);
            url.append("&ids=[" + this.songId + "]");
            url.append("&br=3200000");

            String result = HttpClientHelper.sendGet(url.toString(), null, "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray songs = jsonObject.getJSONArray("data");
            JSONObject song = songs.getJSONObject(0);
            String map3Url = song.getString("url");
            String size = song.getString("size");
            String type = song.getString("type");

            System.out.println("爬取mp3url完成");
            try {
                //添加歌曲mp3url
                songService.addMp3Url(this.songId, map3Url);
            } catch (SerException e) {
                e.printStackTrace();
            }
        }
    }

}
