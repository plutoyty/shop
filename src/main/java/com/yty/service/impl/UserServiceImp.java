package com.yty.service.impl;

import com.yty.dao.UserMapper;
import com.yty.entity.User;
import com.yty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void print() {
//        userMapper.login();
        System.out.println(userMapper);
        System.out.println("running------ ");
    }

    @Override
    public User login(String account, String password) {
        User user = new User();
        return null;
    }

    @Override
    public boolean register(User user) {
        return userMapper.register(user);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUser(name);
    }
}
