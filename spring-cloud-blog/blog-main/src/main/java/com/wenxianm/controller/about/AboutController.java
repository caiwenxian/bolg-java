package com.wenxianm.controller.about;

import com.wenxianm.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-19 10:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Controller
@RequestMapping("/about")
public class AboutController extends BaseController {

    @GetMapping("/about-author")
    @ResponseBody
    public ModelAndView aboutAuthorPage() {
        return new ModelAndView("about/about-author");
    }

    @GetMapping("/about-blog")
    @ResponseBody
    public ModelAndView aboutBlogPage() {
        return new ModelAndView("about/about-blog");
    }

    @GetMapping("/guest-message")
    @ResponseBody
    public ModelAndView guestMessagePage() {
        return new ModelAndView("about/guest-message");
    }
}
