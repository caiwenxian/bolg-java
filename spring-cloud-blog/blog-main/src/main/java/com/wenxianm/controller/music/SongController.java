package com.wenxianm.controller.music;

import com.wenxianm.controller.common.BaseController;
import com.wenxianm.exception.ErrorCode;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.dto.common.BaseDTO;
import com.wenxianm.model.po.common.PagePO;
import com.wenxianm.model.po.music.SongInfoPO;
import com.wenxianm.model.vo.music.TopListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wenxianm.service.common.Result;
import com.wenxianm.service.common.impl.ActResult;
import com.wenxianm.service.music.ISongService;

import static com.wenxianm.model.constant.Common.PAGE_LIMIT;

/**
 * 歌曲业务
 *
 * @Author: [caiwenxian]
 * @Date: [2018-02-05 17:52]
 * @Description: [ 歌曲业务 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Controller
@RequestMapping("/song")
public class SongController extends BaseController {

    @Autowired
    ISongService songService;

    /**
     * 歌曲模糊搜索
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/search/{name}/{page}")
    @ResponseBody
    public Result search(@PathVariable String name, @PathVariable Integer page) {
        try {
//            List<SongInfoPO> list = songService.listSongByName(name);
            page = page == null ? 1 : page;
            PagePO<SongInfoPO> pagePO = songService.listSongByNameByPage(name, page, PAGE_LIMIT);
            return ActResult.data(pagePO);
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }
    }

    /**
     * 根据排行榜id获取排行榜歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/toplist/{id}")
    @ResponseBody
    public Result getTopList(@PathVariable String id) {
        try {
            TopListVO vo = songService.listTopListByTopListId(id);
            return ActResult.data(vo);
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }
    }

    /**
     * 获取热门歌曲
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/hotsong/{page}")
    @ResponseBody
    public Result listHotSong(@PathVariable Integer page) {
        try {
            BaseDTO dto = new BaseDTO();
            dto.setPage(page);
            dto.setLimit(20);
            PagePO vo = songService.listHotSong(dto);
            return ActResult.data(vo);
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }
    }

}
