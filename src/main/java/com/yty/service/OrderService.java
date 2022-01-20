package com.yty.service;

import com.yty.entity.Address;
import com.yty.entity.CartItem;
import com.yty.entity.Order;

import java.util.List;

public interface OrderService {


    /**
     * 新增订单
     * @param email
     * @param list
     * @param address
     * @return
     */
    Order creatOrder(String email, List<CartItem> list, Address address);

    /**
     * 获取该邮箱的所有订单
     * @param email
     * @return
     */
    List<Order> getAllByEmail(String email);

    /**
     * 获取订单
     * @param email
     * @param orderId
     * @return
     */
    Order getOrderById(String email, String orderId);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    boolean delete( String orderId);

    /**
     * 获取该订单的商品
     * @param orderId
     * @return
     */
    List<CartItem> getGoodsByOrderId(String orderId);

    /**
     * 更新支付状态
     * @param status
     * @param orderId
     * @return
     */
    boolean updateStatus(String status, String orderId);

    /**
     * 查询商品
     * @param orderId
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    List<Order> selectOrder(String orderId, String status, String page, String pageSize);
}
