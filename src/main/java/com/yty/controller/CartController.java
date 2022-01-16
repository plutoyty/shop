package com.yty.controller;

import com.yty.Vo.BaseResult;
import com.yty.Vo.CartResult;
import com.yty.service.CartService;
import com.yty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@CrossOrigin
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService carService;

    @Autowired
    private UserService userService;


    /**
     * 加入购物车
     * @param goodsId
     * @param email
     * @param select
     * @return
     */
    @RequestMapping("/push")
    public BaseResult pushCar(@RequestParam("id") String goodsId,
                              @RequestParam("email") String email,
                              @RequestParam("select") String select) {
        BaseResult baseResult = new BaseResult();
        System.out.println(goodsId);
        int id = userService.getUserByName(email).getId();
        boolean f1 = carService.merge(goodsId, id);
        if (f1 == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("加入购物车成功");
        } else {
            boolean f = carService.push(goodsId, email, select, String.valueOf(id));
            if (f == true) {
                baseResult.setStatus(100);
                baseResult.setMsg("加入购物车成功");
            } else {
                baseResult.setStatus(200);
                baseResult.setMsg("加入购物车失败");
            }
        }
        return baseResult;
    }


    /**
     * 获取购物车中所有商品
     * @param email
     * @return
     */
    @RequestMapping("/getcarts")
    public CartResult getCart(@RequestParam("email")String email){
        CartResult cartResult = new CartResult();
        cartResult.setData(carService.getAllCartItem(email));
        cartResult.setSum(carService.getSum(cartResult.getData()));
        cartResult.setSelectAll(carService.selectAll(cartResult.getData()));
        cartResult.setStatus(100);
        return cartResult;
    }


    /**
     * 商品数量和是否选中更新
     * @param goodsId
     * @param email
     * @param count
     * @return
     */
    @RequestMapping("/updatecart")
    public BaseResult count(@RequestParam("id")String goodsId,
                            @RequestParam("email")String email,
                            @RequestParam("count") String count,
                            @RequestParam("select")String selected){
        BaseResult baseResult = new BaseResult();
        boolean f = carService.updateCart(goodsId,email,count,selected);
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
     * 删除操作
     * @param email
     * @param goodsId
     * @return
     */
    @RequestMapping("delete")
    private BaseResult delete(@RequestParam("email")String email,
                              @RequestParam("goodsId")String goodsId){
        BaseResult baseResult = new BaseResult();
        boolean f = carService.deleteItem(goodsId,email);
        if (f==true){
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        }else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

    /**
     * 对某个用户购物车中的内容全选
     * @param email
     * @return
     */
    @RequestMapping("/allSelect")
    private BaseResult allSelect(@RequestParam("email")String email){
        BaseResult baseResult = new BaseResult();
        boolean f = carService.allSelect(email);
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
     * 取消全选
     * @param email
     * @return
     */
    @RequestMapping("/unAllSelect")
    private BaseResult unAllSelect(
            @RequestParam("email")String email){
        BaseResult baseResult = new BaseResult();
        boolean f = carService.unAllSelect(email);
        if(f==true){
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        }else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

}
