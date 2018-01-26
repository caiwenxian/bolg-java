package controller.test;

import exception.SerException;
import model.po.user.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.common.Result;
import service.common.impl.ActResult;
import service.user.IUserService;

import javax.annotation.Resource;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-16 11:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    IUserService userService;

    @GetMapping("/v1/test")
    public String test() {
        return "hello";
    }


    @GetMapping("/v1/user")
    @ResponseBody
    public Result user() {
        try {
            userService.addUser(new UserPO("zhangsan", "000000", 1));
            return ActResult.success("success");
        } catch (SerException e) {
            return ActResult.error(303, e.getMessage());
        }

    }

    @GetMapping("/v1/home")
    public ModelAndView home () {
        return new ModelAndView("home/home");
    }
}
