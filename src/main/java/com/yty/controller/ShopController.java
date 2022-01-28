package com.yty.controller;

import com.yty.Vo.AllOrderResult;
import com.yty.Vo.BaseResult;
import com.yty.Vo.ProductListResult;
import com.yty.Vo.store.AllApplyResult;
import com.yty.entity.Product;
import com.yty.entity.Shop;
import com.yty.service.ProductService;
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

    @Autowired
    private ProductService productService;


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

    /**
     * 获取店铺的所有商品
     * @param shopId
     * @return
     */
    @RequestMapping("getAllGoods")
    private ProductListResult allGoodsShop(@RequestParam("shopId")String shopId){
        ProductListResult productListResult = new ProductListResult();
        productListResult.setStatus(100);
        productListResult.setProducts(shopService.getGoods(shopId));
        return productListResult;
    }

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    @RequestMapping("/addGoods")
    private BaseResult addGoods(@RequestBody Product product) {
        BaseResult baseResult = new BaseResult();
        boolean f = productService.addGoods(product);
        if (f == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        } else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

    /**
     * 获取该商店的申请售后的订单
     * @param
     * @return
     */
    @RequestMapping("/findOrder")
    private AllOrderResult findOrder(){
        AllOrderResult allOrderResult = new AllOrderResult();

        return allOrderResult;
    }

    /**
     * 获取所有售后订单
     * @return
     */
    @RequestMapping("afterSale")
    private AllOrderResult AfterSale(){
        AllOrderResult allOrderResult = new AllOrderResult();
        allOrderResult.setStatus(100);
        allOrderResult.setData(shopService.getAfterOrder());
        return allOrderResult;
    }

}
