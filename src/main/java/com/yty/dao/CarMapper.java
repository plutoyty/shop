package com.yty.dao;

import com.yty.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CarMapper {

    /**
     * 将商品加入购物车
     * @param goodsId
     * @param email
     * @param id
     * @param select
     * @return
     */
    boolean push(@Param("goodsId") String goodsId,@Param("email") String email,@Param("id") String id,@Param("select") String select);


    /**
     * 合并购物车相同商品
     * @param goodsId
     * @param id
     * @return
     */
    boolean merge(@Param("goodsId") String goodsId,@Param("userId") int id);

    /**
     * 相同的商品减1
     * @param goodsId
     * @param id
     * @return
     */
    boolean minus(@Param("goodsId") String goodsId,@Param("userId") int id);

    /**
     * 获取购物车中商品
     * @param email
     * @return
     */
    List<CartItem> getAllCart(@Param("email") String email);

    /**
     * 更新购物车中商品
     * @param goodsId
     * @param email
     * @param count
     * @param select
     * @return
     */
    boolean updateCart(@Param("goodsId")String goodsId,@Param("email")
            String email,@Param("count") String count,@Param("select")String select);

    /**
     * 删除购物车中的商品
     * @param email
     * @param goodsId
     * @return
     */
    boolean deleteItem(@Param("email")String email,@Param("goodsId")String goodsId);

    /**
     * 对该用户的购物车进行全选
     * @param email
     * @return
     */
    boolean selectAll(String email);

    /**
     * 取消全选
     * @param email
     * @return
     */
    boolean unSelectAll(String email);

    /**
     * 获取所有选中的商品
     * @param email
     * @return
     */
    List<CartItem> getAllSelectCartItem(String email);

    /**
     * 删除该用户所有选中的商品
     * @param email
     * @return
     */
    boolean deleteSelect(String email);

    /**
     * 获取购物车的数量
     * @param email
     * @return
     */
    Integer getCartCount(String email);
}
