package com.yty.entity;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private String province; //省
    private String city;   //市
    private String region; //区
    private String detail;//详细地址
    private String postalCode;//邮政编码
    private String name;
    private String tel;
    private String isDefault;
    private String status;
    private String alias;//别名
}
