package controller.music;

import exception.ErrorCode;
import exception.SerException;
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
import service.music.ITopListService;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-26 14:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("/toplist")
public class TopListController {

    @Autowired
    ITopListService topListService;

    @GetMapping("/top/{name}")
    @ResponseBody
    public Result listTopByTopType (@PathVariable String name) {

        TopListDTO topListDTO = new TopListDTO();
        TopListType topListType = TopListType.topListType(name);
        if (null == topListType) {
            return ActResult.error(ErrorCode.GENERAL, "榜单不存在");
        }
        topListDTO.setTopListType(topListType);
        try {
            topListService.reptileSongs(topListDTO);
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }

    @GetMapping("/tops")
    @ResponseBody
    public Result listTopByTopType (String[] names) {


//        TopListDTO topListDTO = new TopListDTO();
//        TopListType topListType = TopListType.topListType(name);
//        if (null == topListType) {
//            return ActResult.error(ErrorCode.GENERAL, "榜单不存在");
//        }


        try {
            for (String name : names) {
                TopListDTO topListDTO = new TopListDTO();
                TopListType topListType = TopListType.topListType(name);
                topListDTO.setTopListType(topListType);
                topListService.reptileSongs(topListDTO);
            }

            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(ErrorCode.GENERAL, e.getMessage());
        }

    }
}
