package service.user.impl;

import controller.security.token.UserToken;
import dao.java.Dao;
import dao.java.user.IUserDao;
import dao.java.user.UserDaoImpl;
import exception.SerException;
import model.bo.user.Client;
import model.enums.user.CacheType;
import model.po.user.LoginPO;
import model.po.user.UserPO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.user.IClientService;
import service.user.IUserService;
import service.user.ILoginService;
import utils.CacheUtil;

import java.util.logging.Logger;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-26 17:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Service
public class LoginServiceImpl implements ILoginService {

    final Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());

    @Autowired
    IUserService userService;
    @Autowired
    CacheUtil cacheUtil;
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private IClientService clientService;

    @Override
    public void login(LoginPO po) throws SerException {
        po.setName("zhangsan");
        UserToken token = new UserToken("zhangsan", "000000", false, null, null);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
//            cacheUtil.put(CacheType.USER_INFO, "u_" + user.getId(), user);
//            UserPO po1 =  cacheUtil.get(CacheType.USER_INFO, "u_" + user.getId(), UserPO.class);

//            UserPO po2 = userDao.get(user.getId());
//            logger.info(userDao.get(user.getId()).getCreateTime().toString());
//            subject.getSession().setAttribute(subject.getSession().getId(), user);

            //存储到shiro管理的缓存
            UserPO user = userService.getUserInfoByName(po.getName());
            Client client = new Client(user.getId(), user.getName(), user.getType());
            clientService.addCurrentUser(client);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            throw new SerException("password error!");
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            throw new SerException("username error!");
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            throw new SerException("times error");
        }
    }


}
