package com.wenxianm.dao.java.user;

import com.wenxianm.dao.java.impl.DaoImpl;
import com.wenxianm.model.po.user.UserPO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-16 17:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */


public interface IUserDao {

    /**
     * 添加用户
     *
     * @param po
     * @return class
     * @version v1
     */
    void addUser(UserPO po) throws SQLException;

    /**
     * 根据姓名获取用户对象
     *
     * @param
     * @return class
     * @version v1
     */
    UserPO getUserByName(String name) throws SQLException;

    /**
     * 根据姓名获取基本信息
     *
     * @param
     * @return class
     * @version v1
     */
    UserPO getUserInfoByName(String name) throws SQLException;

//    UserPO getUser(String id);
}
