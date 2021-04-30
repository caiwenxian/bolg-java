package com.wenxianm.controller.account;

import com.wenxianm.controller.common.BaseController;
import com.wenxianm.exception.ActException;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.po.user.LoginPO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.wenxianm.service.common.Result;
import com.wenxianm.service.common.impl.ActResult;
import com.wenxianm.service.user.IClientService;
import com.wenxianm.service.user.ILoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class LoginController extends BaseController {

    final Logger logger = Logger.getLogger(LoginController.class.getName());
    @Autowired
    ILoginService loginService;
    @Autowired
    IClientService clientService;

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
        if (subject.isAuthenticated() && StringUtils.isNotBlank(refUrl)) {
            response.sendRedirect(refUrl);
        }
        if (StringUtils.isBlank(refUrl)) {
            refUrl = request.getHeader("referer");
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
        if (StringUtils.isBlank(loginPO.getName()) || StringUtils.isBlank(loginPO.getPassword())) {
            return ActResult.error(Result.MSG_PARAMS_MISS);
        }
        try {
            loginService.login(loginPO);

            /*String refUrl = request.getParameter("refUrl");
            if (StringUtils.isNotBlank(refUrl)) {
                response.sendRedirect(refUrl);
            }*/
            return ActResult.data(clientService.getCurrentUser().getToken());
        } catch (SerException e) {
            e.printStackTrace();
            return ActResult.error(e.getMessage());
        }
    }

    /**
     * 退出登录
     *
     * @param
     * @return class
     * @version v1
     */
    @PostMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request, HttpServletResponse response) throws ActException {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ActResult.success("success");
    }

}
