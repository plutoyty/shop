package com.yty.dao;

import com.yty.Vo.store.ShopApply;
import com.yty.entity.Order;
import com.yty.entity.Product;
import com.yty.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {

    /**
     * 申请店铺
     * @param email
     * @param shop
     * @return
     */
    boolean applyShop(@Param("email") String email,@Param("shop") Shop shop);

    /**
     * 获取所有的申请
     * @return
     */
    List<ShopApply> getAllApply();

    /**
     * 获取商品
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
