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
    public boolean push(String goodsId, String email, String select, String id) {
        return carMapper.push(goodsId, email, id, select);
    }

    @Override
    public boolean merge(String goodsId, int id) {
        return carMapper.merge(goodsId, id);
    }

    @Override
    public List<CartItem> getAllCartItem(String email) {
        List<CartItem> list = carMapper.getAllCart(email);
        return list;
    }

    @Override
    public List<CartItem> getAllSelectCartItem(String email) {
        return carMapper.getAllSelectCartItem(email);
    }

    @Override
    public boolean deleteSelect(String email) {
        return carMapper.deleteSelect(email);
    }

    @Override
    public boolean updateCart(String id, String email, String count, String select) {
        return carMapper.updateCart(id, email, count, select);
    }

    @Override
    public boolean deleteItem(String id, String email) {
        return carMapper.deleteItem(email, id);
    }

    @Override
    public boolean allSelect(String email) {
        return carMapper.selectAll(email);
    }

    @Override
    public boolean unAllSelect(String email) {
        return carMapper.unSelectAll(email);
    }

    @Override
    public Integer getSum(List<CartItem> list) {
        Integer sum = 0;
        for (CartItem c : list) {
            if (c.getSelect().equals("1")) {
                sum += c.getCount() * c.getProduct().getPrice();
            }
        }
        return sum;
    }

    @Override
    public String selectAll(List<CartItem> list) {
        for (CartItem c : list) {
            if (c.getSelect().equals("0"))return "false";
        }
        return "true";
    }
}
