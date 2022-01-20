package com.yty.entity;

import lombok.Data;
import java.util.List;

@Data
public class Order {
    private String orderId; //订单号
    private List<CartItem> goods;
    private String status; //订单状态
    private String updateDate;
    private String creatDate;
    private String province;
    private String city;
    private String region;
    private String detail;
    private String name;
    private String tel;
    private Integer sum;//总价格
    private String username;//下订单的用户名
}
