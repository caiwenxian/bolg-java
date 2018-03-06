package controller.home;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-16 17:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("/index")
public class indexController {

    @GetMapping()
    public ModelAndView index() {
        Subject subject = SecurityUtils.getSubject();
        Object object = subject.getSession().getAttribute(subject.getSession().getId());
        System.out.println(object.toString());
        return new ModelAndView("home/home");
    }
}
