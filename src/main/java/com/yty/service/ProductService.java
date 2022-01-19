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

    /**
     * 更新浏览时间
     * @param email
     * @param goodsId
     * @return
     */
    boolean lookup(String email, String goodsId);

    /**
     * 获取浏览记录
     * @param email
     * @return
     */
    List<Product> getLookup(String email);

    /**
     * 添加商品
     * @param product
     * @return
     */
    boolean addGoods(Product product);

    /**
     * 查询商品
     * @param title
     * @param category
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Product> getGoods(String title, String category, String pageIndex, String pageSize);

    /**
     * 删除商品
     * @param id
     * @return
     */
    boolean deleteGood(String id);

    /**
     * 更改商品资料
     * @param product
     * @return
     */
    boolean updGood(Product product);
}
