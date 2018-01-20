package controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/v1/test")
    public String test() {
        return "hello";
    }

}
