package com.yty.dao;

import com.yty.Vo.store.ShopApply;
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
}
