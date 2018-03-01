package service.user;

import exception.SerException;
import model.po.user.UserPO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-25 16:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IUserService {

    /**
     * 添加用户
     *
     * @param po
     * @return class
     * @version v1
     */
    void addUser(UserPO po) throws SerException;

    /**
     * 根据姓名获取用户
     *
     * @param
     * @return class
     * @version v1
     */
    UserPO getUserByName(String name) throws SerException;

    /**
     * 根据姓名获取基本信息
     *
     * @param
     * @return class
     * @version v1
     */
    UserPO getUserInfoByName(String name) throws SerException;
}
