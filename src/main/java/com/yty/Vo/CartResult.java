package com.yty.Vo;

import com.yty.entity.CartItem;
import lombok.Data;
import java.util.List;

@Data
public class CartResult {
    private Integer status;
    private List<CartItem> data;
    private Integer sum;
    private String selectAll;
}
