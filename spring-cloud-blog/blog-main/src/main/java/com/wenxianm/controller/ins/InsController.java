package com.wenxianm.controller.ins;

import com.wenxianm.model.vo.ins.PictureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.wenxianm.service.common.impl.ActResult;
import com.wenxianm.service.ins.InsService;

import java.util.List;

/**
 * @description：ins业务
 * @author: caiwx
 * @createDate ： 2020年08月12日 15:46:00
 */

@Controller
@RequestMapping("/ins")
public class InsController {

    @Autowired
    private InsService insService;

    /**
     * 功能描述：根据连接获取图片url页面
     *
     * @author caiwx
     * @return ModelAndView
     * @date 2020/8/12
     */
    @GetMapping("/to/link/picture/url")
    public ModelAndView picByLink() {
        return new ModelAndView("ins/picByLink");
    }

    /**
     * 功能描述：根据连接获取图片url
     *
     * @author caiwx
     * @param link
     * @return ActResult
     * @date 2020/8/12
     */
    @GetMapping("/link/picture/url")
    @ResponseBody
    public ActResult getPicByLink(String link) {
        try {
            List<PictureVo> list = insService.listPictureUrlByLink(link);
            return ActResult.data(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActResult.error("error");
    }

    /**
     * 功能描述：根据用户获取图片url
     *
     * @author caiwx
     * @param
     * @return
     * @date 2020/8/20
     */
    @GetMapping("/user/picture/url")
    @ResponseBody
    public ActResult getPicByUser(String userName) {
        try {
            List<PictureVo> list = insService.listPictureUrlByUser(userName);
            return ActResult.data(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActResult.error("error");
    }

}
