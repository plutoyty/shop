package com.yty.service.impl;

import com.yty.dao.CarMapper;
import com.yty.entity.CartItem;
import com.yty.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartImp implements CartService {

    @Autowired
    private CarMapper carMapper;


    @Override
    public boolean push(String goodsId, String email,String select,String id) {
        return carMapper.push(goodsId,email,id,select);
    }

    @Override
    public boolean merge(String goodsId, int id) {
        return carMapper.merge(goodsId,id);
    }

    @Override
    public List<CartItem> getAllCartItem(String email) {
        return carMapper.getAllCart(email);
    }
}
