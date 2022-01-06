package com.yty.service;

import com.yty.entity.User;

public interface UserService {
    void print();

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    User login(String account, String password);

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 用过邮箱或手机号获取user信息
     * @param name
     * @return
     */
    User getUserByName(String name);
}
