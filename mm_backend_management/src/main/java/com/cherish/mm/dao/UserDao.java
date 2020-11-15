package com.cherish.mm.dao;

import com.cherish.mm.pojo.User;

/**
 * @author Cherish
 * @version 1.8.0_121
 * @date 2020/11/03
 */
public interface UserDao {
    User login(String username);
}
