package controller.account;

import controller.security.token.UserToken;
import exception.ActException;
import exception.SerException;
import model.po.base.validate.ADD;
import model.po.user.LoginPO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.user.ILoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * 登录控制器
 *
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
    @Autowired
    ILoginService loginService;

    /**
     * 登录页面
     *
     * @param request
     * @return class ModelAndView
     * @version v1
     */
    @GetMapping()
    @ResponseBody
    public ModelAndView loginPage(HttpServletRequest request) {
        String refUrl = request.getParameter("refUrl");
        ModelAndView modelAndView = new ModelAndView("account/login");
        modelAndView.addObject("refUrl", refUrl);
        return modelAndView;
    }

    /**
     * 登录校验
     *
     * @param
     * @return class
     * @version v1
     */
    @PostMapping()
    @ResponseBody
    public String login(LoginPO loginPO, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws ActException {
        UserToken token = new UserToken("zhangsan", "000000", true, null, null);
        Subject subject = SecurityUtils.getSubject();

        try {
            loginService.login(new LoginPO("zhangsan", "000000"));

            Object object = subject.getSession().getAttribute(subject.getSession().getId());

            String refUrl = request.getParameter("refUrl");
            if (StringUtils.isNotBlank(refUrl)) {
                response.sendRedirect(refUrl);
            }
            return "success";
        } catch (SerException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            return "success";
        }
    }

}
