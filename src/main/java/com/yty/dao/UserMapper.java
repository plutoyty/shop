package com.yty.dao;

import com.yty.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    User login(@Param("account") String account, @Param("password") String password);

    /**
     * 注册一个user信息
     *
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 通过邮箱或者手机号获取一个user
     *
     * @param name
     * @return
     */
    User getUser(String name);

    /**
     * 修改个人信息
      *
     * @param user
     * @return
     */
    boolean changUserInfo(User user);

    /**
     * 判断是否是管理员
     *
     * @param id
     * @return
     */
    String admin(String id);

    /**
     * 修改密码
     *
     * @param email
     * @param newPassword
     */
    void updatePwd(@Param("email") String email, @Param("password") String newPassword,@Param("privateKey")String privateKey);

    /**
     * 获取用户
     * ***分页未加
     *
     * @return
     */
    List<User> getUsers();

    /**
     * 获取用户的数量
     *
     * @return
     */
    int getUsersCount();

    /**
     * 删除用户
     * @param email
     * @return
     */
    boolean deleteUser(String email);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 查询用户
     * @param name
     * @param tel
     * @return
     */
    List<User> getSearchUsers(@Param("name") String name,@Param("tel") String tel);
}
