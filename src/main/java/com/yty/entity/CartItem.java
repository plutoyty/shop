package com.yty.entity;

import lombok.Data;

@Data
public class CartItem {
    private Product product;
    private String select;
    private Integer count;
    private String sellCount;
}
