package controller.account;

import controller.security.token.UserToken;
import exception.ActException;
import exception.SerException;
import model.po.base.validate.ADD;
import model.po.user.LoginPO;
import model.vo.user.TokenVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.common.Result;
import service.common.impl.ActResult;
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
    public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refUrl = request.getParameter("refUrl");
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            response.sendRedirect(refUrl);
        }
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
    public Result login(LoginPO loginPO, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws ActException {
        UserToken token = new UserToken("zhangsan", "000000", true, null, null);
        Subject subject = SecurityUtils.getSubject();

        try {
            loginService.login(new LoginPO("zhangsan", "000000"));

            String refUrl = request.getParameter("refUrl");
            if (StringUtils.isNotBlank(refUrl)) {
                response.sendRedirect(refUrl);
            }
            TokenVO tokenVO = new TokenVO(subject.getSession().getId().toString());
            return ActResult.data(tokenVO);
        } catch (SerException e) {
            e.printStackTrace();
            return ActResult.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ActResult.error(e.getMessage());
        }
    }

}