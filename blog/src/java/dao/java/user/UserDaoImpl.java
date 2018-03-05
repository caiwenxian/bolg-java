package dao.java.user;

import dao.java.impl.DaoImpl;
import model.po.user.UserPO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-03 17:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
@Repository
public class UserDaoImpl extends DaoImpl<UserPO, String> implements IUserDao{
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplateASS;

    @Override
    public void addUser(UserPO po) throws SQLException {

    }

    @Override
    public UserPO getUserByName(String name) throws SQLException {
        return sqlSessionTemplateASS.selectOne("getUserByName", name);
    }

    @Override
    public UserPO getUserInfoByName(String name) throws SQLException {
        return sqlSessionTemplateASS.selectOne("getUserInfoByName", name);
    }

    /*@Override
    public UserPO getUser(String id) {
        return super.get(id);
    }*/
}
