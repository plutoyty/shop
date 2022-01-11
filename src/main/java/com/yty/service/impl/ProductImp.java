package com.yty.service.impl;

import com.yty.dao.ProductMapper;
import com.yty.entity.Product;
import com.yty.service.ProductService;
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


}
