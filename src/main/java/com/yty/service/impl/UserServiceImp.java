package com.yty.service.impl;

import com.yty.dao.UserMapper;
import com.yty.entity.User;
import com.yty.service.UserService;
import com.yty.utils.RedisUtil;
import com.yty.utils.SendEmailUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void print() {
//        userMapper.login();
        System.out.println(userMapper);
        System.out.println(redisUtil);
        redisUtil.set("123", "qqwqqwqq");
        System.out.println(redisUtil.get("123"));
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

    @SneakyThrows
    @Override
    public String sendEmail(String email) {
        if (redisUtil.hasKey(email + "emailCode") == true) {
            return ""+redisUtil.getExpire(email + "emailCode");
        }
        String code = makeCode();
        SendEmailUtil.sendMail(email, code);
        redisUtil.set(email + "emailCode", code, 58);
        return code;
    }

    @Override
    public String makeCode() {
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String code1 = String.valueOf(code);
        return code1;
    }

    @Override
    public Integer verifyRegisterInfo(String email, String tel, String password, String name, String code) {
        String emailTest = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String phoneTest = "^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$";
        if (name.equals("") || email.equals("") || password.equals("") || tel.equals("") || code.equals("")) {
            return 404;
        }
        if (email.matches(emailTest) == false || tel.matches(phoneTest) == false) {
            return 500;
        }
        if (!redisUtil.hasKey(email + "emailCode") || redisUtil.hasKey(email + "emailCode") && !code.equals(redisUtil.get(email + "emailCode"))) {
            return 101;
        }
        return 1010;
    }
}
