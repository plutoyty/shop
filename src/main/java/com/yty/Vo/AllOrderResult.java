package com.yty.Vo;

import com.yty.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class AllOrderResult {
    private Integer status;
    private List<Order> data;
}
