package dao.java.user;

import dao.java.impl.DaoImpl;
import model.po.user.UserPO;

import java.sql.SQLException;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-03 17:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class UserDaoImpl extends DaoImpl<UserPO, String> implements IUserDao{
    @Override
    public void addUser(UserPO po) throws SQLException {

    }

    @Override
    public UserPO getUserByName(String name) throws SQLException {
        return null;
    }

    @Override
    public UserPO getUserInfoByName(String name) throws SQLException {
        return null;
    }

    @Override
    public UserPO getUser(String id) {
        return super.get(id);
    }
}
