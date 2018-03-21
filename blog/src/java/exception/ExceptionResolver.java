package exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-20 09:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class ExceptionResolver implements HandlerExceptionResolver {
    //该方法的的参数会自动获取 请求（request），响应（response），异常（exception），handler（异常参数的处理器 Controller）
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        ModelAndView mav = new ModelAndView();
        /*if (ex instanceof SerException) {

        }*/
        //系统异常
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mav.addObject("message", "系统出现异常，请稍后访问");
        mav.setViewName("common/500");
        return mav;
    }
}