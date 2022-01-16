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

    /**
     * 发送验证码
     * @param email
     * @return
     */
    String sendEmail(String email);

    /**
     * 随机生成验证码
     * @return
     */
    String makeCode();


    /**
     * 校验注册信息
     * @param email
     * @param tel
     * @param password
     * @param name
     * @return
     */
    Integer verifyRegisterInfo(String email, String tel, String password, String name,String code);

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    boolean changeUserInfo(User user);

    /**
     * 是否是管理员
     * @param id
     * @return
     */
    boolean adminBool(String id);
}
