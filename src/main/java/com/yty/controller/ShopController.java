package com.yty.controller;

import com.yty.Vo.BaseResult;
import com.yty.Vo.store.AllApplyResult;
import com.yty.entity.Shop;
import com.yty.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ShopController
 * @Description: 店铺操作接口
 * @author: yty
 * @Date: 2022/1/24 21:00
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 申请店铺
     * @param email
     * @param shop
     * @return
     */
    @RequestMapping("applyShop")
    private BaseResult apply(@RequestParam("email")String email,
                             @RequestBody Shop shop){
//        System.out.println(email);
//        System.out.println(shop);
        BaseResult baseResult = new BaseResult();
        boolean f = shopService.applyShop(email,shop);
        if(f==true){
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        }else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

    /**
     * 获取所有的店铺申请
     * @return
     */
    @RequestMapping("getALlApply")
    private AllApplyResult getAll(){
        AllApplyResult allApplyResult = new AllApplyResult();
        allApplyResult.setStatus(100);
        allApplyResult.setList(shopService.getAllApply());
        return allApplyResult;
    }
}
