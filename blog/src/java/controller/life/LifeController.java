package controller.life;

import controller.common.BaseController;
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
@RequestMapping("/life")
public class LifeController extends BaseController {

    @GetMapping
    @ResponseBody
    public ModelAndView lifePage() {
        return new ModelAndView("life/life");
    }
}
