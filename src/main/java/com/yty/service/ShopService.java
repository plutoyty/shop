package com.yty.service;

import com.yty.Vo.store.ShopApply;
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
}
