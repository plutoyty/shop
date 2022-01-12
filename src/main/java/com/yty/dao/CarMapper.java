package com.yty.dao;

import com.yty.Vo.CartResult;
import com.yty.entity.CartItem;
import com.yty.service.impl.CartImp;
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
}
