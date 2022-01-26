package com.yty.service.impl;

import com.yty.Vo.store.ShopApply;
import com.yty.dao.ShopMapper;
import com.yty.entity.Shop;
import com.yty.service.ShopService;
import com.yty.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ShopImp
 * @Description: TODO
 * @author: yty
 * @Date: 2022/1/24 17:12
 * @Version: 1.0
 */
@Service
public class ShopImp implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public boolean applyShop(String email, Shop shop) {
        if (email == null || email.equals("") || shop.getName().equals("")){
            return false;
        }
        shop.setCreatDate(DateUtil.ptfDate());
        return shopMapper.applyShop(email,shop);
    }

    @Override
    public List<ShopApply> getAllApply() {
        return shopMapper.getAllApply();
    }
}
