package com.yty.dao;

import com.yty.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ProductMapper {

    /**
     * 通过商品编号获得商品信息
     * @param id
     * @return
     */
    Product getProduct(String id);

    /**
     * 通过种类id获取该种类的商品
     * @param id
     * @return
     */
    List<Product> getProductList(String id);
}
