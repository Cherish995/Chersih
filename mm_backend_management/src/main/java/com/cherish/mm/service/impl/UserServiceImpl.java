package com.cherish.mm.service.impl;

import com.cherish.mm.dao.UserDao;
import com.cherish.mm.pojo.User;
import com.cherish.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/03
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User check(User user) {

        User userLogin = userDao.login(user.getUsername());
        if (userLogin != null) {
            // 用户名正确
            //校验密码是否也是正确的
            if (userLogin.getPassword().equals(user.getPassword())) {
                // 登录校验成功
                return userLogin;
            } else {
                // 无法完成登录
                throw new RuntimeException("密码错误！");
            }
        } else {
            // 用户名错误
            throw new RuntimeException("用户名错误！");
        }


    }
}
