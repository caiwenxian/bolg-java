package controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-25 17:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    final Logger logger = Logger.getLogger(LoginController.class.getName());

    @GetMapping()
    @ResponseBody
    public ModelAndView loginPage(HttpServletRequest request) {
        String refUrl = request.getParameter("refUrl");
        ModelAndView modelAndView = new ModelAndView("account/login");
        modelAndView.addObject("refUrl", refUrl);
        return modelAndView;
    }

}
