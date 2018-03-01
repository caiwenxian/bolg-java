package service.user;

import exception.SerException;
import model.po.user.LoginPO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-26 17:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public interface ILoginService {

    void login(LoginPO po) throws SerException;
}
