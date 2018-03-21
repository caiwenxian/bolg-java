package controller.test;

import controller.security.token.UserToken;
import exception.ActException;
import exception.SerException;
import model.po.music.SongInfoPO;
import model.po.user.LoginPO;
import model.po.user.UserPO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.common.Result;
import service.common.impl.ActResult;
import service.music.ISongService;
import service.user.IUserService;
import service.user.ILoginService;
import utils.CacheManage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

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

    final Logger logger = Logger.getLogger(TestController.class.getName());

    @Autowired
    IUserService userService;
    @Autowired
    ISongService songService;

    @GetMapping("/v1/test")
    @ResponseBody
    public String test() {
        CacheManage cacheManage = new CacheManage();
        try {
            Collection collection = cacheManage.AllSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @GetMapping("v1/transaction")
    public String test1 () {
        try {
            songService.update(new SongInfoPO("28196001", null, null, null, "212", null, null));
            return "success";
        } catch (SerException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Autowired
    EhCacheManager cacheManager;
    @Autowired
    ILoginService loginService;

    @GetMapping("v1/login")
    @ResponseBody
    public String login (HttpServletRequest request, HttpServletResponse response) throws ActException {
        UserToken token = new UserToken("zhangsan", "000000", true, null, null);
        Subject subject = SecurityUtils.getSubject();

        try {
            loginService.login(new LoginPO("zhangsan", "000000"));

//            Object object = subject.getSession().getAttribute(subject.getSession().getId());
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

    @GetMapping("/v1/header")
    @ResponseBody
    public ModelAndView header () {
        return new ModelAndView("common/header");
    }


}
