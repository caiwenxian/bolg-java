package controller.account;

import exception.SerException;
import model.bo.user.Client;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.common.Result;
import service.common.impl.ActResult;
import service.user.IClientService;

/**
 * 账户基本信息
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-21 11:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    IClientService clientService;

    /**
     * 检测用户状态
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/checkStatus")
    @ResponseBody
    public Result checkStatus() {
        try {
            Client client = clientService.getCurrentUser();
            if (null == client) {
                return ActResult.error(Result.CODE_NOT_LOGIN, Result.MSG_NOT_LOGIN);
            }
            return ActResult.success("success");
        } catch (SerException e) {
            e.printStackTrace();
            return ActResult.error(Result.CODE_NOT_LOGIN, Result.MSG_NOT_LOGIN);
        }
    }

    /**
     * 获取在线用户数据
     *
     * @param
     * @return class
     * @version v1
     */
    @GetMapping("/userinfo")
    @ResponseBody
    public Result getUserInfo() {
        try {
            Client client = clientService.getCurrentUser();
            if (null == client) {
                return ActResult.error(Result.CODE_NOT_LOGIN, Result.MSG_NOT_LOGIN);
            }
            return ActResult.data(client);
        } catch (SerException e) {
            e.printStackTrace();
            return ActResult.error(Result.CODE_NOT_LOGIN, Result.MSG_NOT_LOGIN);
        }
    }
}
