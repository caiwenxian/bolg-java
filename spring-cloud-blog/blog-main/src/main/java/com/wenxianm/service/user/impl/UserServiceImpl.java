package com.wenxianm.service.user.impl;

import com.wenxianm.dao.java.user.IUserDao;
import com.wenxianm.dao.java.user.UserDaoImpl;
import com.wenxianm.exception.SerException;
import com.wenxianm.model.po.user.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenxianm.service.user.IUserService;
import com.wenxianm.utils.MD5Utils;

import java.sql.SQLException;
import java.util.UUID;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-25 16:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDaoImpl userDao;

    public void addUser(UserPO po) throws SerException {
        try {
            //todo 查询用户是否已存在
            po.setId(UUID.randomUUID().toString());
            po.setPassword(MD5Utils.encryptPassword(po.getPassword()));
            userDao.addUser(po);
        } catch (SQLException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public UserPO getUserByName(String name) throws SerException {
        try {
            return userDao.getUserByName(name);
        } catch (SQLException e) {
            throw new SerException("用户不存在");
        }
    }

    @Override
    public UserPO getUserInfoByName(String name) throws SerException {
        try {
            return userDao.getUserInfoByName(name);
        } catch (SQLException e) {
            throw new SerException("用户不存在");
        }
    }
}
