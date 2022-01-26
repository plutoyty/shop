package com.yty.Vo.store;

import com.yty.entity.Shop;
import com.yty.entity.User;
import lombok.Data;

/**
 * @ClassName: ShopApply
 * @Description: 申请
 * @author: yty
 * @Date: 2022/1/24 22:40
 * @Version: 1.0
 */
@Data
public class ShopApply {
    private User user;
    private Shop shop;
}
