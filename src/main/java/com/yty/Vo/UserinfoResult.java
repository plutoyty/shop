package com.yty.Vo;

import com.yty.entity.User;
import lombok.Data;

/**
 * 100 成功
 * 200 失败
 */
@Data
public class UserinfoResult {
    private Integer status;
    private User data;
}
