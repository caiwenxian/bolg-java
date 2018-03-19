package controller.music;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-19 10:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Controller
@RequestMapping("/music")
public class MusicController {

    @GetMapping
    @ResponseBody
    public ModelAndView musicPage() {
        return new ModelAndView("music/music");
    }
}
