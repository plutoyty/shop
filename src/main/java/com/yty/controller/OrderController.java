package com.yty.controller;


import com.yty.Vo.AllOrderResult;
import com.yty.Vo.BaseResult;
import com.yty.Vo.OrderResult;
import com.yty.entity.Address;
import com.yty.entity.CartItem;
import com.yty.entity.Order;
import com.yty.service.AddressService;
import com.yty.service.CartService;
import com.yty.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    /**
     * 创建订单
     *
     * @param email
     * @param place
     * @return
     */
    @RequestMapping("/createOrder")
    public OrderResult creat(@RequestParam("email") String email,
                             @RequestParam("shippingId") String place) {
        System.out.println(email);
        OrderResult orderResult = new OrderResult();
        List<CartItem> list = cartService.getAllSelectCartItem(email);
        Address address = addressService.getAddress(place);
        Order order = orderService.creatOrder(email, list, address);
        cartService.deleteSelect(email);
        orderResult.setStatus(100);
        orderResult.setData(order);
        return orderResult;
    }

    /**
     * 获取所有订单
     *
     * @param email
     * @return
     */
    @RequestMapping("/getAll")
    public AllOrderResult getAll(@RequestParam("email") String email) {
        AllOrderResult allOrderResult = new AllOrderResult();
        allOrderResult.setData(orderService.getAllByEmail(email));
        for (Order c : allOrderResult.getData()) {
            c.setGoods(orderService.getGoodsByOrderId(c.getOrderId()));
        }
        allOrderResult.setStatus(100);
        return allOrderResult;
    }

    /**
     * 获取订单
     *
     * @param email
     * @param orderId
     * @return
     */
    @RequestMapping("/getOrder")
    public OrderResult getAll(@RequestParam("email") String email,
                              @RequestParam("orderId") String orderId) {
        OrderResult orderResult = new OrderResult();
        orderResult.setData(orderService.getOrderById(email, orderId));
        List<CartItem> list = orderService.getGoodsByOrderId(orderId);
        orderResult.getData().setGoods(list);
        orderResult.setStatus(100);
        return orderResult;
    }

    /**
     * 更新支付状态
     *
     * @param status
     * @param orderId
     * @return
     */
    @RequestMapping("/updateStatus")
    private BaseResult updateStatus(@RequestParam("status") String status,
                                    @RequestParam("orderId") String orderId) {
        BaseResult baseResult = new BaseResult();
        boolean f = orderService.updateStatus(status, orderId);
        if (f == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("更新成功");
        } else {
            baseResult.setStatus(200);
            baseResult.setMsg("更新失败");
        }
        return baseResult;
    }

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/deleteOrder")
    private BaseResult deleteOrder(@RequestParam("orderId") String orderId) {
        BaseResult baseResult = new BaseResult();
        boolean f = orderService.delete(orderId);
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
     * 订单列表
     *
     * @param orderId
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("selectOrder")
    private AllOrderResult selectOrder(@RequestParam("orderId") String orderId,
                                       @RequestParam("status") String status,
                                       @RequestParam("pageIndex") String page,
                                       @RequestParam("pageSize") String pageSize) {
        if (status.trim().equals("")) status = null;
        AllOrderResult allOrderResult = new AllOrderResult();
        allOrderResult.setData(orderService.selectOrder(orderId, status, page, pageSize));
        for (Order c : allOrderResult.getData()) {
            c.setGoods(orderService.getGoodsByOrderId(c.getOrderId()));
        }
        allOrderResult.setStatus(100);
        return allOrderResult;
    }


}
