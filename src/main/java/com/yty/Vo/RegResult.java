package com.yty.Vo;

import lombok.Data;

/**
 * 300 注册失败
 * 500 邮箱或手机号不合法
 * 200 注册成功
 * 2002 邮箱或密码已存在，注册失败!
 * 404 填写信息不全
 * 101 邮箱验证码错误
 */
@Data
public class RegResult {
    private int status;
    private String msg;
}
