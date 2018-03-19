package service.user.impl;

import exception.SerException;
import model.bo.user.Client;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import service.user.IClientService;

/**
 * 在线用户管理实现
 *
 * @Author: [caiwenxian]
 * @Date: [2018-03-08 15:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Service
public class ClientServiceImpl implements IClientService {
    @Override
    public Client getCurrentUser() throws SerException {
        try {
            Subject subject = SecurityUtils.getSubject();
            Client client = (Client) subject.getSession().getAttribute(subject.getSession().getId());
            return client;
        } catch (Exception e) {
            throw new SerException("获取当前用户失败");
        }
    }

    @Override
    public void addCurrentUser(Client client) throws SerException {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.getSession().setAttribute(subject.getSession().getId(), client);
        } catch (Exception e) {
            throw new SerException("添加在线用户失败");
        }
    }
}