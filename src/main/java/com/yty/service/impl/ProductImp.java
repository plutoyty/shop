package com.yty.service.impl;

import com.yty.dao.ProductMapper;
import com.yty.entity.Product;
import com.yty.service.ProductService;
import com.yty.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImp implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductInfo(String id) {
        if("".equals(id))return null;
        else return productMapper.getProduct(id);
    }

    @Override
    public List<Product> getProductList(String id) {
        List<Product> list = productMapper.getProductList(id);
        return list;
    }

    @Override
    public boolean lookup(String email, String goodsId) {
        String s =  productMapper.checkLookup(email,goodsId);
        boolean f;
        if (s!=null){
           f = productMapper.updateLookup(email,goodsId, DateUtil.ptfDate());
        }else {
           f =  productMapper.insertLookup(email,goodsId,DateUtil.ptfDate());
        }
        return f;
    }

    @Override
    public List<Product> getLookup(String email) {
        List<Product> list = productMapper.getAllLookup(email);
        return list;
    }

    @Override
    public boolean addGoods(Product product) {
        product.setDate(DateUtil.ptfDate());
        return productMapper.addGoods(product);
    }

    @Override
    public List<Product> getGoods(String title, String categoryId, String pageIndex, String pageSize) {
        Integer start = Integer.valueOf(pageIndex) * Integer.valueOf(pageSize)-Integer.valueOf(pageSize);
        Integer end = start+Integer.valueOf(pageSize)+1;
        List<Product> list = productMapper.getSearchList(title,categoryId,start,end);
        return list;
    }

    @Override
    public boolean deleteGood(String id) {
        return productMapper.deleteGood(id);
    }


}
