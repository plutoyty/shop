package com.yty.Vo;

import com.yty.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserListResult {
    private Integer status;
    private List<User> data;
    private Integer count;
}
