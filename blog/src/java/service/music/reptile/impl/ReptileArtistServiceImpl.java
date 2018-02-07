package service.music.reptile.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import exception.SerException;
import model.constant.NetseaseUrl;
import model.enums.music.Origin;
import model.po.music.ArtistPO;
import model.po.music.HotArtistPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.music.IArtistService;
import service.music.reptile.IReptileArtistService;
import utils.HttpClientHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-30 09:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class ReptileArtistServiceImpl implements IReptileArtistService {

    @Autowired
    IArtistService artistService;

    @Override
    public void reptileArtist(String name) throws SerException {
        StringBuffer url = new StringBuffer();
        url.append(NetseaseUrl.API);
        url.append("/api/search/pc/");
        url.append("?s=" + name);
        url.append("&limit=10");
        url.append("&type=100");
        url.append("&offset=0");

        String result = HttpClientHelper.sendPost(url.toString(), null, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject object = (JSONObject) jsonObject.get("result");
        JSONArray artists = object.getJSONArray("artists");
        for (Object artist1 : artists) {
            JSONObject artist = (JSONObject) artist1;
            ArtistPO artistPO = new ArtistPO();
            artistPO.setArtistId(artist.getString("id"));
            artistPO.setName(artist.getString("name"));
            artistPO.setOrigin(Origin.WANG_YI.getName());
            artistService.addArtist(artistPO);
        }
        System.out.println("爬取歌手完成");
    }

    @Override
    public void reptileHotArtist(int limit) throws SerException {
//        http://music.163.com/api/artist/top?offset=0&total=true&limit=10
        limit = limit == 0 ? 100 : limit;
        StringBuffer url = new StringBuffer();
        url.append(NetseaseUrl.API);
        url.append("/api/artist/top");
        url.append("?limit=" + limit);
        url.append("&total=true");
        url.append("&offset=0");

        String result = HttpClientHelper.sendGet(url.toString(), null, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (!"200".equals(jsonObject.getString("code"))) {
            return;
        }

        JSONArray artists = jsonObject.getJSONArray("artists");
        int num = 1;
        List<HotArtistPO> pos = new ArrayList<HotArtistPO>();
        for (Object artist1 : artists) {
            JSONObject artist = (JSONObject) artist1;
            ArtistPO artistPO = new ArtistPO();
            artistPO.setArtistId(artist.getString("id"));
            artistPO.setName(artist.getString("name"));
            artistPO.setOrigin(Origin.WANG_YI.getName());
            artistService.addArtist(artistPO);
            HotArtistPO po = new HotArtistPO(artistPO.getArtistId(), num);
            pos.add(po);
            num++;
        }
        artistService.addHotArtist(pos);
        System.out.println("爬取热门歌手列表完成");
    }

    @Override
    public void asynReptile(int type, String[] params) throws SerException {
        Thread thread = new Thread(new Reptilep(type, params));
        thread.start();
    }

    /**
     * 异步爬取
     *
     * @version v1
     */
    class Reptilep implements Runnable {

        private Integer type;

        private String params[];

        public Reptilep(Integer type, String[] params) {
            this.type = type;
            this.params = params;
        }

        public void run() {
            try {
                switch (type) {
                    case 1:  //歌手搜索
                        reptileArtist(params[0]);
                        break;
                    default:
                        break;
                }
            } catch (SerException e) {
                e.printStackTrace();
            }

        }
    }
}
