package controller.home;

import exception.SerException;
import model.bo.user.Client;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.user.IClientService;

import java.util.logging.Logger;


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

    final static Logger logger = Logger.getLogger(indexController.class.getName());

    @Autowired
    IClientService clientService;

    @GetMapping()
    public ModelAndView index() throws SerException {
//        Subject subject = SecurityUtils.getSubject();
//        Client client = clientService.getCurrentUser();
//        logger.info(client.toString());
        return new ModelAndView("home/home");
//        return new ModelAndView("knowledge/knowledge");
    }
}
