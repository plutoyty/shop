package com.yty.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: ShopGoods
 * @Description: 店铺中的商品
 * @author: yty
 * @Date: 2022/1/24 17:09
 * @Version: 1.0
 */
@Data
public class ShopGoods {
    private Shop shop;
    private List<Product> products;
}
