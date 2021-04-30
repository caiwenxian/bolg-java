package com.wenxianm.service.user.impl;

import com.wenxianm.controller.security.token.UserToken;
import com.wenxianm.dao.java.Dao;
import com.wenxianm.dao.java.user.IUserDao;
import com.wenxianm.dao.java.user.UserDaoImpl;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.bo.user.Client;
import com.wenxianm.model.enums.user.CacheType;
import com.wenxianm.model.po.user.LoginPO;
import com.wenxianm.model.po.user.UserPO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenxianm.service.user.IClientService;
import com.wenxianm.service.user.IUserService;
import com.wenxianm.service.user.ILoginService;
import com.wenxianm.utils.CacheUtil;

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
        UserToken token = new UserToken(po.getName(), po.getPassword(), false, null, null);
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
            throw new SerException("密码错误!");
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            throw new SerException("用户名不存在!");
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            throw new SerException("登录次数过多!");
        }
    }


}
