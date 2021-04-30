package com.wenxianm.controller.music;

import com.wenxianm.controller.common.BaseController;
import com.wenxianm.exception.ErrorCode;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.po.music.ArtistPO;
import com.wenxianm.model.po.music.SongInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wenxianm.service.common.Result;
import com.wenxianm.service.common.impl.ActResult;
import com.wenxianm.service.music.IArtistHotSongService;
import com.wenxianm.service.music.IArtistService;

import java.util.List;

/**
 * 歌手业务
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-29 17:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("/artist")
public class ArtistController extends BaseController {

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
