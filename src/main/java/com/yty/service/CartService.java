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

    /**
     * 获取所有选中的商品
     * @param email
     * @return
     */
    List<CartItem> getAllSelectCartItem(String email);

    /**
     * 删除所有选中的商品
     * @param email
     * @return
     */
    boolean deleteSelect(String email);

    /**
     * 更新购物车中商品的信息
     * @param id
     * @param email
     * @param count
     * @param select
     * @return
     */
    boolean updateCart(String id,String email,String count,String select);

    /**
     * 删除购物车中商品
     * @param id
     * @param email
     * @return
     */
    boolean deleteItem(String id,String email);

    /**
     * 对该邮箱的购物车进行全选
     * @param email
     * @return
     */
    boolean allSelect(String email);

    /**
     * 取消全选
     * @param email
     * @return
     */
    boolean unAllSelect(String email);

    /**
     * 求出总价格
     * @param list
     * @return
     */
    Integer getSum(List<CartItem> list);

    /**
     * 求出总价格
     * @param list
     * @return
     */
    String selectAll(List<CartItem> list);

    /**
     * 获取购物车的数量
     * @param email
     * @return
     */
    Integer getCount(String email);
}
