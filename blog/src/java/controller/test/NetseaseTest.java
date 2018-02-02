package controller.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.po.music.SongInfoPO;
import utils.HttpClientHelper;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-17 15:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NetseaseTest {


    public static void main(String[] args) throws Exception {
//        search();
//        artist();
        hotArtists();
    }


//    @Test
    static void search() {
        String url = "http://music.163.com/api/search/pc/?s=海阔天空&limit=10&type=1&offset=0";
        String result = HttpClientHelper.sendPost(url, null, "UTF-8");
        System.out.println("result:" + result);

        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject object = (JSONObject) jsonObject.get("result");
//        JSONArray object = jsonObject.getJSONArray("result");
        JSONArray songs = object.getJSONArray("songs");
        JSONObject song = (JSONObject)songs.get(1);
        JSONArray artists = song.getJSONArray("artists");

        String artistId = String.valueOf(((JSONObject)artists.get(0)).get("id"));
        String id = String.valueOf(song.get("id"));
        String name = String.valueOf(song.get("name"));
        SongInfoPO songInfoPO = new SongInfoPO(id, name, artistId, null);
        System.out.println(songInfoPO.toString());

    }

    static void artist() {
        String url = "http://music.163.com/api/artist/6452?offset=0&limit=10";
        String result = HttpClientHelper.sendGet(url, null, "UTF8");
        System.out.println("result:" + result);

        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject object = (JSONObject) jsonObject.get("result");
//        JSONArray object = jsonObject.getJSONArray("result");
        JSONArray songs = object.getJSONArray("songs");
        JSONObject song = (JSONObject)songs.get(1);
        JSONArray artists = song.getJSONArray("artists");

        String artistId = String.valueOf(((JSONObject)artists.get(0)).get("id"));
        String id = String.valueOf(song.get("id"));
        String name = String.valueOf(song.get("name"));
        SongInfoPO songInfoPO = new SongInfoPO(id, name, artistId, null);
        System.out.println(songInfoPO.toString());

    }

    static void hotArtists() {
        String url = "http://music.163.com/api/artist/top?offset=0&total=true&limit=10";
        String result = HttpClientHelper.sendGet(url, null, "UTF8");
        System.out.println("result:" + result);

        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject object = (JSONObject) jsonObject.get("result");
//        JSONArray object = jsonObject.getJSONArray("result");
        JSONArray songs = object.getJSONArray("songs");
        JSONObject song = (JSONObject)songs.get(1);
        JSONArray artists = song.getJSONArray("artists");

        String artistId = String.valueOf(((JSONObject)artists.get(0)).get("id"));
        String id = String.valueOf(song.get("id"));
        String name = String.valueOf(song.get("name"));
        SongInfoPO songInfoPO = new SongInfoPO(id, name, artistId, null);
        System.out.println(songInfoPO.toString());

    }


}
