package com.yty.entity;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String img;
    private Integer price;
    private Integer oldPrice;
    private String title;
    private String subtitle;
    private String shopId; //属于哪个店铺
    private String category; //分类
    private String categoryId; //分类id
    private String sellCount;//销量
    private String repertory; //库存
    private String status;//状态
    private String hot;//热门 1 不热门 0
    private String date; //创建时间

}
