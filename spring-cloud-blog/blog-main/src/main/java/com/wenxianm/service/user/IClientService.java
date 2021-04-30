package com.wenxianm.service.user;

import com.wenxianm.exception.SerException;
import com.wenxianm.model.bo.user.Client;

/**
 * 在线用户管理
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-08 15:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public interface IClientService {

    /**
     * 获取当前用户
     *
     * @param
     * @return class
     * @version v1
     */
    Client getCurrentUser() throws SerException;

    /**
     * 添加在线用户
     *
     * @param
     * @return class
     * @version v1
     */
    void addCurrentUser(Client client) throws SerException;
}
