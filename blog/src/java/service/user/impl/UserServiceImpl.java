package service.user.impl;

import dao.java.user.IUserDao;
import exception.SerException;
import model.po.user.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.user.IUserService;
import utils.MD5Utils;

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
    IUserDao userDao;

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
}
