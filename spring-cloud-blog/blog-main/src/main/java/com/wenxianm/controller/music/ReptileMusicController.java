package com.wenxianm.controller.music;

import com.wenxianm.controller.common.BaseController;
import com.wenxianm.exception.ErrorCode;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.dto.music.ArtistHotSongDTO;
import com.wenxianm.model.dto.music.MusicDTO;
import com.wenxianm.model.dto.music.SongListDTO;
import com.wenxianm.model.dto.music.TopListDTO;
import com.wenxianm.model.enums.music.SongListType;
import com.wenxianm.model.enums.music.TopListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.wenxianm.service.common.Result;
import com.wenxianm.service.common.impl.ActResult;
import com.wenxianm.service.music.reptile.IReptileArtistService;
import com.wenxianm.service.music.reptile.IReptileSongService;

/**
 * 爬取业务
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-31 17:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("/reptilemusic")
public class ReptileMusicController extends BaseController {

    @Autowired
    IReptileSongService reptileSongService;
    @Autowired
    IReptileArtistService reptileArtistService;

    /**
     * 根据歌手姓名获取热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    @PostMapping("/hotsong")
    @ResponseBody
    public Result reptileArtistHotSong(ArtistHotSongDTO dto) {
        try {
            dto.setLimit(50);
            reptileSongService.reptileHotSongs(dto);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(303, e.getMessage());
        }
    }

    /**
     * 歌手信息
     *
     * @param
     * @return class
     * @version v1
     */
    @PostMapping("/artist")
    @ResponseBody
    public Result reptileArtist(String name) {

        try {
            reptileArtistService.reptileArtist(name);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }

    /**
     * 获取排行榜
     *
     * @param name 排行榜名称
     * @return class
     * @version v1
     */
    @GetMapping("/top-song")
    @ResponseBody
    public Result reptileTopByTopType(String name) {

        TopListDTO topListDTO = new TopListDTO();
        TopListType topListType = TopListType.topListType(name);
        if (null == topListType) {
            return ActResult.error(ErrorCode.GENERAL, "榜单不存在");
        }
        topListDTO.setTopListType(topListType);
        try {
            reptileSongService.reptileTopSongs(topListDTO);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }

    /**
     * 获取所有排行榜
     * @author caiwx
     * @date 2019/12/20 15:41
     * @return
     */
    @GetMapping("/top-song-all")
    @ResponseBody
    public Result reptileAllTop() {
        try {
            reptileSongService.reptileAllTopSongs();
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
    public Result reptileTopByTopType(String[] names) {

        try {
            for (String name : names) {
                TopListDTO topListDTO = new TopListDTO();
                TopListType topListType = TopListType.topListType(name);
                topListDTO.setTopListType(topListType);
                reptileSongService.reptileTopSongs(topListDTO);
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
    public Result reptileTopByTopType() {

        try {
            reptileArtistService.reptileHotArtist(100);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }

    /**
     * 推荐歌单
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/songlist-recommend/{offset}/{limit}")
    @ResponseBody
    public Result reptileRecommendSongList(@PathVariable Integer offset, @PathVariable Integer limit) {

        try {
            reptileSongService.reptileRecommendSongList(offset, limit);
            return ActResult.success("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }

    /**
     * 搜索歌单
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/search/songlist/{keyword}")
    @ResponseBody
    public Result reptileSongList(@PathVariable String keyword) {

        try {
            MusicDTO musicDTO = new MusicDTO(keyword);
            reptileSongService.reptileSongList(musicDTO);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }

    /**
     * 按列表获取歌单
     *
     * @param order "hot"/"new"
     * @param type  类型 例: "华语"、"韩语"
     * @return class
     * @version v1
     */
    @GetMapping("/songlist")
    @ResponseBody
    public Result reptileSongListByType(String order, String type) {
        try {
            SongListDTO dto = new SongListDTO();
            dto.setOrder(order);
            dto.setSongListType(SongListType.getSongListType(type));
            dto.setLimit(10);
            dto.setPage(1);
            reptileSongService.reptileSongListByType(dto);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }
}
