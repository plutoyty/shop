package com.yty.dao;

import com.yty.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

     /**
      * 登录
      * @param account
      * @param password
      * @return
      */
     User login(@Param("account") String account, @Param("password") String password);

     /**
      * 注册一个user信息
      * @param user
      * @return
      */
     boolean register(User user);

     /**
      * 通过邮箱或者手机号获取一个user
      * @param name
      * @return
      */
     User getUser(String name);
}
