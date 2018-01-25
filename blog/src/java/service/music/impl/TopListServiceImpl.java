package service.music.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import exception.SerException;
import model.constant.NetseaseUrl;
import model.dto.music.TopListDTO;
import model.enums.music.TopListType;
import model.po.music.ArtistPO;
import model.po.music.SongInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.music.IArtistService;
import service.music.ISongService;
import service.music.ITopListService;
import utils.HttpClientHelper;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 17:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service("topListService")
public class TopListServiceImpl implements ITopListService{

    @Autowired
    IArtistService artistService;
    @Autowired
    ISongService songService;

    public void addSong(SongInfoPO po) {

    }

    public void reptileSongs(TopListDTO dto) throws SerException{

        StringBuffer url = new StringBuffer();
        url.append(NetseaseUrl.API);
        String url2 = TopListType.getUrl(dto.getTopListType().getCode());
        url.append(dto);
        url.append(url2);
        url.append("&limit=" + dto.getLimit());
        url.append("&order=new");

        String result = HttpClientHelper.sendPost(url.toString(), null, "UTF-8");

        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject object = (JSONObject) jsonObject.get("result");
        JSONArray songs = object.getJSONArray("tracks");
        Iterator<Object> it = songs.iterator();
        while (it.hasNext()) {
            JSONObject song = (JSONObject) it.next();
            JSONArray artist = song.getJSONArray("artists");
            //保存歌手
            ArtistPO artistPO = new ArtistPO();
            artistPO.setArtistId(artist.getJSONObject(0).getString("id"));
            artistPO.setName(artist.getJSONObject(0).getString("name"));
            //todo 保存操作
            artistService.addArtist(artistPO);


            //保存歌曲
            SongInfoPO po = new SongInfoPO();
            po.setSongId(song.getString("id"));
            po.setName(song.getString("name"));
            po.setArtistId(artistPO.getArtistId());
            //todo 保存操作
            songService.addSong(po);
            //爬取歌曲url
            songService.reptileMp3Url(po.getSongId());

        }



    }
}
