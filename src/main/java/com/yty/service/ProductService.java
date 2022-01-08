package com.yty.service;

import com.yty.entity.Product;

public interface ProductService {

    /**
     * 根据商品的编号获取商品
     * @param id
     * @return
     */
    Product getProductInfo(String id);


}
