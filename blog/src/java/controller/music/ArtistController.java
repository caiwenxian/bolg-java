package controller.music;

import dao.java.music.IArtistHotSongDao;
import exception.ErrorCode;
import exception.SerException;
import model.dto.music.ArtistHotSongDTO;
import model.po.music.ArtistPO;
import model.po.music.SongInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.common.Result;
import service.common.impl.ActResult;
import service.music.IArtistHotSongService;
import service.music.IArtistService;
import service.music.reptile.IReptileSongService;

import java.util.List;

/**
 * 歌手业务
 * @Author: [caiwenxian]
 * @Date: [2018-01-29 17:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    IArtistService artistService;
    @Autowired
    IArtistHotSongService artistHotSongService;

    /**
     * 歌手模糊搜索
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/search/{name}")
    @ResponseBody
    public Result search(@PathVariable String name) {
        try {
            List<ArtistPO> list = artistService.listArtistByName(name);
            return ActResult.data(list);
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }
    }

    /**
     * 歌手热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/hot-song/{artistId}")
    @ResponseBody
    public Result listHotSong(@PathVariable String artistId) {
        try {
            List<SongInfoPO> list = artistHotSongService.listHotSongByArtistId(artistId);
            return ActResult.data(list);
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }
    }





}
