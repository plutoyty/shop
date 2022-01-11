package com.yty.Vo;

import com.yty.entity.LoginData;
import com.yty.entity.User;
import lombok.Data;

/**
 * 1010：成功登陆
 * 2008:邮箱错误
 * 2007:密码错误
 */

@Data
public class LoginResult {
    private int status=0;
    private String msg;
    private String name;
    private String email;
    private LoginData data;
}
