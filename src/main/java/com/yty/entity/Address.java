package com.yty.entity;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private String region; //地区
    private String detail;//详细地址
    private String postalCode;
    private String name;
    private String tel;
    private String isDefault;
    private String status;
    private String alias;//别名
}
