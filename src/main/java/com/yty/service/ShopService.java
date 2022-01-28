package com.yty.service;

import com.yty.Vo.store.ShopApply;
import com.yty.entity.Order;
import com.yty.entity.Product;
import com.yty.entity.Shop;

import java.util.List;

public interface ShopService {

    /**
     * 申请店铺
     * @param email
     * @param shop
     * @return
     */
    boolean applyShop(String email, Shop shop);

    /**
     * 获取所有的店铺申请
     * @return
     */
    List<ShopApply> getAllApply();

    /**
     * 获取该店铺的所有商品
     * @param shopId
     * @return
     */
    List<Product> getGoods(String shopId);

    /**
     * 获取售后订单
     * @return
     */
    List<Order> getAfterOrder();
}
