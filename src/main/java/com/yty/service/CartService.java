package com.yty.service;

import com.yty.entity.CartItem;
import java.util.List;

public interface CartService {

    /**
     * 将物品加入购物车
     * @param goodsId
     * @param email
     * @return
     */
    boolean push(String goodsId,String email,String select,String id);

    /**
     * 合并购物车中相同的商品
     * @param goodsId
     * @param id
     * @return
     */
    boolean merge(String goodsId, int id);

    /**
     * 获取该邮箱的所有购物车中的物品
     * @param email
     * @return
     */
    List<CartItem> getAllCartItem(String email);
}
