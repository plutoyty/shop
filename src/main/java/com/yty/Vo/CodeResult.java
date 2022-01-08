package com.yty.Vo;

import lombok.Data;

/**
 * 100 发送成功
 * 200 发送失败
 * 300 验证码未过期传回还有多少秒过期
 */
@Data
public class CodeResult {
    private Integer status;
    private String msg;
}
