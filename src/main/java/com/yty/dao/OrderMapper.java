package com.yty.dao;

import com.yty.entity.CartItem;
import com.yty.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 把商品添加到订单中去
     * @param id
     * @param order
     * @return
     */
    boolean addGoods(@Param("id") String id,@Param("order") String order,@Param("count")String count);

    /**
     * 新建订单
     * @param order
     * @return
     */
    boolean creatOrder(@Param("order") Order order,@Param("email") String  email);

    /**
     *  获取所有订单
     * @param email
     * @return
     */
    List<Order> getAllByEmail(String email);

    /**
     *  获取该id订单
     * @param email
     * @param orderId
     * @return
     */
    Order getOrderById(@Param("email") String email,@Param("orderId") String orderId);

    /**
     * 获取订单中的商品
     * @param orderId
     * @return
     */
    List<CartItem> getOrderGoods(String orderId);

    /**
     * 更新订单的状态
     * @param status
     * @param orderId
     * @return
     */
    boolean updateStatus(@Param("status") String status,@Param("orderId") String orderId,@Param("date")String date);

    /**
     * 获取订单列表
     * @param orderId
     * @param status
     * @param start
     * @param end
     * @return
     */
    List<Order> selectOrder(@Param("orderId") String orderId,@Param("status") String status,
                            @Param("start") Integer start,@Param("end") Integer end);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    boolean deleteOrder(@Param("orderId") String orderId);
}
