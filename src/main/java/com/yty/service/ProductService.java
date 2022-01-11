package com.yty.service;

import com.yty.entity.Product;

import java.util.List;

public interface ProductService {

    /**
     * 根据商品的编号获取商品
     * @param id
     * @return
     */
    Product getProductInfo(String id);

    /**
     * 获取id种类的商品
     * @param id
     * @return
     */
    List<Product> getProductList(String id);

}
