package com.yty.entity;

import lombok.Data;

@Data
public class Order {
    private String id;
    private String goodId;
    private String status;
    private String per; //单价
    private String date;

}
