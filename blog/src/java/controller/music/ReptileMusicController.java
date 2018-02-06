package controller.music;

import exception.ErrorCode;
import exception.SerException;
import model.dto.music.ArtistHotSongDTO;
import model.dto.music.TopListDTO;
import model.enums.music.TopListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.common.Result;
import service.common.impl.ActResult;
import service.music.IArtistHotSongService;
import service.music.ITopListService;
import service.music.reptile.IReptileArtistService;
import service.music.reptile.IReptileSongService;

/**
 * 爬取业务
 * @Author: [caiwenxian]
 * @Date: [2018-01-31 17:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("/reptilemusic")
public class ReptileMusicController {

    @Autowired
    IReptileSongService reptileSongService;
    @Autowired
    IReptileArtistService reptileArtistService;

    /**
     * 根据歌手id获取热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/hotsong/{id}")
    @ResponseBody
    public Result reptileArtistHotSong(@PathVariable String id) {
        ArtistHotSongDTO dto = new ArtistHotSongDTO(new String[]{id});
        dto.setLimit(50);
        try {
            reptileSongService.reptileHotSongs(dto);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(303, e.getMessage());
        }
    }

    /**
     * 获取排行榜
     *
     * @param name 排行榜名称
     * @return class
     * @version v1
     */
    @GetMapping("/top-song/{name}")
    @ResponseBody
    public Result reptileTopByTopType (@PathVariable String name) {

        TopListDTO topListDTO = new TopListDTO();
        TopListType topListType = TopListType.topListType(name);
        if (null == topListType) {
            return ActResult.error(ErrorCode.GENERAL, "榜单不存在");
        }
        topListDTO.setTopListType(topListType);
        try {
            reptileSongService.reptileSongs(topListDTO);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }

    /**
     * 批量获取排行榜
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/tops-song")
    @ResponseBody
    public Result reptileTopByTopType (String[] names) {

        try {
            for (String name : names) {
                TopListDTO topListDTO = new TopListDTO();
                TopListType topListType = TopListType.topListType(name);
                topListDTO.setTopListType(topListType);
                reptileSongService.reptileSongs(topListDTO);
            }
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }

    /**
     * 热门歌手
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/hot-artist")
    @ResponseBody
    public Result reptileTopByTopType () {

        try {
            reptileArtistService.reptileHotArtist(100);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }

    /**
     * 歌手信息
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/artist/{name}")
    @ResponseBody
    public Result reptileArtist (@PathVariable String name) {

        try {
            reptileArtistService.reptileArtist(name);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }
}