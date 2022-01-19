package com.yty.service;

import com.yty.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @param msg
     * @return
     */
    String sendEmail(String email,String msg);

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

    /**
     * 检查验证码
     * @param code
     * @return
     */
    boolean CheckCode(String email,String code,String type);

    /**
     * 修改密码
     * @param email
     * @param newPassword
     */
    void updatePwd(String email, String newPassword);

    /**
     * 获取用户
     *          ***分页为加
     * @return
     */
    List<User> getUsers();

    /**
     * 获取用户数量
     * @return
     */
    Integer getUsersCount();

    /**
     * 删除用户
     * @param email
     * @return
     */
    boolean deleteUser(String email);

    /**
     * 更新用户
     * @param user
     * @return
     */
    boolean updataUser(User user);

    /**
     *查找用户
     * @param name
     * @param tel
     * @return
     */
    List<User> getSearchUsers(String name, String tel);

    /**
     * 上传头像到七牛云
     * @param avatar
     * @return
     */
    String uploadAvatar(MultipartFile avatar);

}
