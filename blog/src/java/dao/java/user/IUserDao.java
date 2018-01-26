package dao.java.user;

import model.po.user.UserPO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-16 17:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Repository
public interface IUserDao {

    /**
     * 添加用户
     *
     * @param po
     * @return class
     * @version v1
     */
    void addUser(UserPO po) throws SQLException;
}
