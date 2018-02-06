package controller.music;

import exception.ErrorCode;
import exception.SerException;
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
import service.music.ISongService;

import java.util.List;

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
public class SongController {

    @Autowired
    ISongService songService;

    /**
     * 歌曲模糊搜索
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/search/{name}")
    @ResponseBody
    public Result search(@PathVariable String name) {
        try {
            List<SongInfoPO> list = songService.listSongByName(name);
            return ActResult.data(list);
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }
    }
}
