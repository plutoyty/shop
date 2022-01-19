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
        return productMapper.addGoods(product);
    }


}
