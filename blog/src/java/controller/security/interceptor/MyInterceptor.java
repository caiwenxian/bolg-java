package controller.security.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-22 10:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class MyInterceptor implements HandlerInterceptor {

    static final Logger logger = Logger.getLogger(MyInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Accept");
//        response.setHeader("Access-Control-Allow-Methods", "Get,POST,Put,OPTIONS");
//        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setContentType("textml;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed","1");
//        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (request.getServletPath() != null && request.getServletPath().indexOf("static") < 1) {
            logger.info("请求：" + request.getServletPath());
            logger.info("sessionId：" + request.getSession().getId());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        response.setHeader("Access-Control-Allow-Origin", "*");
    }
}
